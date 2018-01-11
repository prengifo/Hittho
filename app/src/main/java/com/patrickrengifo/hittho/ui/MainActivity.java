package com.patrickrengifo.hittho.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.Progress;
import com.facebook.yoga.YogaAlign;
import com.patrickrengifo.hittho.R;

import static com.facebook.yoga.YogaEdge.ALL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext c = new ComponentContext(this);

        final LithoView lithoView = LithoView.create(
                this,
                RecyclerCollectionComponent.create(c)
                        .loadingComponent(
                                Progress.create(c)
                                        .alignSelf(YogaAlign.CENTER)
                                        .colorRes(R.color.colorAccent)
                                        .marginDip(ALL, 140)
                                        .build()
                        )
                        .section(CompanyListSection.create(new SectionContext(c))
                                .build())
                        .build());

        setContentView(lithoView);
    }
}
