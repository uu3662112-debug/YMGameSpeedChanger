# SDK下载失败解决方案

## 错误信息
```
java.io.EOFException: Unexpected end of ZLIB input stream
Warning: An error occurred while preparing SDK package Google Play Intel x86_64 Atom System Image
```

## 原因
- 网络中断导致下载文件损坏
- ZLIB压缩包不完整
- Google服务器不稳定

---

## 解决方案

### 方案1: 跳过可选组件(推荐)

`Google Play Intel x86_64 Atom System Image` 是可选组件,不影响编译!

**操作步骤:**
1. 在SDK Manager中
2. 取消勾选 `Google Play Intel x86_64 Atom System Image`
3. 只勾选必需组件:
   - ✅ Android SDK Platform 34
   - ✅ Android SDK Build-Tools 34.0.0
   - ✅ Android Emulator (可选)
4. 点击 `Apply`

### 方案2: 手动清理缓存重试

```bash
# 删除损坏的缓存文件
cd C:\Users\Administrator\AppData\Local\Android\Sdk
del /s /q *.zip
del /s /q *.tar.gz

# 然后在Android Studio中重新下载
```

### 方案3: 使用离线安装

**下载地址(国内镜像):**
- 清华大学: https://mirrors.tuna.tsinghua.edu.cn/android/repository/
- 腾讯云: https://mirrors.cloud.tencent.com/AndroidSDK/

**手动下载需要的组件:**
1. `platform-34_r06.zip`
2. `build-tools_r34.0.0-windows.zip`
3. 解压到 `C:\Users\Administrator\AppData\Local\Android\Sdk`

### 方案4: 配置更稳定的镜像

编辑 `%USERPROFILE%\.gradle\init.gradle`:
```gradle
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/google' }
        maven { url 'https://maven.aliyun.com/repository/public' }
        google()
        mavenCentral()
    }
}
```

---

## 必需组件 vs 可选组件

### 必需组件(必须下载)
- ✅ **Android SDK Platform 34**: Android平台API
- ✅ **Android SDK Build-Tools 34.0.0**: 编译工具
- ✅ **Android SDK Platform-Tools**: adb等工具
- ✅ **Android SDK Tools**: SDK管理工具

### 可选组件(可以跳过)
- ⭕ **Google Play Intel x86_64 Atom System Image**: Google Play版系统镜像(可以下载普通版替代)
- ⭕ **Android Emulator**: 模拟器(如果用其他模拟器可以跳过)
- ⭕ **Documentation**: 文档
- ⭕ **Samples**: 示例代码

---

## 快速解决(三步)

### 第1步: 关闭Android Studio

### 第2步: 删除损坏的下载
```bash
rd /s /q "%LOCALAPPDATA%\Android\Sdk\.download"
```

### 第3步: 重新打开Android Studio
- 在SDK Manager中只勾选必需组件
- 取消勾选所有可选组件
- 点击 `Apply`

---

## 如果问题依然存在

### 方法A: 使用完整SDK离线包

1. 从国内网站下载完整SDK压缩包
2. 解压到 `C:\Users\Administrator\AppData\Local\Android\Sdk`
3. 重新打开Android Studio

**下载地址:**
- http://www.androiddevtools.cn/
- https://www.android-studio.org/

### 方法B: 使用云构建

如果本地环境问题太多:
- GitHub Codespaces
- Gitpod
- 在线IDE

---

## 当前建议

**最快方案:**
1. 在SDK Manager中取消勾选 `Google Play Intel x86_64 Atom System Image`
2. 只下载必需组件
3. 立即继续编译

**如果需要Google Play组件:**
- 以后可以单独下载
- 或使用离线包
