package com.fusheng.tinkerdemo;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @ author  LiXiaoWei
 * @ date  2019/7/18.
 * desc:
 */

public class DemoApplication extends TinkerApplication {

    protected DemoApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.fusheng.tinkerdemo.DemoApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
