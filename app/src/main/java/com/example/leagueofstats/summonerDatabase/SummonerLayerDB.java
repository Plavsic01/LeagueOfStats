package com.example.leagueofstats.summonerDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leagueofstats.model.summoner.Summoner;

public class SummonerLayerDB {

    DatabaseHelper database;

    public SummonerLayerDB(DatabaseHelper database) {
        this.database = database;
    }

    public boolean addSummoner(Summoner summoner) {
        SQLiteDatabase db = database.getWritableDatabase();
        if(getSummoner(summoner.getName()) == null){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COLUMN_SUMMONER_ID, summoner.getId());
            contentValues.put(DatabaseHelper.COLUMN_SUMMONER_PUUID, summoner.getPuuid());
            contentValues.put(DatabaseHelper.COLUMN_SUMMONER_NAME, summoner.getName());
            contentValues.put(DatabaseHelper.COLUMN_SEARCH_SUMMONER_NAME, summoner.getName().replace(" ",""));
            contentValues.put(DatabaseHelper.COLUMN_SUMMONER_LEVEL, summoner.getSummonerLevel());
            contentValues.put(DatabaseHelper.COLUMN_SUMMONER_ICON, summoner.getProfileIconId());

            long inserted = db.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);

            if (inserted == -1) {
                return false;
            }
            return true;
        }

        return false;
    }

    public Summoner getSummoner(String name) {
        Summoner summoner = null;
        String summonerName = name.replace(" ",""); // ime mora biti spojeno jer je COLUMN_SEARCH_SUMMONER_NAME uvek spojen tekst
        String[] sumNameSearch = {summonerName};

        String query = "SELECT * FROM " + DatabaseHelper.DATABASE_TABLE + " WHERE " + DatabaseHelper.COLUMN_SEARCH_SUMMONER_NAME + " = ?";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, sumNameSearch);

        if(cursor.moveToFirst()){
            String sumId = cursor.getString(0);
            String sumPuuid = cursor.getString(1);
            String sumName = cursor.getString(2);
            String sumLevel = cursor.getString(4);
            String sumIconId = cursor.getString(5);
            summoner = new Summoner(sumPuuid,sumId, sumName,sumLevel,sumIconId);
        }

        cursor.close();
        return summoner;
    }

    public void updateSummoner(Summoner summoner){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_SUMMONER_LEVEL, summoner.getSummonerLevel());
        contentValues.put(DatabaseHelper.COLUMN_SUMMONER_ICON, summoner.getProfileIconId());

        db.update(DatabaseHelper.DATABASE_TABLE,contentValues,DatabaseHelper.COLUMN_SUMMONER_ID + " = ?",new String[]{summoner.getId()});

    }

}
