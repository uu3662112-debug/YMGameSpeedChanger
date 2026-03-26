plugins {
    id 'com.android.library'
}

android {
    namespace 'com.speedchanger.module'
    compileSdk 34

    defaultConfig {
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName '1.0'
    }

    buildTypes {
        release {
            minifyEnabled false
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
}

repositories {
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/public' }
    maven { url 'https://maven.aliyun.com/repository/central' }
    google()
    mavenCentral()
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
}
