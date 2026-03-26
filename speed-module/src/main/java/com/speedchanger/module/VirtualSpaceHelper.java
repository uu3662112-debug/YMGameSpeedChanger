package com.speedchanger.module;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟空间助手 - 用于在虚拟环境中启动应用
 */
public class VirtualSpaceHelper {
    private Context context;

    public VirtualSpaceHelper(Context context) {
        this.context = context;
    }

    /**
     * 获取已安装应用列表
     */
    public List<AppInfo> getInstalledApps() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                // 只显示非系统应用
                AppInfo app = new AppInfo();
                app.packageName = packageInfo.packageName;
                app.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                try {
                    app.icon = packageInfo.applicationInfo.loadIcon(pm);
                } catch (Exception e) {
                    app.icon = null;
                }
                apps.add(app);
            }
        }
        return apps;
    }

    /**
     * 启动应用到虚拟空间
     * 注意: 这需要VirtualApp或类似框架支持
     */
    public boolean launchInVirtualSpace(String packageName) {
        // 这里应该调用VirtualApp的启动接口
        // 由于不引入外部依赖,这里提供接口定义

        // 示例代码:
        // Intent intent = pm.getLaunchIntentForPackage(packageName);
        // if (intent != null) {
        //     VirtualCore.get().launchApp(packageName, 0);
        //     return true;
        // }

        return false;
    }

    /**
     * 应用信息类
     */
    public static class AppInfo {
        public String appName;
        public String packageName;
        public Drawable icon;
    }
}
