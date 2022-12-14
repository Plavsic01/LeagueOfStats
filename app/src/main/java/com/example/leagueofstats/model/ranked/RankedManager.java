package com.example.leagueofstats.model.ranked;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leagueofstats.Constants;
import com.example.leagueofstats.model.summoner.Summoner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankedManager {


    public static void fetchRankedStats(Summoner summoner, Context ctx, final RankedCallBack callBack){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = Constants.Ranked.RANKED_URL + summoner.getId() + "?api_key=" + Constants.API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    if(jsonArray.length() == 2){
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject ranked = jsonArray.getJSONObject(i);
                            String queueType = ranked.getString("queueType");
                            String leaguePoints = ranked.getString("leaguePoints");
                            String tier = ranked.getString("tier");
                            String rank = ranked.getString("rank");
                            int wins = ranked.getInt("wins");
                            int losses = ranked.getInt("losses");
                            Ranked rankedInfo = new Ranked(queueType,tier,rank,leaguePoints,wins,losses);
                            callBack.onSuccess(rankedInfo);
                        }

                    }else if(jsonArray.length() == 1){
                        JSONObject ranked = jsonArray.getJSONObject(0);
                        String queueType = ranked.getString("queueType");
                        String leaguePoints = ranked.getString("leaguePoints");
                        String tier = ranked.getString("tier");
                        String rank = ranked.getString("rank");
                        int wins = ranked.getInt("wins");
                        int losses = ranked.getInt("losses");

                        switch (queueType){
                            case "RANKED_SOLO_5x5":
                                Ranked soloRanked = new Ranked("Ranked Flex","Unranked","","0",0,0);
                                callBack.onSuccess(soloRanked);
                                break;
                            case "RANKED_FLEX_SR":
                                Ranked flexRanked = new Ranked("Ranked Solo","Unranked","","0",0,0);
                                callBack.onSuccess(flexRanked);
                                break;
                        }
                        Ranked rankedInfo = new Ranked(queueType,tier,rank,leaguePoints,wins,losses);
                        callBack.onSuccess(rankedInfo);


                        }else {
                        Ranked rankedInfo = new Ranked("Ranked Solo","Unranked","","0",0,0);
                        callBack.onSuccess(rankedInfo);
                        Ranked rankedInfo1 = new Ranked("Ranked Flex","Unranked","","0",0,0);
                        callBack.onSuccess(rankedInfo1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError(null);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });

        queue.add(stringRequest);

    }

}
