package com.patrickrengifo.hittho.ui;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaJustify;
import com.patrickrengifo.hittho.R;

import static com.facebook.yoga.YogaEdge.ALL;
import static com.facebook.yoga.YogaEdge.LEFT;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@LayoutSpec
public class CompanyListItemSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop String displayName,
                                          @Prop String street,
                                          @Prop String city) {

        ComponentLayout column = Column.create(c)
                .paddingDip(LEFT, 16)
                .justifyContent(YogaJustify.CENTER)
                .child(
                        Text.create(c)
                                .text(displayName)
                                .textSizeSp(18)
                                .build())
                .child(
                        Text.create(c)
                                .text(street + ", " + city)
                                .textSizeSp(16)
                                .build())
                .build();

        Component image = Image.create(c)
                // Temporal icon
                .drawableRes(R.drawable.ic_launcher_foreground)
                .widthDip(80)
                .heightDip(80)
                .build();

        return Row.create(c)
                .backgroundAttr(android.R.attr.selectableItemBackground)
                .paddingDip(ALL, 16)
                .child(image)
                .child(column)
                .build();
    }
}
