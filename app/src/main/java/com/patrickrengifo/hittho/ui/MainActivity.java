package com.patrickrengifo.hittho.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.Progress;
import com.facebook.litho.widget.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext c = new ComponentContext(this);

        final LithoView lithoView = LithoView.create(
                this,
                RecyclerCollectionComponent.create(c)
                        .disablePTR(true)
                        .loadingComponent(
                                Progress.create(c)
                                        .build())
                        .errorComponent(
                                Text.create(c)
                                        .text("Data Fetch has failed").build())
                        .emptyComponent(
                                Text.create(c)
                                        .text("No data to show").build())
                        .section(CompanyListSection.create(new SectionContext(c)).build())
                        .build());

        setContentView(lithoView);
    }
}
