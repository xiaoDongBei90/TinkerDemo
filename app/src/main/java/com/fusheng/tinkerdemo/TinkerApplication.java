package com.fusheng.tinkerdemo;

import android.app.Application;

import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * @ author  LiXiaoWei
 * @ date  2019/7/8.
 * desc:
 */

public class TinkerApplication extends Application {

    private static TinkerApplication instance;
    private ApplicationLike tinkerPatchApplicationLike;

    public static TinkerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initTinker();
    }

    private void initTinker() {
        tinkerPatchApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
        TinkerPatch.init(tinkerPatchApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
    }
}
