package com.example.leagueofstats.displaySummoner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.leagueofstats.R;
import com.example.leagueofstats.displayMatch.MatchActivity;
import com.example.leagueofstats.model.connection.ConnectionManager;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchCallBack;
import com.example.leagueofstats.model.match.MatchManager;
import com.example.leagueofstats.model.match.SelectListener;
import com.example.leagueofstats.model.summoner.Summoner;

import java.util.ArrayList;

public class MatchFragment extends Fragment implements SelectListener {

    private static final String SUMMONER = "summoner";
    private ArrayList<Match> matches = new ArrayList<>();

    private Summoner summoner;
    private TextView matchErrorLabel;
    private MatchRecyclerViewAdapter matchRecyclerViewAdapter;

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

        View view = inflater.inflate(R.layout.fragment_match, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.matchRecyclerView);
        matchErrorLabel = view.findViewById(R.id.matchErrorLabel);
        matchRecyclerViewAdapter = new MatchRecyclerViewAdapter(getContext(),matches,this);
        recyclerView.setAdapter(matchRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        fetchMatches(recyclerView,matchErrorLabel);
    }

    public void fetchMatches(RecyclerView recyclerView,TextView matchErrorLabel){
        MatchManager.fetchMatches(summoner, getContext(), new MatchCallBack() {
            @Override
            public void onSuccess(Match result) {
                if(result == null){
                    matchErrorLabel.setVisibility(View.VISIBLE);
                    matchErrorLabel.setText(R.string.noGamesRecordedError);
                    if(ConnectionManager.getConnectivityNetworkStatus(getContext()) == 0){
                        matchErrorLabel.setText(R.string.noInternetConnectionError);
                    }
                }else{
                    matches.add(result);
                    matchRecyclerViewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(VolleyError error) {
                if(error == null){ // json error while parsing
                    matchErrorLabel.setText(R.string.jsonError);
                }else{
                    try {
                        int statusCode = error.networkResponse.statusCode;
                        if(statusCode == 429){
                            recyclerView.setVisibility(View.INVISIBLE);
                            matchErrorLabel.setText(R.string.requestError);
                        }else{
                            Toast.makeText(getContext(),"Error " + statusCode, Toast.LENGTH_SHORT).show();
                        }

                    }catch (NullPointerException e){
                        // ako se iskljuci internet konekcija, VolleyError postane null i izbaci NullPointerException
                        System.out.println(e);
                    }
                }

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