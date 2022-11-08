package com.example.leagueofstats.recentlySearchedDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;


    public DatabaseManager(Context context){
        this.context = context;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String summonerName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUMMONER_NAME,summonerName);
        database.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
    }

    public ArrayList<HashMap<String,String>> getSummoners(){
        ArrayList<HashMap<String,String>> summoners = new ArrayList<>();
        String query = "SELECT " +  DatabaseHelper.SUMMONER_ID + "," + DatabaseHelper.SUMMONER_NAME + " FROM " + DatabaseHelper.DATABASE_TABLE + " ORDER BY " + DatabaseHelper.SUMMONER_ID + " DESC " + ";";
        Cursor cursor = database.rawQuery(query,null);
        while(cursor.moveToNext()){
            HashMap<String,String> summoner = new HashMap<>();
            summoner.put("id",cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUMMONER_ID)));
            summoner.put("summonerName",cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUMMONER_NAME)));
            summoners.add(summoner);
        }

        return summoners;
    }

    public void delete(long id){
//        database.delete(DatabaseHelper.DATABASE_TABLE,DatabaseHelper.SUMMONER_ID+"=?",new String[]{String.valueOf(id)});
        database.execSQL(" DELETE FROM " + DatabaseHelper.DATABASE_TABLE + " WHERE " + DatabaseHelper.SUMMONER_ID + "=\"" + id + "\";");

    }

}
