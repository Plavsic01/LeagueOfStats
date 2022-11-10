package com.example.leagueofstats.displaySummoner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leagueofstats.MainActivity;
import com.example.leagueofstats.Model.Summoner;
import com.example.leagueofstats.Model.SummonerManager;
import com.example.leagueofstats.Model.Summonerable;
import com.example.leagueofstats.R;

public class SummonerActivity extends AppCompatActivity implements Summonerable {

    private ImageView summonerIcon;
    private SummonerManager manager = new SummonerManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);

        summonerIcon = findViewById(R.id.summonerIcon);

        Intent i = getIntent();
        String sumName = i.getStringExtra("searchedSummoner");
        manager.delegate = this;
        manager.fetchSummonerInfo(sumName);

    }

    @Override
    public void onBackPressed() {
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }

    @Override
    public void summonerData(Summoner summoner) {
        System.out.println(summoner);
        manager.fetchSummonerIcon(summoner.getProfileIconId());

    }

    @Override
    public void summonerIcon(Bitmap icon) {
        summonerIcon.setImageBitmap(icon);
        System.out.println(icon);
    }
}