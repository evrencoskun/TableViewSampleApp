package com.evrencoskun.tableviewsample2.data.network;

import android.util.Log;

import com.evrencoskun.tableviewsample2.data.database.entity.User;
import com.evrencoskun.tableviewsample2.data.network.pojo.Data;
import com.evrencoskun.tableviewsample2.model.ServiceResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL = "http://vuetable.ratiw.net/api/";

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory
                .create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static Disposable getDataFromService(int size, int page,
                                                DisposableObserver<ServiceResponse> observer) {
        Log.d(LOG_TAG, "Getting data from the server");
        try {
            RestApi service = getRetrofit().create(RestApi.class);

            Observable<ServiceResponse> observable = service.getUser(size, page);
            return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread
                    ()).subscribeWith(observer);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Getting data process has been failed. ", e);
        }
        return null;
    }


    public static List<User> convertToUserList(ServiceResponse serviceResponse) {
        List<User> users = new ArrayList<>();

        Log.d(LOG_TAG, "Converting the response.");
        try {
            for (Data data : serviceResponse.data) {
                User user = new User();
                user.id = data.id;
                user.name = data.name;
                user.nickname = data.nickname;
                user.email = data.email;
                user.birthdate = data.birthdate;
                user.gender = data.gender;
                user.salary = data.salary;
                user.job = data.group.description;
                user.created_at = getDate(data.createdAt);
                user.updated_at = getDate(data.updatedAt);
                user.age = data.age;
                user.address = data.address.line1;
                user.zipcode = data.address.zipcode;
                user.mobile = data.address.mobile;
                user.fax = data.address.fax;

                // add
                users.add(user);
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        return users;
    }

    private static Date getDate(String stringData) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        return format.parse(stringData);
    }

}
