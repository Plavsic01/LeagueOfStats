package com.example.leagueofstats.displayMatch;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchObjectives;

import java.util.ArrayList;

public class MatchDetailsScoreBoardFragment extends Fragment {

    private static final String MATCH_OBJECTIVE = "match_objective";
    private static final String GAME_DURATION = "game_duration";

    private MatchObjectives matchObjective;
    private String gameDuration;

    public MatchDetailsScoreBoardFragment() {}

    public static MatchDetailsScoreBoardFragment newInstance(MatchObjectives matchObjective,String gameDuration) {
        MatchDetailsScoreBoardFragment fragment = new MatchDetailsScoreBoardFragment();
        Bundle args = new Bundle();
        args.putSerializable(MATCH_OBJECTIVE,matchObjective);
        args.putString(GAME_DURATION,gameDuration);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            matchObjective = (MatchObjectives) getArguments().getSerializable(MATCH_OBJECTIVE);
            gameDuration = getArguments().getString(GAME_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_details_score_board, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView baronIcon = view.findViewById(R.id.baronIcon);
        ImageView dragonIcon = view.findViewById(R.id.dragonIcon);
        ImageView towerIcon = view.findViewById(R.id.towerIcon);

        TextView win = view.findViewById(R.id.winLabel);
        TextView timePlayed = view.findViewById(R.id.gameDuration);
        TextView baronKills = view.findViewById(R.id.baronKills);
        TextView dragonKills = view.findViewById(R.id.dragonKills);
        TextView towerKills = view.findViewById(R.id.towerKills);

        if(matchObjective.isBlueTeam()){
            win.setTextColor(Color.rgb(94,130,225));
            timePlayed.setTextColor(Color.rgb(94,130,225));
            baronIcon.setImageResource(R.drawable.baron_blue);
            dragonIcon.setImageResource(R.drawable.dragon_blue);
            towerIcon.setImageResource(R.drawable.tower_blue);

        }else{
            win.setTextColor(Color.rgb(208,76,88));
            timePlayed.setTextColor(Color.rgb(208,76,88));
            baronIcon.setImageResource(R.drawable.baron_red);
            dragonIcon.setImageResource(R.drawable.dragon_red);
            towerIcon.setImageResource(R.drawable.tower_red);
        }



        win.setText(matchObjective.isWin());
        timePlayed.setText(gameDuration);
        baronKills.setText(matchObjective.getBaronKills());
        dragonKills.setText(matchObjective.getDragonKills());
        towerKills.setText(matchObjective.getTowerKills());
    }
}