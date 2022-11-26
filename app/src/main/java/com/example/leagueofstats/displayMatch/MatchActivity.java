package com.example.leagueofstats.displayMatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import com.example.leagueofstats.R;
import com.example.leagueofstats.displayRanked.RankedFragment;
import com.example.leagueofstats.model.match.Match;

public class MatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent i = getIntent();
        Match currentMatch = (Match) i.getSerializableExtra("match");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BlueTeamFragment blueTeamFragment = BlueTeamFragment.newInstance(currentMatch);
        fragmentTransaction.replace(R.id.matchDetailsContainer,blueTeamFragment).commit();

    }
}