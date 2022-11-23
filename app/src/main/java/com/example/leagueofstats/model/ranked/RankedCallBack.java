package com.example.leagueofstats.model.ranked;

public interface RankedCallBack {

    void onSuccess(Ranked result);

    void onError(String error);


}
