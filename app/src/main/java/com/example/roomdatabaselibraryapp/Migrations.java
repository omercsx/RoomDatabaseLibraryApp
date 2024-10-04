package com.example.roomdatabaselibraryapp;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Movie ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0");
        }
    };
    // ... other migrations if needed

    public static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Movie ADD COLUMN director TEXT");
        }
    };
}
