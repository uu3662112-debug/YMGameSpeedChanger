package com.speedchanger.main;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.speedchanger.module.SpeedManager;
import com.speedchanger.module.VirtualSpaceHelper;
import com.speedchanger.module.VirtualSpaceHelper.AppInfo;
import com.hjq.permissions.XXPermissions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SpeedManager speedManager;
    private VirtualSpaceHelper virtualHelper;

    private SeekBar speedSeekBar;
    private TextView speedText;
    private EditText customSpeedEdit;
    private Button applyButton;
    private Button resetButton;
    private ListView appListView;
    private Button startAppButton;
    private Button stopAppButton;

    private List<String> appList = new ArrayList<>();
    private List<String> packageList = new ArrayList<>();
    private String selectedPackageName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化管理器
        speedManager = SpeedManager.getInstance();
        virtualHelper = new VirtualSpaceHelper(this);

        // 请求权限
        requestPermissions();

        // 初始化UI
        initUI();

        // 加载应用列表
        loadAppList();
    }

    private void requestPermissions() {
        if (!XXPermissions.isGranted(this, Manifest.permission.QUERY_ALL_PACKAGES)) {
            XXPermissions.with(this)
                    .permission(Manifest.permission.QUERY_ALL_PACKAGES)
                    .request();
        }
    }

    private void initUI() {
        // 变速滑块
        speedSeekBar = findViewById(R.id.speedSeekBar);
        speedText = findViewById(R.id.speedText);
        customSpeedEdit = findViewById(R.id.customSpeedEdit);
        applyButton = findViewById(R.id.applyButton);
        resetButton = findViewById(R.id.resetButton);
        appListView = findViewById(R.id.appListView);
        startAppButton = findViewById(R.id.startAppButton);
        stopAppButton = findViewById(R.id.stopAppButton);

        // 设置滑块范围: 0.1x - 100x, 步长0.1
        speedSeekBar.setMax(999); // 0-999代表0.1x-100.0x
        speedSeekBar.setProgress(90); // 默认1.0x

        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float speed = (progress + 1) / 10.0f;
                speedText.setText(String.format("当前速度: %.1fx", speed));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 应用按钮
        applyButton.setOnClickListener(v -> {
            float speed = Float.parseFloat(customSpeedEdit.getText().toString());
            if (speed < 0.1f) speed = 0.1f;
            if (speed > 100.0f) speed = 100.0f;
            speedManager.setSpeedMultiplier(speed);
            Toast.makeText(this, "变速已应用: " + speed + "x", Toast.LENGTH_SHORT).show();
        });

        resetButton.setOnClickListener(v -> {
            speedManager.reset();
            speedSeekBar.setProgress(90);
            customSpeedEdit.setText("1.0");
            Toast.makeText(this, "已重置速度", Toast.LENGTH_SHORT).show();
        });

        // 应用列表
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, appList);
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        appListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPackageName = packageList.get(position);
        });

        // 启动/停止应用
        startAppButton.setOnClickListener(v -> {
            if (selectedPackageName != null) {
                boolean success = virtualHelper.launchInVirtualSpace(selectedPackageName);
                if (success) {
                    Toast.makeText(this, "应用已启动", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "启动失败,需要虚拟框架支持", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请先选择应用", Toast.LENGTH_SHORT).show();
            }
        });

        stopAppButton.setOnClickListener(v -> {
            // 停止逻辑
            Toast.makeText(this, "停止功能需要虚拟框架支持", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadAppList() {
        new Thread(() -> {
            List<AppInfo> apps = virtualHelper.getInstalledApps();
            appList.clear();
            packageList.clear();

            for (AppInfo app : apps) {
                appList.add(app.appName);
                packageList.add(app.packageName);
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                ((ArrayAdapter) appListView.getAdapter()).notifyDataSetChanged();
            });
        }).start();
    }
}
