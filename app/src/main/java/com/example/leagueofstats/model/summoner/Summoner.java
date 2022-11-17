package com.example.leagueofstats.model.summoner;

import java.io.Serializable;

public class Summoner implements Serializable {

    private String puuid;
    private String id;
    private String name;
    private String profileIconId;
    private String summonerLevel;


    public Summoner(String puuid,String id,String name,String summonerLevel,String profileIconId){
        this.puuid = puuid;
        this.id = id;
        this.name = name;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getProfileIconId() {
        return profileIconId;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "puuid='" + puuid + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId='" + profileIconId + '\'' +
                ", summonerLevel='" + summonerLevel + '\'' +
                '}';
    }
}
