package com.example.leagueofstats.displaySummoner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leagueofstats.R;
import com.example.leagueofstats.displayMatch.MatchActivity;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchCallBack;
import com.example.leagueofstats.model.match.MatchMananger;
import com.example.leagueofstats.model.match.SelectListener;
import com.example.leagueofstats.model.summoner.Summoner;

import java.util.ArrayList;

public class MatchFragment extends Fragment implements SelectListener {

    private static final String SUMMONER = "summoner";
    private ArrayList<Match> matches = new ArrayList<>();

    private Summoner summoner;
    private SummonerRecyclerViewAdapter summonerRecyclerViewAdapter;

    public MatchFragment() {}


    public static MatchFragment newInstance(Summoner summoner) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putSerializable(SUMMONER, summoner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            summoner = (Summoner) getArguments().getSerializable(SUMMONER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match_recycler, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.matchRecyclerView);

        summonerRecyclerViewAdapter = new SummonerRecyclerViewAdapter(getContext(),matches,this);
        recyclerView.setAdapter(summonerRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        fetchMatches();
        return view;
    }


    public void fetchMatches(){
        MatchMananger.fetchMatches(summoner, getContext(), new MatchCallBack() {
            @Override
            public void onSuccess(Match result) {
                matches.add(result);
                summonerRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                System.out.println("error");
            }
        });
    }


    @Override
    public void onItemClicked(Match match) {
        Intent matchDetails = new Intent(getContext(), MatchActivity.class);
        matchDetails.putExtra("match",match);
        startActivity(matchDetails);
    }
}