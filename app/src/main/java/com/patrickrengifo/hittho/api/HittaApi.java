package com.patrickrengifo.hittho.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.patrickrengifo.hittho.Constants.APIPath.SEARCH;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

public interface HittaApi {

    @GET(SEARCH)
    Observable<Object> getCompanies(@Query("query") String query,
                                    @Query("geo.hint") String geo,
                                    @Query("sort.order") String sortOrder,
                                    @Query("range.from") String rangeFrom,
                                    @Query("range.to") String rangeTo);
}
