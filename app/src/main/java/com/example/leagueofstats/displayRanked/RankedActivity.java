package com.example.leagueofstats.displayRanked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.summoner.Summoner;

public class RankedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked);

        Intent i = getIntent();
        Summoner sum = (Summoner)i.getSerializableExtra("summoner");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RankedFragment rankedFragment = RankedFragment.newInstance(sum);
        fragmentTransaction.replace(R.id.rankedFrameLayout,rankedFragment).commit();

    }

}