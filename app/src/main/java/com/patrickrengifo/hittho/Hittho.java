package com.patrickrengifo.hittho;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.patrickrengifo.hittho.di.components.DaggerNetworkComponent;
import com.patrickrengifo.hittho.di.components.NetworkComponent;
import com.patrickrengifo.hittho.di.modules.AppModule;
import com.patrickrengifo.hittho.di.modules.NetworkModule;

/**
 * Created by Patrick Rengifo on 1/9/18.
 */

public class Hittho extends Application {

    private NetworkComponent networkModule;

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
        Iconify.with(new FontAwesomeModule());

        AppModule appModule = new AppModule(this);
        networkModule = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkModule() {
        return networkModule;
    }
}
