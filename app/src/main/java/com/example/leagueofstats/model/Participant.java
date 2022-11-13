package com.example.leagueofstats.model;

public class Participant {

    private String summonerName;
    private String champLevel;
    private String championName;
    private String kills;
    private String deaths;
    private String assists;
    private int goldEarned;
    private int totalMinionsKilled;
    private String summoner1Id;
    private String summoner2Id;
    private boolean win;

    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;


    public Participant(String summonerName, String champLevel, String championName, String kills, String deaths, String assists, int goldEarned, int totalMinionsKilled, String summoner1Id, String summoner2Id, boolean win, int item0, int item1, int item2, int item3, int item4, int item5, int item6) {
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
        this.win = win;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(String champLevel) {
        this.champLevel = champLevel;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public String getSummoner1Id() {
        return summoner1Id;
    }

    public void setSummoner1Id(String summoner1Id) {
        this.summoner1Id = summoner1Id;
    }

    public String getSummoner2Id() {
        return summoner2Id;
    }

    public void setSummoner2Id(String summoner2Id) {
        this.summoner2Id = summoner2Id;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getItem6() {
        return item6;
    }

    public void setItem6(int item6) {
        this.item6 = item6;
    }
}
