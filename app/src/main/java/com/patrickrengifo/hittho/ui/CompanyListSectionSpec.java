package com.patrickrengifo.hittho.ui;

import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.State;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.LoadingEvent;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.SectionLifecycle;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.annotations.OnCreateService;
import com.facebook.litho.sections.annotations.OnRefresh;
import com.facebook.litho.sections.annotations.OnViewportChanged;
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

    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c,
                                     @State List<CompanyModel> companies) {
        return Children.create()
                .child(
                        DataDiffSection.<CompanyModel>create(c)
                                .data(companies)
                                .renderEventHandler(CompanyListSection.onRender(c)))
                .build();
    }

    @OnCreateInitialState
    static void createInitialState(
            SectionContext c,
            StateValue<List<CompanyModel>> companies,
            StateValue<Integer> start,
            StateValue<Integer> count) {

        start.set(1);
        count.set(51);
        HittaApi service =  ((Hittho) c.getApplicationContext()).getNetworkModule().hittaApi();
        service.getCompanies("ica", "57.75072152:11.81813876", "relevance",
                String.valueOf(start.get()), String.valueOf(count.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    CompanyListSection.updateCompaniesParam(c, results.result.companies.company);
                    SectionLifecycle.dispatchLoadingEvent(c, false, LoadingEvent.LoadingState.SUCCEEDED, null);
                });
    }

    @OnCreateService
    static HittaApi onCreateService(
            final SectionContext c,
            @State List<CompanyModel> companies,
            @State int start,
            @State int count
    ) {
        return ((Hittho) c.getApplicationContext()).getNetworkModule().hittaApi();
    }

    @OnRefresh
    static void onRefresh(
            SectionContext c,
            HittaApi service,
            @State List<CompanyModel> companies,
            @State int start,
            @State int count
    ) {
        CompanyListSection.updateStartParam(c, 1);
        service.getCompanies("ica", "57.75072152:11.81813876", "relevance",
                String.valueOf(1), String.valueOf(51))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    SectionLifecycle.dispatchLoadingEvent(c, false, LoadingEvent.LoadingState.SUCCEEDED, null);
                    CompanyListSection.updateCompaniesParam(c, results.result.companies.company);
                });
    }

    @OnUpdateState
    static void updateStartParam(final StateValue<Integer> start, @Param int newStart) {
        start.set(newStart);
    }

    @OnViewportChanged
    static void onViewportChanged(
            SectionContext c,
            int firstVisiblePosition,
            int lastVisiblePosition,
            int firstFullyVisibleIndex,
            int lastFullyVisibleIndex,
            int totalCount,
            HittaApi service,
            @State List<CompanyModel> companies,
            @State int start,
            @State int count
    ) {
        if (totalCount == companies.size() - 1) {
            CompanyListSection.updateStartParam(c, companies.size());
            service.getCompanies("ica", "57.75072152:11.81813876", "relevance",
                    String.valueOf(companies.size() + 1), String.valueOf(totalCount + 52))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(results -> CompanyListSection.updateCompaniesParam(c, results.result.companies.company));
        }
    }


    @OnUpdateState
    static void updateCompaniesParam(StateValue<List<CompanyModel>> companies, StateValue<Integer> start, @Param List<CompanyModel> data) {
        if (start.get() == 1) {
            companies.set(data);
        } else {
            List<CompanyModel> feeds1 = new ArrayList<>();
            feeds1.addAll(companies.get());
            feeds1.addAll(data);
            companies.set(feeds1);
        }
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
}
