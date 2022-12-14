package com.example.leagueofstats.model.ranked;

import com.android.volley.VolleyError;

public interface RankedCallBack {

    void onSuccess(Ranked result);

    void onError(VolleyError error);


}
