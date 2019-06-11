package com.evrencoskun.tableviewsample2.data.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Log;

import com.evrencoskun.tableviewsample2.AppExecutors;
import com.evrencoskun.tableviewsample2.data.database.entity.User;
import com.evrencoskun.tableviewsample2.model.ServiceRequest;
import com.evrencoskun.tableviewsample2.model.ServiceResponse;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class UserNetworkDataSource {

    private static final String LOG_TAG = UserNetworkDataSource.class.getSimpleName();

    // For Singleton instantiation
    private static UserNetworkDataSource sInstance;
    private static final Object LOCK = new Object();

    private AppExecutors mAppExecutors;
    private final MutableLiveData<List<User>> mDownloadedData;

    public UserNetworkDataSource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
        this.mDownloadedData = new MutableLiveData<>();
    }

    public static UserNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new UserNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public void fetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                NetworkUtils.getDataFromService(serviceRequest.getSize(), serviceRequest.getPage(), new
                        DisposableObserver<ServiceResponse>() {

                    @Override
                    public void onNext(ServiceResponse serviceResponse) {
                        setUserList(NetworkUtils.convertToUserList(serviceResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG_TAG, "Getting data process has been failed.", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }


    private void setUserList(List<User> userList){
        mDownloadedData.postValue(userList);
    }

    public LiveData<List<User>> getUserList(){
        return mDownloadedData;
    }

}
