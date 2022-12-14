package com.example.leagueofstats.model.match;

import android.text.format.DateUtils;

import com.example.leagueofstats.model.summoner.Summoner;

import java.io.Serializable;
import java.util.ArrayList;

public class Match implements Serializable {

    private int gameCreation;
    private int gameDuration;
    private int gameStartTimestamp;
    private int gameEndTimestamp;
    private Summoner summoner;
    private ArrayList<Participant> participants = new ArrayList<Participant>();
    private ArrayList<MatchObjectives> matchObjectives = new ArrayList<MatchObjectives>();


    public Match(int gameCreation, int gameDuration, int gameStartTimestamp, int gameEndTimestamp, Summoner summoner,
                 ArrayList<Participant> participants,ArrayList<MatchObjectives> matchObjectives) {
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameEndTimestamp = gameEndTimestamp;
        this.summoner = summoner;
        this.participants = participants;
        this.matchObjectives = matchObjectives;
    }

    public int getGameCreation() {
        return gameCreation;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public int getGameStartTimestamp() {
        return gameStartTimestamp;
    }

    public int getGameEndTimestamp() {
        return gameEndTimestamp;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<MatchObjectives> getMatchObjectives() {
        return matchObjectives;
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

    public String calcGameDuration(){
        long timeInSeconds = ((getGameEndTimestamp() / 1000) - (getGameStartTimestamp() / 1000));
        String gameDuration = DateUtils.formatElapsedTime(timeInSeconds);
        return gameDuration;
    }

}


