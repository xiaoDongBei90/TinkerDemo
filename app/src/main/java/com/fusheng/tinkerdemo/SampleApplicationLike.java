package com.fusheng.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.entry.DefaultApplicationLike;

import java.util.Locale;

/**
 * @ author  LiXiaoWei
 * @ date  2019/9/3.
 * desc:
 */
public class SampleApplicationLike extends DefaultApplicationLike {
    public SampleApplicationLike(Application application,
                                 int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime,
                                 long applicationStartMillisTime,
                                 Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //设置是否开启热更新能力，默认为true
        //Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        //Beta.canAutoDownloadPatch=true;
        // 设置是否自动合成补丁，默认为true
        //Beta.canAutoPatch=true;
        // 设置是否提示用户重启，默认为false
        //Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplication(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };

        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        // Bugly.setIsDevelopmentDevice(getApplication(), true);
        String channel = WalleChannelReader.getChannel(getApplication());
        Bugly.setAppChannel(getApplication(), channel);
        Bugly.init(getApplication(), "21d5badd98", false);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
    }

    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
  /*  @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }*/
}
