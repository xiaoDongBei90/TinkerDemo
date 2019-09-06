package com.fusheng.tinkerdemo;

import android.util.Log;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @ author  LiXiaoWei
 * @ date  2019/9/3.
 * desc:
 */
public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, SampleApplicationLike.class.getName(), "com.tencent.tinker.loader.TinkerLoader", false);
        Log.d(SampleApplication.class.getSimpleName(), SampleApplicationLike.class.getName());
    }
}
