package com.patrickrengifo.hittho.ui;

import android.text.TextUtils;

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
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.patrickrengifo.hittho.R;

import static com.facebook.yoga.YogaAlign.CENTER;
import static com.facebook.yoga.YogaEdge.BOTTOM;
import static com.facebook.yoga.YogaEdge.END;
import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.TOP;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@LayoutSpec
class CompanyListItemSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop String displayName,
                                          @Prop String street,
                                          @Prop String city) {

        ComponentLayout column = Column.create(c)
                .flexShrink(1.0f)
                .flexGrow(1.0f)
                .marginDip(LEFT, 16)
                .marginDip(END, 16)
                .justifyContent(YogaJustify.CENTER)
                .child(
                        Text.create(c)
                                .isSingleLine(true)
                                .ellipsize(TextUtils.TruncateAt.END)
                                .text(displayName)
                                .textSizeSp(16)
                                .build())
                .child(
                        Text.create(c)
                                .isSingleLine(true)
                                .ellipsize(TextUtils.TruncateAt.END)
                                .text(street + ", " + city)
                                .textSizeSp(14)
                                .build())
                .build();

        Component image = Image.create(c)
                .drawable(
                        new IconDrawable(c, FontAwesomeIcons.fa_building)
                                .color(R.color.colorAccent)
                )
                .marginDip(LEFT, 16)
                .widthDip(32)
                .heightDip(32)
                .build();

        return Row.create(c)
                .heightDip(72)
                .alignItems(CENTER)
                .paddingDip(TOP, 8)
                .paddingDip(BOTTOM, 8)
                .child(image)
                .child(column)
                .build();
    }
}
