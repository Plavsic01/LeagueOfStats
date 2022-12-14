package com.example.leagueofstats.displaySummoner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.leagueofstats.MainActivity;
import com.example.leagueofstats.model.summoner.Summoner;
import com.example.leagueofstats.model.summoner.SummonerManager;
import com.example.leagueofstats.model.summoner.Summonerable;
import com.example.leagueofstats.R;
import com.example.leagueofstats.summonerDatabase.DatabaseHelper;
import com.example.leagueofstats.summonerDatabase.SummonerLayerDB;


public class SummonerActivity extends AppCompatActivity implements Summonerable {

    SummonerManager summonerManager = new SummonerManager(this);
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

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
        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
        databaseHelper.close();
        finish();
    }

    @Override
    public void onCompleteSummonerData(Summoner summoner) {
        SummonerLayerDB summonerLayerDB = new SummonerLayerDB(databaseHelper);

        if(summoner == null){ // ako ne postoji summoner kada aplikacija radi u offline rezimu
            Summoner noSum = new Summoner("0","0","No Information","0","0");
            summoner = noSum;
        }else {
            boolean isAdded = summonerLayerDB.addSummoner(summoner);

            if(!isAdded){
                summonerLayerDB.updateSummoner(summoner);
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SummonerFragment summonerFragment = SummonerFragment.newInstance(summoner);
        MatchFragment matchRecyclerFragment = MatchFragment.newInstance(summoner);
        fragmentTransaction.replace(R.id.summonerContainer,summonerFragment);
        fragmentTransaction.replace(R.id.matchContainer,matchRecyclerFragment)
                .commit();

    }

    @Override
    public void onErrorSummonerData(VolleyError error) {
        int statusCode = error.networkResponse.statusCode;
        if(statusCode == 404){
            Toast.makeText(this,"Summoner does not exist!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error " + statusCode, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
