package com.example.leagueofstats.model.summoner;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class SummonerManager {

    private Context ctx;
    public Summonerable delegate = null;

    public SummonerManager(Context ctx){
        this.ctx = ctx;
    }

    // napraviti file sa konstantama tj url-ovima

    public void fetchSummonerInfo(String summonerName){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=RGAPI-455b8388-a653-4a2d-8c68-e82c472934a0";
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
                    System.out.println(summoner);

                    delegate.summonerData(summoner);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR OCCURED");
                error.printStackTrace();
            }
        });

       queue.add(stringRequest);

    }

    public void fetchSummonerIcon(String profileIconId){
        String url = "http://ddragon.leagueoflegends.com/cdn/12.21.1/img/profileicon/" + profileIconId + ".png";
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                delegate.summonerIcon(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });

        requestQueue.add(imageRequest);

    }


}
