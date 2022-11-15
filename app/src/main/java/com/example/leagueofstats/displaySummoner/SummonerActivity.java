package com.example.leagueofstats.displaySummoner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.widget.ImageView;

import com.example.leagueofstats.MainActivity;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchCallBack;
import com.example.leagueofstats.model.match.MatchMananger;
import com.example.leagueofstats.model.ranked.RankedManager;
import com.example.leagueofstats.model.summoner.Summoner;
import com.example.leagueofstats.model.summoner.SummonerManager;
import com.example.leagueofstats.model.summoner.Summonerable;
import com.example.leagueofstats.R;

import java.util.ArrayList;

public class SummonerActivity extends AppCompatActivity implements Summonerable {

    private ImageView summonerIcon;
    private SummonerManager summonerManager = new SummonerManager(this);
    private MatchMananger matchMananger = new MatchMananger(this);

    RankedManager m = new RankedManager(this);


    private ArrayList<Match> matchArray = new ArrayList<>();

    SummonerRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);

        m.fetchRankedStats();

        summonerIcon = findViewById(R.id.summonerIcon);

        Intent i = getIntent();
        String sumName = i.getStringExtra("searchedSummoner");
        summonerManager.delegate = this;
        summonerManager.fetchSummonerInfo(sumName);

        RecyclerView recyclerView = findViewById(R.id.matchRecyclerView);
        adapter = new SummonerRecyclerViewAdapter(this,matchArray);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }



    @Override
    public void summonerData(Summoner summoner) {
        summonerManager.fetchSummonerIcon(summoner.getProfileIconId());


        matchMananger.fetchMatchIds(summoner,new MatchCallBack() {
            @Override
            public void onSuccess(Match result) {
                matchArray.add(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });

    }

    @Override
    public void summonerIcon(Bitmap icon) {
        summonerIcon.setImageBitmap(icon);
    }
}