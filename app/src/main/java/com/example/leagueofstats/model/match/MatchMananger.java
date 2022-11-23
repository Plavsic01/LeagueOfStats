package com.example.leagueofstats.model.match;

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

import java.util.ArrayList;


public class MatchMananger {

    public static void fetchMatches(Summoner summoner, Context ctx,final MatchCallBack callback){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = Constants.Match.ALL_MATCHES_URL + summoner.getPuuid() + "/ids?start=0&count=10&api_key=" + Constants.API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length();i++){

                        fetchMatch(summoner,ctx,(String) jsonArray.get(i), new MatchCallBack() {
                            @Override
                            public void onSuccess(Match result) {
                                callback.onSuccess(result);
                            }

                            @Override
                            public void onError(String error) {
                                callback.onError(error);
                            }
                        });
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }


    private static void fetchMatch(Summoner summoner,Context ctx,String matchId,final MatchCallBack callback){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = Constants.Match.MATCH_URL + matchId + "?api_key=" + Constants.API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject info = jsonObject.getJSONObject("info");
                    int gameCreation = info.getInt("gameCreation");
                    int gameDuration = info.getInt("gameDuration");
                    int gameStartTimestamp = info.getInt("gameStartTimestamp");
                    int gameEndTimestamp = info.getInt("gameEndTimestamp");

                    JSONArray participants = info.getJSONArray("participants");

                    ArrayList<Participant> participantsArray = new ArrayList<>();

                    for(int i = 0; i < participants.length();i++){
                        JSONObject participant = participants.getJSONObject(i);

                        String summonerName = participant.getString("summonerName");
                        String champLevel = participant.getString("champLevel");
                        String championName = participant.getString("championName");

                        String kills = participant.getString("kills");
                        String deaths = participant.getString("deaths");
                        String assists = participant.getString("assists");

                        int goldEarned = participant.getInt("goldEarned");
                        int minionsKilled = participant.getInt("totalMinionsKilled");
                        int neutralMinionsKilled = participant.getInt("neutralMinionsKilled");

                        String summoner1Id = participant.getString("summoner1Id");
                        String summoner2Id = participant.getString("summoner2Id");

                        Boolean win = participant.getBoolean("win");

                        int item0 = participant.getInt("item0");
                        int item1 = participant.getInt("item1");
                        int item2 = participant.getInt("item2");
                        int item3 = participant.getInt("item3");
                        int item4 = participant.getInt("item4");
                        int item5 = participant.getInt("item5");
                        int item6 = participant.getInt("item6");


                        int totalMinionsKilled = minionsKilled + neutralMinionsKilled;

                        Participant parObj = new Participant(summonerName,champLevel,championName,
                                kills,deaths,assists,goldEarned,
                                totalMinionsKilled,summoner1Id,summoner2Id,
                                win,item0,item1,item2,item3,item4,item5,item6);

                        participantsArray.add(parObj);

                    }

                    Match match = new Match(gameCreation,gameDuration,gameStartTimestamp,gameEndTimestamp,summoner,participantsArray);
                    callback.onSuccess(match);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("ERROR"); // za sada ovako
            }
    });
        queue.add(stringRequest);

    }


}
