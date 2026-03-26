# 构建映梦业绩王 APK

## 方法1: 使用构建脚本(推荐)

1. 双击运行 `build-apk.bat`
2. 等待构建完成(首次需要下载依赖,约5-10分钟)
3. APK位置: `output/映梦业绩王-v1.0-debug.apk`

## 方法2: 使用Android Studio

1. 用Android Studio打开 `GameSpeedChanger` 项目
2. 等待Gradle同步完成
3. 菜单: `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
4. APK位置: `app/build/outputs/apk/debug/映梦业绩王-v1.0-debug.apk`

## 方法3: 命令行构建

```bash
cd c:/Users/Administrator/CodeBuddy/20260317164357/GameSpeedChanger
gradlew.bat assembleDebug
```

## 环境要求

- **JDK 17** 或更高版本
- **Android SDK** (API 34)
- **Android Studio** (推荐,用于配置SDK路径)

## 如果构建失败

### 1. local.properties 未配置SDK路径

编辑 `local.properties` 文件,添加:
```
sdk.dir=C\:\\Users\\Administrator\\AppData\\Local\\Android\\Sdk
```

将路径修改为你实际的Android SDK位置。

### 2. 依赖下载失败

检查网络连接,或配置国内镜像源:
- 在 `build.gradle.kts` 中添加阿里云镜像
- 或使用VPN

### 3. 找不到Java

安装JDK 17并配置环境变量 `JAVA_HOME`

## 安装到模拟器

```bash
# 安装APK
adb install output/映梦业绩王-v1.0-debug.apk

# 或直接拖拽APK到模拟器窗口
```

## 使用说明

1. 打开"映梦业绩王"应用
2. 选择要加速的游戏
3. 设置变速倍率 (0.1x - 100.0x)
4. 点击"应用"
5. 启动游戏

**注意**: 完整变速功能需要虚拟环境(VMOS Pro等)支持
