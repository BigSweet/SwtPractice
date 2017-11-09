package cn.swt.m.firstcode;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/8/25
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Fresco.initialize(this);
    }
}
