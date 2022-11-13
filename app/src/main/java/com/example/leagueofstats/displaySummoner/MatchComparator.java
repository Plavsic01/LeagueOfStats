package com.example.leagueofstats.displaySummoner;

import com.example.leagueofstats.model.Match;

import java.util.Comparator;

public class MatchComparator implements Comparator<Match> {
    @Override
    public int compare(Match match, Match t1) {
        if(match.getGameCreation() > t1.getGameCreation()){
            return -1;
        }else if(match.getGameCreation() < t1.getGameCreation()){
            return 1;
        }
        return 0;
    }
}
