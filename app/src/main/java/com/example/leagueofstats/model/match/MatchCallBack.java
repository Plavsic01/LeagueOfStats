package com.example.leagueofstats.model.match;

public interface MatchCallBack {
    void onSuccess(Match result);

    void onError(String error);
}
