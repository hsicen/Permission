package com.hsicen.permission.checker;

import android.content.Context;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;

/**
 * <p>作者：Hsicen  2019/9/28 21:39
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：
 */
class SipTest implements PermissionTest {

    private Context mContext;

    SipTest(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean test() throws Throwable {
        if (!SipManager.isApiSupported(mContext)) {
            return true;
        }
        SipManager manager = SipManager.newInstance(mContext);
        if (manager == null) {
            return true;
        }
        SipProfile.Builder builder = new SipProfile.Builder("Permission", "127.0.0.1");
        builder.setPassword("password");
        SipProfile profile = builder.build();
        manager.open(profile);
        manager.close(profile.getUriString());
        return true;
    }
}