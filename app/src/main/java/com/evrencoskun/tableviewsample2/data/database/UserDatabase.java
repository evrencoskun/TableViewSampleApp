package com.evrencoskun.tableviewsample2.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.evrencoskun.tableviewsample2.data.database.entity.User;

@Database(entities = {User.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class UserDatabase extends RoomDatabase {

    private static final String LOG_TAG = UserDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "user_table";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static UserDatabase mInstance;

    public static UserDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting " + DATABASE_NAME + " database");

        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME).build();
                Log.d(LOG_TAG, DATABASE_NAME + " database has been created.");
            }
        }
        return mInstance;
    }

    // The associated DAOs for the database
    public abstract UserDao userDao();

}
