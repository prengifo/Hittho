package com.patrickrengifo.hittho;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by Patrick Rengifo on 1/9/18.
 */

public class Hittho extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
    }
}
