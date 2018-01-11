package com.patrickrengifo.hittho.models;

import java.util.List;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

public class CompanyModel {

    public final String displayName;
    public final List<AddressModel> address;

    public CompanyModel(String displayName, List<AddressModel> address) {
        this.displayName = displayName;
        this.address = address;
    }
}
