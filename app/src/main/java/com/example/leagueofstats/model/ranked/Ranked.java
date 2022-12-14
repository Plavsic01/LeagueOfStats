package com.example.leagueofstats.model.ranked;

import com.example.leagueofstats.R;

public class Ranked {


    private String queueType;
    private String tier;
    private String rank;
    private String leaguePoints;
    private int wins;
    private int losses;

    public Ranked(String queueType, String tier, String rank, String leaguePoints, int wins, int losses) {
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public String getQueueType() {
        switch (queueType){
            case "RANKED_SOLO_5x5":
                queueType = "Ranked Solo";
                break;

            case "RANKED_FLEX_SR":
                queueType = "Ranked Flex";
                break;
        }
        return queueType;
    }


    public String getTier() {
        return tier;
    }

    public String getRank() {
        return rank;
    }

    public String getLeaguePoints() {
        return leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getWinRate(){
        if(this.wins != 0 || this.losses != 0){
            Double winRate = Double.valueOf(this.wins) / Double.valueOf(this.wins + this.losses);
            winRate *= 100;

            String winRateFormatted = String.format("%.2f",winRate);
            return "Win Rate " + winRateFormatted + "%";
        }

        return "Win Rate 0%";
    }

    public int getRankImg(){

        int imgName;
        switch (getTier()){
            case "IRON":
                imgName = R.drawable.emblem_iron;
                break;
            case "BRONZE":
                imgName = R.drawable.emblem_bronze;
                break;
            case "SILVER":
                imgName = R.drawable.emblem_silver;
                break;
            case "GOLD":
                imgName = R.drawable.emblem_gold;
                break;
            case "PLATINUM":
                imgName = R.drawable.emblem_platinum;
                break;
            case "DIAMOND":
                imgName = R.drawable.emblem_diamond;
                break;
            case "MASTER":
                imgName = R.drawable.emblem_master;
                break;
            case "GRANDMASTER":
                imgName = R.drawable.emblem_grandmaster;
                break;
            case "CHALLENGER":
                imgName = R.drawable.emblem_challenger;
                break;
            default:
                imgName = R.drawable.emblem_unranked;
        }

        return imgName;
    }

}
