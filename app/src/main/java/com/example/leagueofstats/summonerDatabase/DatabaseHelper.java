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

//    public boolean addSummoner(Summoner summoner) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        if(getSummoner(summoner.getName()) == null){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(COLUMN_SUMMONER_ID, summoner.getId());
//            contentValues.put(COLUMN_SUMMONER_PUUID, summoner.getPuuid());
//            contentValues.put(COLUMN_SUMMONER_NAME, summoner.getName());
//            contentValues.put(COLUMN_SEARCH_SUMMONER_NAME, summoner.getName().replace(" ",""));
//            contentValues.put(COLUMN_SUMMONER_LEVEL, summoner.getSummonerLevel());
//            contentValues.put(COLUMN_SUMMONER_ICON, summoner.getProfileIconId());
//
//            long inserted = db.insert(DATABASE_TABLE, null, contentValues);
//
//            if (inserted == -1) {
//                return false;
//            }
//            return true;
//        }
//
////        db.close();
//        return false;
//    }
//
//    public Summoner getSummoner(String summonerName) {
//        Summoner summoner = null;
//        String[] sumNameSearch = {summonerName};
//        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + COLUMN_SEARCH_SUMMONER_NAME + " = ?";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, sumNameSearch);
//
//        if(cursor.moveToFirst()){
//            String sumId = cursor.getString(0);
//            String sumPuuid = cursor.getString(1);
//            String sumName = cursor.getString(2);
//            String sumLevel = cursor.getString(4);
//            String sumIconId = cursor.getString(5);
//
//           summoner = new Summoner(sumPuuid,sumId, sumName,sumLevel,sumIconId);
//        }
//
//        cursor.close();
//        return summoner;
//    }
//
//    public void updateSummoner(Summoner summoner){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_SUMMONER_LEVEL, summoner.getSummonerLevel());
//        contentValues.put(COLUMN_SUMMONER_ICON, summoner.getProfileIconId());
//
//        db.update(DATABASE_TABLE,contentValues,COLUMN_SUMMONER_ID + " = ?",new String[]{summoner.getId()});
////        db.close();
//    }
}
