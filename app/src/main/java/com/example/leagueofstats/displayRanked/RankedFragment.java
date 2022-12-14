package com.example.leagueofstats.displayRanked;

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

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.example.leagueofstats.R;
import com.example.leagueofstats.model.ranked.Ranked;
import com.example.leagueofstats.model.ranked.RankedCallBack;
import com.example.leagueofstats.model.ranked.RankedManager;
import com.example.leagueofstats.model.summoner.Summoner;

import org.w3c.dom.Text;

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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRanked);
        TextView connectionError = view.findViewById(R.id.connectionError);
        rankedRecyclerViewAdapter = new RankedRecyclerViewAdapter(getContext(),rankedData);
        recyclerView.setAdapter(rankedRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetchRankedStats(connectionError);
    }

    private void fetchRankedStats(TextView connectionError){
        RankedManager.fetchRankedStats(summoner,getContext(), new RankedCallBack() {
            @Override
            public void onSuccess(Ranked result) {
                rankedData.add(result);
                rankedRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(VolleyError error) {
                if(error instanceof NoConnectionError){
                    connectionError.setVisibility(View.VISIBLE);
                    connectionError.setText(R.string.noInternetConnectionError);
                }
            }
        });
    }

}