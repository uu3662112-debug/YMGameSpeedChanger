# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep SpeedManager class
-keep class com.speedchanger.module.SpeedManager { *; }
-keep class com.speedchanger.module.SpeedHooker { *; }
-keep class com.speedchanger.module.VirtualSpaceHelper { *; }
-keep class com.speedchanger.module.VirtualSpaceHelper$AppInfo { *; }

# Keep MainActivity
-keep class com.speedchanger.main.MainActivity { *; }
