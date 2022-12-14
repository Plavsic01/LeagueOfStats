package com.example.leagueofstats.model.summoner;


import com.android.volley.VolleyError;

public interface Summonerable {
    void onCompleteSummonerData(Summoner summoner);
    void onErrorSummonerData(VolleyError error);
}
