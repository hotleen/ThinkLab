package com.app.thinkerlab.ui.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


/**
 *         findViewById(R.id.btn_call).setOnClickListener(v -> {
 *
 *             VideoRepository repository =
 *                     VideoRepositoryProvider.INSTANCE.getRepository();
 *
 *             repository.videoCall(
 *                     LifecycleOwnerKt.getLifecycleScope(this),
 *                     new VideoCallCallback() {
 *                         @Override
 *                         public void onSuccess() {
 *                             // 呼叫成功 → 进入通话页
 *                             startActivity(
 *                                     new Intent(CallActivity.this, VideoCallActivity.class)
 *                             );
 *                         }
 *
 *                         @Override
 *                         public void onFailure(int code, String message) {
 *                             Toast.makeText(
 *                                     CallActivity.this,
 *                                     "失败(" + code + ")：" + message,
 *                                     Toast.LENGTH_SHORT
 *                             ).show();
 *                         }
 *                     }
 *             );
 *         });
 *     }
 */

object VideoRepositoryProvider {

    val repository: VideoRepository by lazy {
        VideoRepository(
            tracker = ConsoleTracker(),
            tradeService = TradeService()
        )
    }
}

/**
 * 视频呼叫 Repository
 *
 * - Java 页面：videoCall(scope, callback)
 * - Kotlin 页面：observeNetworkQuality / observeRemoteVolume
 */
interface VideoCallCallback {
    fun onSuccess()
    fun onFailure(code: Int, message: String)
}

sealed class VideoCallResult {
    object Success : VideoCallResult()
    data class Failure(
        val code: Int,
        val message: String
    ) : VideoCallResult()
}

class VideoCallException(
    val code: Int,
    message: String
) : RuntimeException(message)

object VideoErrorCode {
    const val INIT_FAIL = 1001
    const val LOGIN_FAIL = 1002
    const val CALL_FAIL = 1003
}


class VideoRepository(
    private val tracker: Tracker,
    private val tradeService: TradeService
) {

    /* =========================================================
     * 1. 对外统一视频呼叫入口（给 Java 用）
     * ========================================================= */

    fun videoCall(
        scope: CoroutineScope,
        callback: VideoCallCallback
    ) {
        scope.launch {
            when (val result = videoCallInternal()) {
                is VideoCallResult.Success -> callback.onSuccess()
                is VideoCallResult.Failure ->
                    callback.onFailure(result.code, result.message)
            }
        }
    }

    /* =========================================================
     * 2. Kotlin 内部使用的 suspend 流程
     * ========================================================= */

    private suspend fun videoCallInternal(): VideoCallResult {
        return try {
            init()
            login()
            callVideo()

            tracker.track("video_call_success")
            VideoCallResult.Success
        } catch (e: VideoCallException) {
            tracker.track("video_call_fail_${e.code}")
            VideoCallResult.Failure(
                code = e.code,
                message = e.message ?: "unknown error"
            )
        }
    }

    /* =========================================================
     * 3. SDK 回调 → suspend
     * ========================================================= */

    private suspend fun init() =
        suspendCancellableCoroutine<Unit> { cont ->
            initSdk(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("init_success")
                    if (cont.isActive) cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("init_fail")
                    if (cont.isActive) {
                        cont.resumeWithException(
                            VideoCallException(
                                VideoErrorCode.INIT_FAIL,
                                err
                            )
                        )
                    }
                }
            })
        }

    private suspend fun login() =
        suspendCancellableCoroutine<Unit> { cont ->
            loginToServer(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("login_success")
                    if (cont.isActive) cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("login_fail")
                    if (cont.isActive) {
                        cont.resumeWithException(
                            VideoCallException(
                                VideoErrorCode.LOGIN_FAIL,
                                err
                            )
                        )
                    }
                }
            })
        }

    private suspend fun callVideo() =
        suspendCancellableCoroutine<Unit> { cont ->
            call(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("call_success")
                    if (cont.isActive) cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("call_fail")
                    if (cont.isActive) {
                        cont.resumeWithException(
                            VideoCallException(
                                VideoErrorCode.CALL_FAIL,
                                err
                            )
                        )
                    }
                }
            })
        }

    /* =========================================================
     * 4. 通话中状态（Flow）
     * ========================================================= */

    fun observeNetworkQuality(): Flow<NetworkQuality> = callbackFlow {
        val cancelable = VideoSdk.startNetworkQualityMonitor(
            object : NetworkQualityCallback {
                override fun onQualityChanged(level: Int) {
                    trySend(NetworkQuality.from(level))
                }
            }
        )
        awaitClose { cancelable.cancel() }
    }

    fun observeRemoteVolume(): Flow<Int> = callbackFlow {
        val cancelable = VideoSdk.startVolumeMonitor(
            object : VolumeCallback {
                override fun onVolumeChanged(volume: Int) {
                    trySend(volume)
                }
            }
        )
        awaitClose { cancelable.cancel() }
    }
}
