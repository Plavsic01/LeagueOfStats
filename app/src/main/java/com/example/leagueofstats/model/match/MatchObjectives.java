package com.example.leagueofstats.model.match;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MatchObjectives implements Serializable {

    private int teamId;
    private String baronKills;
    private String championKills;
    private String dragonKills;
    private String towerKills;
    private boolean win;

    public MatchObjectives(int teamId, String baronKills, String championKills, String dragonKills,String towerKills,boolean win) {
        this.teamId = teamId;
        this.baronKills = baronKills;
        this.championKills = championKills;
        this.dragonKills = dragonKills;
        this.towerKills = towerKills;
        this.win = win;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getBaronKills() {
        return baronKills;
    }

    public String getChampionKills() {
        return championKills;
    }

    public String getDragonKills() {
        return dragonKills;
    }

    public String getTowerKills() {
        return towerKills;
    }

    public String isWin() {
        if(win){
            return "Victory";
        }
        return "Defeat";
    }

    public boolean isBlueTeam(){
        if(teamId == 100){
            return true;
        }
        return false;
    }


}
