# CLAUDE.md

该文件为 Claude Code（claude.ai/code）在此代码库中工作时提供指导。

## 构建与测试

```bash
./gradlew assembleDebug          # 构建 Debug APK
./gradlew assembleRelease        # 构建 Release APK（启用混淆）
./gradlew test                   # 运行单元测试
./gradlew connectedAndroidTest   # 在已连接的设备上运行插桩测试
./gradlew lint                   # 运行 lint 检查
```

APK 输出命名规则：`ThinkLab-<variant>-<version>.apk`（详见 `app/build.gradle.kts`）。

## 架构

**ThinkLab** 是一个 Android 应用（`com.app.thinkerlab`），使用 Kotlin 2.2.0 + Jetpack Compose + Material3。最低 SDK 24，目标/编译 SDK 35，JVM 目标 11。单模块项目，无 DI 框架。

### 导航策略

应用使用**三种不同的导航模式**：

1. **Tab 切换（MainActivity）** — `mutableIntStateOf(selectedTab)` 在 `Scaffold` + `MainBottomBar` 中切换 `HomePage` 和 `ProfilePage`。不使用 NavHost。
2. **NavHost（EBikeOverviewActivity）** — `NavHost` 包含两个目的地（`EBikeRangeScreen` → `EBikeMileageScreen`），通过 `navController.navigate()` / `popBackStack()` 导航。
3. **显式 Intent** — HomePage 的 `MessageCard` 通过 `context.startActivity(Intent(...))` 启动 `DualRecordActivity` 或 `EBikeOverviewActivity`。

### Activity 概览

| Activity | UI 方式 | 用途 |
|---|---|---|
| `MainActivity` | Compose（`setContent`） | 启动页。底部 Tab 导航，包含首页和个人中心。 |
| `EBikeOverviewActivity` | Compose（`NavHost`） | 电动车续航仪表盘 + 里程记录（Canvas 图表 + `compose-charts`）。 |
| `DualRecordActivity` | XML（`AppCompatActivity`） | 双录视频通话界面。横屏，`ConstraintLayout` + `TextureView`。通过 `repeatOnLifecycle` 收集 ViewModel 的 Flow。 |

### 包目录

| 包 | 内容 |
|---|---|
| `ui.components` | 可复用 Composable：`MainBottomBar`、`HomePage`（消息列表）、`ProfilePage`（个人中心）、`EBikeRangeScreen`（仪表盘卡片 + Canvas 图表 + 弹窗）、`EBikeMileageScreen`（骑行记录列表 + `compose-charts` 折线图）、`TopSearchBar`、`PageHeader` |
| `ui.activity` | Activity + `DualRecordViewModel` |
| `ui.data` | `Screen` 密封类（路由定义）、`VideoRepository`（回调→协程桥接）、`VideoCallSimulation`（模拟 SDK 演示） |
| `ui.theme` | `Color.kt` — 4 个颜色值，转换为 CieXyz 色彩空间（无自定义 `MaterialTheme` 包装） |
| `ui.graphic` | `SunIcon` — 使用 `path()` 手动构建的自定义 `ImageVector` |

### 关键模式

- **回调 → 挂起函数桥接**：`VideoRepository` 使用 `suspendCancellableCoroutine` 包装基于回调的 SDK 调用，恢复前检查 `cont.isActive`。失败时抛出自定义 `VideoCallException`。
- **持续观察**：产生连续回调的 SDK 方法使用 `callbackFlow` + `awaitClose` 包装为冷 Flow，供页面以 `Flow<NetworkQuality>` / `Flow<Int>` 方式消费。
- **ViewModel → Activity 通信**：`DualRecordViewModel` 通过 `shareIn` 构建 `secondTicker` Flow，派生 `StateFlow`。Activity 使用 `lifecycleScope.repeatOnLifecycle(STARTED)` 收集，每个流嵌套 `launch`。
- **UI 状态管理**：Composable 内使用 `remember { mutableIntStateOf() }` / `mutableStateOf()` 管理本地状态；Tab/首页/个人中心无独立 ViewModel。
- **Java 互调**：`VideoRepository.videoCall(scope, callback)` 为 Java 调用方包装协程链，结果映射到 `VideoCallCallback` 接口。
- **依赖解析**：`settings.gradle.kts` 中阿里云镜像配置在 Google/Maven Central 之前。
- **版本目录**：依赖定义在 `gradle/libs.versions.toml` 中，通过 `libs.*` 访问器引用。
- **无 DI**：依赖（`Tracker`、`TradeService`）通过 `VideoRepositoryProvider` 中的 `lazy` 委托手动实例化。
