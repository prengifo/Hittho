package com.patrickrengifo.hittho;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.patrickrengifo.hittho.ui.CompanyListItem;

public class CompanyListItemTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext c = new ComponentContext(this);

        // Copy the following in MainActivity
        final LithoView lithoView = LithoView.create(
                this,
                CompanyListItem.create(c)
                        .displayName("Hitta")
                        .street("Drottninggatan 95A")
                        .city("Stockholm")
                        .build());

        setContentView(lithoView);
    }
}
