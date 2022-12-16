package com.example.leagueofstats.summonerDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "summoner.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "summoners";
    public static final String COLUMN_SUMMONER_ID = "summoner_id";
    public final static String COLUMN_SUMMONER_PUUID = "summoner_puuid";
    public final static String COLUMN_SUMMONER_NAME = "summoner_name";
    public final static String COLUMN_SEARCH_SUMMONER_NAME = "search_by_name";
    public final static String COLUMN_SUMMONER_LEVEL = "summoner_level";
    public final static String COLUMN_SUMMONER_ICON = "summoner_icon";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE +
            "(" + COLUMN_SUMMONER_ID + " TEXT PRIMARY KEY, " + COLUMN_SUMMONER_PUUID + " TEXT NOT NULL, " + COLUMN_SUMMONER_NAME + " TEXT NOT NULL, " + COLUMN_SEARCH_SUMMONER_NAME + " TEXT NOT NULL COLLATE NOCASE, " + COLUMN_SUMMONER_LEVEL + " INT NOT NULL, " + COLUMN_SUMMONER_ICON + " TEXT NOT NULL " + ");";

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
