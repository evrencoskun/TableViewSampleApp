package com.evrencoskun.tableviewsample2.data;

import androidx.lifecycle.LiveData;
import android.util.Log;

import com.evrencoskun.tableviewsample2.AppExecutors;
import com.evrencoskun.tableviewsample2.data.database.UserDao;
import com.evrencoskun.tableviewsample2.data.database.entity.User;
import com.evrencoskun.tableviewsample2.data.network.UserNetworkDataSource;
import com.evrencoskun.tableviewsample2.model.ServiceRequest;

import java.util.List;

/**
 * This class responsible for handling data operations. This is the mediator between different
 * data sources (persistent model, web service, cache, etc.)
 */
public class UserRepository {
    private static final String LOG_TAG = UserRepository.class.getSimpleName();

    private UserDao mUserDao;
    private UserNetworkDataSource mNetworkDataSource;

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static UserRepository sInstance;

    public UserRepository(UserDao userDao, UserNetworkDataSource networkDataSource, AppExecutors
            executors) {
        this.mUserDao = userDao;
        this.mNetworkDataSource = networkDataSource;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        mNetworkDataSource.getUserList().observeForever(users -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "user table is updating");
                mUserDao.updateAll(users);
            });
        });
    }

    public static UserRepository getInstance(UserDao userDao, UserNetworkDataSource
            networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new UserRepository(userDao, networkDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<User>> getUserList() {
        return mUserDao.getUserList();
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchData(serviceRequest);
    }

}
