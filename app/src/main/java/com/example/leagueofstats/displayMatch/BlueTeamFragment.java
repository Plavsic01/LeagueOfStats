package com.example.leagueofstats.displayMatch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leagueofstats.R;
import com.example.leagueofstats.displaySummoner.MatchRecyclerViewAdapter;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.summoner.Summoner;

import java.util.ArrayList;

public class BlueTeamFragment extends Fragment {

    private static final String MATCH = "match";

    private Match match;

    public BlueTeamFragment() {}


    public static BlueTeamFragment newInstance(Match match) {
        BlueTeamFragment fragment = new BlueTeamFragment();
        Bundle args = new Bundle();
        args.putSerializable(MATCH, match);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            match = (Match) getArguments().getSerializable(MATCH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blue_team, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.blueRecyclerView);
        MatchDetailsRecyclerViewAdapter matchDetailsRecyclerViewAdapter = new MatchDetailsRecyclerViewAdapter(getContext(),match);
        recyclerView.setAdapter(matchDetailsRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}