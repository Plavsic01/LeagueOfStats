package com.example.leagueofstats.model.ranked;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leagueofstats.model.match.MatchCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankedManager {

    private Context ctx;

    public RankedManager(Context ctx) {
        this.ctx = ctx;
    }
//    final MatchCallBack callBack
    public void fetchRankedStats(final RankedCallBack callBack){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/sQ_90HT6gmm62XNH8TM8JT7tbRjAmTX1seewXWSfy9CLpiU?api_key=RGAPI-455b8388-a653-4a2d-8c68-e82c472934a0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    System.out.println(jsonArray);
                   callBack.onFetchComplete(jsonArray);


                } catch (JSONException e) {
                    e.printStackTrace();
//                    callBack.onError("error");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

}
