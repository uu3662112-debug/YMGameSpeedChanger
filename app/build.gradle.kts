plugins {
    id 'com.android.application'
}

android {
    namespace 'com.speedchanger.main'
    compileSdk 34

    defaultConfig {
        applicationId 'com.speedchanger.main'
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName '1.0'
        applicationVariants.all {
            val variant = this
            variant.outputs
                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    val outputFileName = "映梦业绩王-v${versionName}-${variant.buildType.name}.apk"
                    output.outputFileName = outputFileName
                }
        }
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding true
    }
}

repositories {
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/public' }
    maven { url 'https://maven.aliyun.com/repository/central' }
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation project(':speed-module')
    implementation 'com.github.getActivity:XXPermissions:18.5'
}
