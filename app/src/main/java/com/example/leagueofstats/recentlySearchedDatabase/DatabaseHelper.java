package com.example.leagueofstats.recentlySearchedDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "summoner.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "summoners";
    public static final String SUMMONER_ID = "_id";
    public final static String SUMMONER_NAME = "summoner_name";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE  +
            "( " + SUMMONER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUMMONER_NAME + " TEXT NOT NULL);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
