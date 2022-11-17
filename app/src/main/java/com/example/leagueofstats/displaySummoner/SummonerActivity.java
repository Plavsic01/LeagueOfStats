package com.example.leagueofstats.displaySummoner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.leagueofstats.MainActivity;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchCallBack;
import com.example.leagueofstats.model.match.MatchMananger;
import com.example.leagueofstats.model.summoner.Summoner;
import com.example.leagueofstats.model.summoner.SummonerManager;
import com.example.leagueofstats.model.summoner.Summonerable;
import com.example.leagueofstats.R;

import java.util.ArrayList;

public class SummonerActivity extends AppCompatActivity implements Summonerable {

    private SummonerManager summonerManager = new SummonerManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);

        Intent i = getIntent();
        String sumName = i.getStringExtra("searchedSummoner");
        summonerManager.delegate = this;
        summonerManager.fetchSummonerInfo(sumName);
    }

    @Override
    public void onBackPressed() {
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }


    @Override
    public void summonerData(Summoner summoner) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SummonerFragment summonerFragment = SummonerFragment.newInstance(summoner);
        MatchRecyclerFragment matchRecyclerFragment = MatchRecyclerFragment.newInstance(summoner);
        fragmentTransaction.replace(R.id.summonerContainer,summonerFragment);
        fragmentTransaction.replace(R.id.matchContainer,matchRecyclerFragment)
                .commit();


    }
}

//        rankedInfo.setVisibility(View.VISIBLE);
//        rankedInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(SummonerActivity.this,RankedActivity.class);
//                i.putExtra("summoner",summoner);
//                startActivity(i);
//            }
//        });