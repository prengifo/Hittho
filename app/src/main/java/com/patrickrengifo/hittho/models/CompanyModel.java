package com.patrickrengifo.hittho.models;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

public class CompanyModel {

    public final String displayName;
    public final AddressModel address;

    public CompanyModel(String displayName, AddressModel address) {
        this.displayName = displayName;
        this.address = address;
    }
}
