@echo off
chcp 65001 > nul
echo ========================================
echo    映梦业绩王 APK 构建脚本
echo ========================================
echo.

REM 检查环境
where java >nul 2>&1
if %errorlevel% neq 0 (
    echo [错误] 未找到 Java JDK
    echo 请安装 JDK 17 或更高版本
    pause
    exit /b 1
)

echo [1/4] 检查 Gradle 环境中...
if not exist gradlew.bat (
    echo [错误] 未找到 gradlew.bat
    echo 请确保在项目根目录运行此脚本
    pause
    exit /b 1
)

echo [√] 环境检查通过
echo.

echo [2/4] 开始构建 Debug APK...
call gradlew.bat assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo [错误] 构建失败
    echo.
    echo 可能的原因:
    echo 1. 未安装 Android SDK
    echo 2. local.properties 未配置 SDK 路径
    echo 3. 网络问题导致依赖下载失败
    echo.
    pause
    exit /b 1
)

echo.
echo [√] 构建成功
echo.

echo [3/4] 复制 APK 到输出目录...

if not exist "output" mkdir "output"
copy "app\build\outputs\apk\debug\映梦业绩王-v1.0-debug.apk" "output\映梦业绩王-v1.0-debug.apk" > nul

echo.
echo [√] APK 已复制
echo.

echo [4/4] 显示文件信息...
dir "output\映梦业绩王-v1.0-debug.apk"

echo.
echo ========================================
echo    构建完成!
echo ========================================
echo.
echo APK 位置: output\映梦业绩王-v1.0-debug.apk
echo.
echo 使用说明:
echo 1. 将 APK 安装到模拟器
echo 2. 如需完整变速功能,请安装虚拟环境 (VMOS等)
echo 3. 打开应用,选择要加速的游戏
echo 4. 设置变速倍率 (0.1x - 100.0x)
echo.
echo 按任意键退出...
pause > nul
