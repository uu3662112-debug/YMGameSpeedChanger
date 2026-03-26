# 游戏变速器 APK

一个Android应用,支持对游戏进行变速(加速/减速),倍率范围 0.1x - 100.0x。

## ⚠️ 重要警告

**使用本工具可能导致的后果:**
- 游戏账号被封禁
- 游戏卡顿或崩溃
- 联网游戏会被服务器检测
- 违反游戏服务条款

**仅适用于:**
- 单机游戏测试
- 游戏开发调试
- 个人学习和研究

## 功能特性

- 🚀 **倍率范围**: 0.1x - 100.0x
- 📱 **应用选择**: 支持选择任意已安装应用
- 🎮 **预设速度**: 快速切换常用倍率(0.5x, 1.0x, 2.0x, 5.0x)
- 🔧 **自定义倍率**: 支持输入任意倍率
- 🎨 **简洁界面**: 现代化Material Design界面

## 技术说明

本APK目前实现了:
- ✅ 完整的UI界面
- ✅ 速度倍率管理逻辑
- ✅ 应用列表获取
- ⚠️ **时间Hook功能需要虚拟框架支持**

### 为什么需要虚拟框架?

Android系统限制,普通APK无法直接Hook系统时间函数。要实现完整的变速功能,需要:

1. **VirtualApp**: 提供虚拟应用环境
2. **LSPosed/Xposed**: Hook系统API
3. **或使用已集成虚拟环境的ROM**

### 推荐方案

#### 方案1: 集成VirtualApp(推荐)

修改项目,添加VirtualApp依赖:

```gradle
// build.gradle.kts
dependencies {
    implementation 'io.virtualapp:lib:0.16.0'
}
```

然后在VirtualApp的虚拟环境中启动游戏,并在其中Hook时间函数。

#### 方案2: 使用现有虚拟环境

- **VMOS Pro**: 安装本APK到VMOS,在VMOS内启动游戏
- **光速虚拟机**: 类似VMOS
- **F1虚拟机**: 集成LSPosed的虚拟机

#### 方案3: Root设备 + LSPosed

- 设备Root后安装LSPosed
- 开发LSPosed模块版本
- 模块Hook所有时间API

## 构建步骤

### 环境要求

- JDK 17+
- Android Studio Hedgehog (2023.1.1) 或更高版本
- Android SDK 34
- Gradle 8.2

### 构建APK

1. 打开Android Studio
2. 导入项目 `GameSpeedChanger`
3. 等待Gradle同步完成
4. 点击 Build -> Build Bundle(s) / APK(s) -> Build APK(s)
5. APK位置: `app/build/outputs/apk/debug/app-debug.apk`

### 命令行构建

```bash
# Windows
cd GameSpeedChanger
gradlew.bat assembleDebug

# Linux/Mac
cd GameSpeedChanger
./gradlew assembleDebug
```

## 使用方法

### 基础使用(仅UI演示)

1. 安装APK到模拟器
2. 打开应用
3. 选择应用(仅列表展示)
4. 调整速度倍率
5. 点击"应用"

**注意**: 没有虚拟框架支持,变速不会生效。

### 完整功能使用(需要虚拟环境)

1. 安装VMOS Pro到模拟器
2. 将本APK安装到VMOS内
3. 在VMOS内打开游戏变速器
4. 选择要加速的游戏
5. 设置变速倍率
6. 启动游戏

## 进阶开发

### 实现完整变速功能

要实现真正的变速,需要修改以下文件:

1. **SpeedManager.java**: 添加LSPosed Hook逻辑
2. **VirtualSpaceHelper.java**: 集成VirtualApp启动接口
3. **build.gradle.kts**: 添加VirtualApp依赖

示例LSPosed Hook代码:

```java
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SpeedXposedModule implements IXposedHookZygoteInit {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // Hook System.currentTimeMillis()
        XposedHelpers.findAndHookMethod(System.class, "currentTimeMillis",
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    SpeedManager mgr = SpeedManager.getInstance();
                    param.setResult(mgr.getSpeedTime());
                }
            });

        // Hook System.nanoTime()
        XposedHelpers.findAndHookMethod(System.class, "nanoTime",
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    SpeedManager mgr = SpeedManager.getInstance();
                    param.setResult(mgr.getHighResTime());
                }
            });
    }
}
```

## 文件结构

```
GameSpeedChanger/
├── app/                          # 主应用模块
│   ├── src/main/
│   │   ├── java/com/speedchanger/main/
│   │   │   └── MainActivity.java # 主界面
│   │   └── res/                  # 资源文件
│   └── build.gradle.kts          # 应用构建配置
├── speed-module/                 # 变速功能模块
│   ├── src/main/java/com/speedchanger/module/
│   │   ├── SpeedManager.java     # 变速管理器
│   │   ├── SpeedHooker.java      # Java层Hook
│   │   └── VirtualSpaceHelper.java # 虚拟空间助手
│   └── build.gradle.kts
├── gradle/                       # Gradle wrapper
├── build.gradle.kts              # 项目构建配置
└── settings.gradle.kts           # Gradle设置
```

## 常见问题

### Q: 为什么变速不起作用?
A: 本APK需要虚拟框架(如VMOS、VirtualApp)或Root+LSPosed环境才能实现真正的变速。

### Q: 会被游戏检测吗?
A: 联网游戏很可能检测到变速,单机游戏通常不会。

### Q: 支持哪些模拟器?
A: 所有Android模拟器都支持,但需要安装虚拟环境。

### Q: 倍率可以更高吗?
A: 可以,修改 `SpeedManager.java` 中的上限值即可。

## 免责声明

本工具仅供学习和研究使用。使用本工具导致的任何后果(包括但不限于账号封禁、设备损坏等)由使用者自行承担,开发者不承担任何责任。

## 许可证

MIT License
