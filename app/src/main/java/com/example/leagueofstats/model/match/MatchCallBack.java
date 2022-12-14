package com.example.leagueofstats.model.match;

import com.android.volley.VolleyError;

public interface MatchCallBack {
    void onSuccess(Match result);
    void onError(VolleyError error);
}
