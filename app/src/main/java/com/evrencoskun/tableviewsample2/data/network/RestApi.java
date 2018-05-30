package com.evrencoskun.tableviewsample2.data.network;

import com.evrencoskun.tableviewsample2.model.ServiceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("users")
    Observable<ServiceResponse> getUser(@Query("per_page") int size, @Query("current_page") int page);
}
