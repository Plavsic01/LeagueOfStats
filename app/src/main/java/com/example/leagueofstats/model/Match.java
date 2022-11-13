package com.example.leagueofstats.model;

import java.util.ArrayList;

public class Match {

    private int gameCreation;
    private int gameDuration;
    private int gameStartTimestamp;
    private int gameEndTimestamp;
    private Summoner summoner;
    private ArrayList<Participant> participants = new ArrayList<Participant>();


    public Match(int gameCreation, int gameDuration, int gameStartTimestamp, int gameEndTimestamp, Summoner summoner, ArrayList<Participant> participants) {
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameEndTimestamp = gameEndTimestamp;
        this.summoner = summoner;
        this.participants = participants;
    }

    public int getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(int gameCreation) {
        this.gameCreation = gameCreation;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public int getGameStartTimestamp() {
        return gameStartTimestamp;
    }

    public void setGameStartTimestamp(int gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }

    public int getGameEndTimestamp() {
        return gameEndTimestamp;
    }

    public void setGameEndTimestamp(int gameEndTimestamp) {
        this.gameEndTimestamp = gameEndTimestamp;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }


    public Participant getCurrentPlayer(){
        Participant player = null;
        for(Participant p:getParticipants()){
            if(summoner.getName().equals(p.getSummonerName())){
                player = p;
            }
        }
        return player;
    }

}


