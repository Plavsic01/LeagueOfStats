package com.example.leagueofstats.model;

public interface MatchCallBack {
    void onSuccess(Match result);

    void onError(String error);
}
