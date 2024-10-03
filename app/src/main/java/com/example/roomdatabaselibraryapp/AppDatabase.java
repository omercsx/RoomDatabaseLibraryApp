package com.example.roomdatabaselibraryapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// This is the main database class for Room Database.
@Database(entities = { Movie.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    // This abstract method will allow Room to generate the implementation for you.
    public abstract MovieDAO movieDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



}
