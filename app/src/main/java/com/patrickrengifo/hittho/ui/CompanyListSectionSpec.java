package com.patrickrengifo.hittho.ui;

import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import com.patrickrengifo.hittho.Hittho;
import com.patrickrengifo.hittho.api.HittaApi;
import com.patrickrengifo.hittho.models.CompanyModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@GroupSectionSpec
public class CompanyListSectionSpec {

    private static List<CompanyModel> companies = new ArrayList<>();
    static HittaApi hittaApi;

    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c) {
        hittaApi = ((Hittho) c.getApplicationContext()).getNetworkModule().hittaApi();
        getData();
        return Children.create()
                .child(
                        DataDiffSection.<CompanyModel>create(c)
                                .data(companies)
                                .renderEventHandler(CompanyListSection.onRender(c)))
                .build();
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo onRender(final SectionContext c, @FromEvent CompanyModel model) {
        return ComponentRenderInfo.create()
                .component(
                        CompanyListItem.create(c)
                                .displayName(model.displayName)
                                .street(model.address.get(0).street)
                                .city(model.address.get(0).city)
                                .build())
                .build();
    }

    private static void getData() {
        hittaApi.getCompanies("ica", "57.75072152:11.81813876", "relevance", "1", "51")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> companies.addAll(results.result.companies.company));
    }
}
