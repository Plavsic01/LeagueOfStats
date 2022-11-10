package com.example.leagueofstats.model;

public class Summoner {

    private String puuid;
    private String name;
    private String profileIconId;
    private String summonerLevel;


    public Summoner(String puuid,String name,String summonerLevel,String profileIconId){
        this.puuid = puuid;
        this.name = name;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
    }

    public String getPuuid() {
        return puuid;
    }

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
                ", name='" + name + '\'' +
                ", profileIconId=" + profileIconId +
                ", summonerLevel=" + summonerLevel +
                '}';
    }
}
