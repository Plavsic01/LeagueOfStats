package com.example.leagueofstats.model.match;

import java.io.Serializable;

public class Participant implements Serializable {

    private String summonerName;
    private String champLevel;
    private String championName;
    private String kills;
    private String deaths;
    private String assists;
    private int goldEarned;
    private int totalMinionsKilled;
    private int summoner1Id;
    private int summoner2Id;

    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;

    private boolean win;


    public Participant(String summonerName, String champLevel, String championName, String kills, String deaths, String assists, int goldEarned, int totalMinionsKilled, int summoner1Id, int summoner2Id,int item0, int item1, int item2, int item3, int item4, int item5, int item6,boolean win) {
        this.summonerName = summonerName;
        this.champLevel = champLevel;
        this.championName = championName;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.goldEarned = goldEarned;
        this.totalMinionsKilled = totalMinionsKilled;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.win = win;
    }


    private String getSummonerSpellName(int summonerSpellId){
        String summonerSpellName = "";
        switch (summonerSpellId){
            case 1:
                summonerSpellName = "SummonerBoost";
                break;
            case 3:
                summonerSpellName = "SummonerExhaust";
                break;
            case 4:
                summonerSpellName = "SummonerFlash";
                break;
            case 6:
                summonerSpellName = "SummonerHaste";
                break;
            case 7:
                summonerSpellName = "SummonerHeal";
                break;
            case 11:
                summonerSpellName = "SummonerSmite";
                break;
            case 12:
                summonerSpellName = "SummonerTeleport";
                break;
            case 13:
                summonerSpellName = "SummonerMana";
                break;
            case 14:
                summonerSpellName = "SummonerDot";
                break;
            case 21:
                summonerSpellName = "SummonerBarrier";
                break;
            case 30:
                summonerSpellName = "SummonerPoroRecall";
                break;
            case 31:
                summonerSpellName = "SummonerPoroThrow";
                break;
            case 32:
                summonerSpellName = "SummonerSnowball";
                break;
            case 39:
                summonerSpellName = "SummonerSnowURFSnowball_Mark";
                break;

            default:
                summonerSpellName = "Summoner_UltBookPlaceholder";
        }
        return summonerSpellName;
    }


    public String getSummonerName() {
        return summonerName;
    }

    public String getChampLevel() {
        return champLevel;
    }

    public String getChampionName() {
        return championName;
    }

    public String getKills() {return kills;}

    public String getDeaths() {
        return deaths;
    }

    public String getAssists() {
        return assists;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public int getSummoner1Id() {
        return summoner1Id;
    }

    public int getSummoner2Id() {
        return summoner2Id;
    }

    public String getSummoner1Name(){
        return getSummonerSpellName(summoner1Id);
    }

    public String getSummoner2Name(){
        return getSummonerSpellName(summoner2Id);
    }


    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public boolean isWin() {
        return win;
    }
}
