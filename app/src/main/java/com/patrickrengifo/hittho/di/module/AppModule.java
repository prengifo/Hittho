package com.patrickrengifo.hittho.di.module;

import com.patrickrengifo.hittho.Hittho;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@Module
public class AppModule {

    private Hittho app;

    public AppModule(Hittho app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Hittho providesApplication() {
        return app;
    }
}
