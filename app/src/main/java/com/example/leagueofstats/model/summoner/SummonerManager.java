package com.example.leagueofstats.model.summoner;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leagueofstats.Constants;
import com.example.leagueofstats.model.connection.ConnectionManager;
import com.example.leagueofstats.summonerDatabase.DatabaseHelper;
import com.example.leagueofstats.summonerDatabase.SummonerLayerDB;

import org.json.JSONException;
import org.json.JSONObject;


public class SummonerManager {

    private Context ctx;
    public Summonerable delegate = null;

    public SummonerManager(Context ctx){
        this.ctx = ctx;
    }


    public void fetchSummonerInfo(String sumName){

        // no spaces
        String summonerName = sumName.replace(" ","");

        DatabaseHelper databaseHelper = new DatabaseHelper(ctx);
        SummonerLayerDB summonerLayerDB = new SummonerLayerDB(databaseHelper);
        Summoner summonerFromDataBase = summonerLayerDB.getSummoner(summonerName);
        System.out.println(summonerFromDataBase);
        int status = ConnectionManager.getConnectivityNetworkStatus(ctx);

        if(status == 0){
            if(summonerFromDataBase != null) {
                delegate.onCompleteSummonerData(summonerFromDataBase);
            }else{
                delegate.onCompleteSummonerData(null);
            }
        }else{
            RequestQueue queue = Volley.newRequestQueue(ctx);
            String url = Constants.Summoner.SUMMONER_URL + summonerName + "?api_key=" + Constants.API_KEY;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String puuid = jsonObject.getString("puuid");
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String summonerLevel = jsonObject.getString("summonerLevel");
                        String profileIconId = jsonObject.getString("profileIconId");

                        Summoner summoner = new Summoner(puuid,id,name,summonerLevel,profileIconId);
                        delegate.onCompleteSummonerData(summoner);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    delegate.onErrorSummonerData(error);
                    error.printStackTrace();
                }
            });

            queue.add(stringRequest);
        }

    }


}
