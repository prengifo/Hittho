package com.patrickrengifo.hittho.di.components;

import com.patrickrengifo.hittho.api.HittaApi;
import com.patrickrengifo.hittho.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    HittaApi hittaApi();
}
