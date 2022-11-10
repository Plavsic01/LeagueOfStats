package com.example.leagueofstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;


import com.example.leagueofstats.displaySummoner.SummonerActivity;
import com.example.leagueofstats.recentlySearchedDatabase.DatabaseManager;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView sv = findViewById(R.id.searchView);
        ListView lv = findViewById(R.id.recentlySearched);

        dbManager = new DatabaseManager(this);

        try {
            dbManager.open();
        } catch (SQLDataException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String,String>> searchedSummoners = dbManager.getSummoners();


        RecentSearchSimpleAdapter searchAdapter = new RecentSearchSimpleAdapter(this,searchedSummoners,R.layout.item_listview_layout,
                new String[]{"id","summonerName"},new int[]{R.id.searchName},dbManager);



        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(searchAdapter);
                lv.setVisibility(View.VISIBLE);
            }
        });

        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                lv.setVisibility(View.GONE);
                return false;
            }
        });



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dbManager.insert(s);
                Intent i = new Intent(getApplicationContext(), SummonerActivity.class);
                i.putExtra("searchedSummoner",s);
                startActivity(i);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });



    }


}