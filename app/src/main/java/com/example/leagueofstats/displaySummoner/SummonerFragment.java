package com.example.leagueofstats.displaySummoner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.summoner.Summoner;
import com.squareup.picasso.Picasso;

public class SummonerFragment extends Fragment {

    private static final String SUMMONER = "summoner";

    private ImageView summonerIcon;
    private TextView summonerName;
    private TextView summonerLevel;


    private Summoner summonerData;

    public SummonerFragment() {}

    public static SummonerFragment newInstance(Summoner summoner) {
        SummonerFragment fragment = new SummonerFragment();
        Bundle args = new Bundle();
        args.putSerializable(SUMMONER, summoner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            summonerData = (Summoner) getArguments().getSerializable(SUMMONER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summoner, container, false);

        summonerIcon = view.findViewById(R.id.summonerIcon);
        summonerName = view.findViewById(R.id.summonerName);
        summonerLevel = view.findViewById(R.id.summonerLevel);

        summonerName.setText(summonerData.getName());
        summonerLevel.setText(summonerData.getSummonerLevel());
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.21.1/img/profileicon/" + summonerData.getProfileIconId() + ".png").into(summonerIcon);

        return view;
    }
}