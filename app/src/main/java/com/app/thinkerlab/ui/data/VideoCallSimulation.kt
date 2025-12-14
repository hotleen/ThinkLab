package com.app.thinkerlab.ui.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.random.Random

//fun main() {
//    initSdk(object : ICallback {
//        override fun onSuccess(msg: String) {
//            println("msg: $msg")`
//        }
//
//        override fun onFail(err: String) {
//            println("err: $err")
//        }
//    })
//}

//模拟初始化sdk
fun initSdk(initCallback: ICallback) {
    if ((System.currentTimeMillis() % 2).toInt() == 0) {
        initCallback.onSuccess("init success")
        loginToServer(object : ICallback {
            override fun onSuccess(msg: String) {
                println("msg: $msg")
            }

            override fun onFail(err: String) {
                println("err: $err")
            }
        })
    } else {
        initCallback.onFail("init failed")
    }
}

//登录服务
fun loginToServer(loginCallback: ICallback) {
    if ((System.currentTimeMillis() % 2).toInt() == 0) {
        loginCallback.onSuccess("login success")
        call(object : ICallback {
            override fun onSuccess(msg: String) {
                println("msg: $msg")
            }

            override fun onFail(err: String) {
                println("err: $err")
            }
        })
    } else {
        loginCallback.onFail("login failed")
    }
}

//呼叫
fun call(callVideoCallback: ICallback) {
    if ((System.currentTimeMillis() % 2).toInt() == 0) {
        callVideoCallback.onSuccess("call success")
        startVideoSession(object : ICallback {
            override fun onSuccess(msg: String) {
                println("msg: $msg")
            }

            override fun onFail(err: String) {
                println("err: $err")
            }
        })
    } else {
        callVideoCallback.onFail("call failed")
    }
}

//开始视频会话
fun startVideoSession(startSessionCallback: ICallback) {
    if ((System.currentTimeMillis() % 2).toInt() == 0) {
        startSessionCallback.onSuccess("start success")
    } else {
        startSessionCallback.onFail("start video fail")
    }
}

interface ICallback {
    fun onSuccess(msg: String)

    fun onFail(err: String)
}

/* =========================
 * 2. 埋点 & 交易（业务副作用）
 * ========================= */

interface Tracker {
    fun track(event: String)
}

class ConsoleTracker : Tracker {
    override fun track(event: String) {
        println("📊 track -> $event")
    }
}

class TradeService {
    suspend fun sendAsyncTrade() {
        delay(300)
        println("💰 async trade sent")
    }
}

/* =========================
 * 3. Repository（核心）
 * ========================= */

class VideoRepository(
    private val tracker: Tracker,
    private val tradeService: TradeService
) {

    suspend fun startVideoFlow() {
        init()
        login()
        callVideo()
        startVideo()

        // 视频通话成功后的业务副作用
        tradeService.sendAsyncTrade()
        tracker.track("video_trade_sent")
    }

    private suspend fun init() =
        suspendCancellableCoroutine<Unit> { cont ->
            initSdk(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("init_success")
                    cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("init_fail")
                    cont.resumeWithException(RuntimeException(err))
                }
            })
        }

    private suspend fun login() =
        suspendCancellableCoroutine<Unit> { cont ->
            loginToServer(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("login_success")
                    cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("login_fail")
                    cont.resumeWithException(RuntimeException(err))
                }
            })
        }

    private suspend fun callVideo() =
        suspendCancellableCoroutine<Unit> { cont ->
            call(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("call_success")
                    cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("call_fail")
                    cont.resumeWithException(RuntimeException(err))
                }
            })
        }

    private suspend fun startVideo() =
        suspendCancellableCoroutine<Unit> { cont ->
            startVideoSession(object : ICallback {
                override fun onSuccess(msg: String) {
                    tracker.track("start_video_success")
                    cont.resume(Unit)
                }

                override fun onFail(err: String) {
                    tracker.track("start_video_fail")
                    cont.resumeWithException(RuntimeException(err))
                }
            })
        }

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


interface NetworkQualityCallback {
    fun onQualityChanged(level: Int)
}

interface VolumeCallback {
    fun onVolumeChanged(volume: Int)
}

object VideoSdk {

    fun startNetworkQualityMonitor(callback: NetworkQualityCallback): Cancelable {
        val job = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                delay(500)
                callback.onQualityChanged(Random.nextInt(1, 6))
            }
        }
        return Cancelable { job.cancel() }
    }

    fun startVolumeMonitor(callback: VolumeCallback): Cancelable {
        val job = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                delay(300)
                callback.onVolumeChanged(Random.nextInt(0, 100))
            }
        }
        return Cancelable { job.cancel() }
    }
}

class Cancelable(private val cancelAction: () -> Unit) {
    fun cancel() = cancelAction()
}

/* =========================================================
 * 6. 通话状态模型（Repository 只关心状态）
 * ========================================================= */

sealed class NetworkQuality {
    object Excellent : NetworkQuality()
    object Good : NetworkQuality()
    object Normal : NetworkQuality()
    object Poor : NetworkQuality()
    object Bad : NetworkQuality()

    companion object {
        fun from(level: Int): NetworkQuality =
            when (level) {
                5 -> Excellent
                4 -> Good
                3 -> Normal
                2 -> Poor
                else -> Bad
            }
    }
}

/* =========================================================
 * 7. 上层调用（模拟页面 / ViewModel）
 * ========================================================= */

fun main() = runBlocking {
    val repository = VideoRepository(
        tracker = ConsoleTracker(),
        tradeService = TradeService()
    )

    try {
        repository.startVideoFlow()
        println("✅ 视频通话启动成功")
    } catch (e: Exception) {
        println("❌ 启动失败：${e.message}")
        return@runBlocking
    }

    val job1 = launch {
        repository.observeNetworkQuality()
            .collect { quality ->
                println("📶 网络质量：$quality")
            }
    }

    val job2 = launch {
        repository.observeRemoteVolume()
            .collect { volume ->
                println("🔊 对方音量：$volume")
            }
    }

    delay(3000)

    println("🛑 挂断通话")
    job1.cancel()
    job2.cancel()
}


/* =========================
 * 4. 上层调用（模拟 UI / main）
 * ========================= */

//fun main() = runBlocking {
//    val repository = VideoRepository(
//        tracker = ConsoleTracker(),
//        tradeService = TradeService()
//    )
//
//    try {
//        repository.startVideoFlow()
//        println("✅ 视频通话流程完成")
//    } catch (e: Exception) {
//        println("❌ 流程失败：${e.message}")
//    }
//}