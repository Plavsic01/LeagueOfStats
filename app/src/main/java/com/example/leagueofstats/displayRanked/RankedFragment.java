package com.example.leagueofstats.displayRanked;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.ranked.Ranked;
import com.example.leagueofstats.model.ranked.RankedCallBack;
import com.example.leagueofstats.model.ranked.RankedManager;
import com.example.leagueofstats.model.summoner.Summoner;

import java.util.ArrayList;

public class RankedFragment extends Fragment {

    private static final String SUMMONER = "summoner";

    private RankedRecyclerViewAdapter rankedRecyclerViewAdapter;
    private ArrayList<Ranked> rankedData = new ArrayList<>();


    private Summoner summoner;


    public RankedFragment() {}

    public static RankedFragment newInstance(Summoner summoner) {
        RankedFragment fragment = new RankedFragment();
        Bundle args = new Bundle();
        args.putSerializable(SUMMONER,summoner);
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

        View view = inflater.inflate(R.layout.fragment_ranked, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRanked);
        rankedRecyclerViewAdapter = new RankedRecyclerViewAdapter(getContext(),rankedData);
        recyclerView.setAdapter(rankedRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetchRankedStats();
        return view;
    }


    private void fetchRankedStats(){
        RankedManager.fetchRankedStats(summoner,getContext(), new RankedCallBack() {
            @Override
            public void onSuccess(Ranked result) {
                rankedData.add(result);
                rankedRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
    }

}