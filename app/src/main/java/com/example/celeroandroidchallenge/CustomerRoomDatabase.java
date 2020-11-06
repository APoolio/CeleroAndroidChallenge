package com.example.celeroandroidchallenge;

/**
 *                                                                      RoomDatabase
 *
 * Room is a database layer on top of an SQLite database. Room takes care of mundane tasks that you used to handle with a database helper class such as SQLiteOpenHelper.
 **/


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.celeroandroidchallenge.DAO.CustomerDao;
import com.example.celeroandroidchallenge.Models.CustomerEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Annotating to be a RoomDatabase
//exportSchema to false since we are not keeping a history of schema versions (For migrating to databases)
@Database(entities = {CustomerEntity.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class CustomerRoomDatabase extends RoomDatabase
{
    public abstract CustomerDao customerDao();

    //Used as a singleton so there can not be multiple instances of the database opened
    private static CustomerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static CustomerRoomDatabase getDatabase(final Context context)
    {
        //If database instance is null then create it
        if (INSTANCE == null)
        {
            synchronized (CustomerRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    //Using Room's database builder to create the RoomDatabase object with the name customer_database
                    //Creating the actual database
                    //Wipes and rebuilds instead of migrating
                    //.fallbackToDestructiveMigration() - if no Migration object. Migration is not part of this practical
                    //.addCallback() adds the RoomDatabase.Callback to add a method for when the room db is built
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CustomerRoomDatabase.class, "customer_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }

        //return if created or already exists
        return INSTANCE;
    }


    //Deleting all content and repopulating the database for when the app is started
    private static CustomerRoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback()
            {

                //Creating an AsyncTask to add the content to the database because it cannot be done on the UI thread
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        //Need a Dao to delete and add customers
        private final CustomerDao mDao;

        PopulateDbAsync(CustomerRoomDatabase db)
        {
            //Initializing the Dao so we can delete the words and add new ones
            mDao = db.customerDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();
            return null;
        }
    }

}