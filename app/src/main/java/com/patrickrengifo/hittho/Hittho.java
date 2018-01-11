package com.patrickrengifo.hittho;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.patrickrengifo.hittho.di.module.AppModule;

/**
 * Created by Patrick Rengifo on 1/9/18.
 */

public class Hittho extends Application {

    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);

        appModule = new AppModule(this);
    }
}
