package com.speedchanger.module;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 变速管理器 - 核心变速逻辑
 */
public class SpeedManager {
    private static SpeedManager instance;
    private float speedMultiplier = 1.0f;
    private long baseTime;
    private long elapsedTime = 0;
    private long lastUpdate = 0;

    private SpeedManager() {
        baseTime = System.currentTimeMillis();
        lastUpdate = System.currentTimeMillis();
    }

    public static SpeedManager getInstance() {
        if (instance == null) {
            instance = new SpeedManager();
        }
        return instance;
    }

    /**
     * 设置变速倍率
     * @param multiplier 倍率 (0.1 - 100.0)
     */
    public void setSpeedMultiplier(float multiplier) {
        if (multiplier < 0.1f) multiplier = 0.1f;
        if (multiplier > 100.0f) multiplier = 100.0f;
        this.speedMultiplier = multiplier;
    }

    /**
     * 获取当前变速倍率
     */
    public float getSpeedMultiplier() {
        return speedMultiplier;
    }

    /**
     * 计算变速后的时间
     */
    public long getSpeedTime() {
        long current = System.currentTimeMillis();
        long realElapsed = current - lastUpdate;
        lastUpdate = current;

        // 累加经过的时间(乘以倍率)
        elapsedTime += (long) (realElapsed * speedMultiplier);

        return baseTime + elapsedTime;
    }

    /**
     * 获取高精度时间
     */
    public long getHighResTime() {
        return getSpeedTime() * 1000; // 微秒
    }

    /**
     * 重置变速
     */
    public void reset() {
        speedMultiplier = 1.0f;
        baseTime = System.currentTimeMillis();
        elapsedTime = 0;
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Hook系统时间函数
     */
    public native void hookTimeFunctions();

    /**
     * 加载native库
     */
    static {
        try {
            System.loadLibrary("speedhook");
        } catch (UnsatisfiedLinkError e) {
            // Native库不存在,使用Java层实现
        }
    }
}
