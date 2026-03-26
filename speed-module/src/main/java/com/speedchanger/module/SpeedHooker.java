package com.speedchanger.module;

import java.lang.reflect.Method;

/**
 * Java层时间Hook实现
 * 通过反射和动态代理实现时间函数Hook
 */
public class SpeedHooker {
    private static boolean isHooked = false;

    /**
     * Hook Java时间函数
     */
    public static void hookJavaTime() {
        if (isHooked) return;

        try {
            // Hook System.currentTimeMillis()
            hookSystemCurrentTimeMillis();

            // Hook System.nanoTime()
            hookSystemNanoTime();

            // Hook Android-specific time functions
            hookSystemClock();

            isHooked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hook System.currentTimeMillis()
     */
    private static void hookSystemCurrentTimeMillis() {
        // 注意: Java反射无法直接修改系统方法
        // 这里提供接口,实际Hook需要使用LSPosed等框架
        // 或者使用VirtualApp + DroidPlugin等技术
    }

    /**
     * Hook System.nanoTime()
     */
    private static void hookSystemNanoTime() {
        // 同样需要Hook框架支持
    }

    /**
     * Hook SystemClock类
     */
    private static void hookSystemClock() {
        try {
            Class<?> systemClockClass = Class.forName("android.os.SystemClock");

            // Hook elapsedRealtime()
            Method elapsedRealtime = systemClockClass.getDeclaredMethod("elapsedRealtime");

            // Hook uptimeMillis()
            Method uptimeMillis = systemClockClass.getDeclaredMethod("uptimeMillis");

            // 实际Hook需要LSPosed/Xposed框架

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否已Hook
     */
    public static boolean isHooked() {
        return isHooked;
    }

    /**
     * 移除Hook
     */
    public static void unhook() {
        isHooked = false;
        SpeedManager.getInstance().reset();
    }
}
