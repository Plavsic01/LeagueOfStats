package com.example.leagueofstats.displaySummoner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leagueofstats.Constants;
import com.example.leagueofstats.R;
import com.example.leagueofstats.displayRanked.RankedActivity;
import com.example.leagueofstats.model.summoner.Summoner;
import com.squareup.picasso.Picasso;

public class SummonerFragment extends Fragment {

    private static final String SUMMONER = "summoner";

    private ImageView summonerIcon;
    private TextView summonerName;
    private TextView summonerLevel;
    private Button rankedInfo;


    private Summoner summoner;

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
            summoner = (Summoner) getArguments().getSerializable(SUMMONER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summoner, container, false);

        summonerIcon = view.findViewById(R.id.summonerIcon);
        summonerName = view.findViewById(R.id.summonerName);
        summonerLevel = view.findViewById(R.id.summonerLevel);
        rankedInfo = view.findViewById(R.id.rankedInfoBtn);

        // rankedInfo je stavljen na GONE u designeru
        rankedInfo.setVisibility(View.VISIBLE);

        summonerName.setText(summoner.getName());
        summonerLevel.setText("Level: " + summoner.getSummonerLevel());
        rankedInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rankedIntent = new Intent(getContext(), RankedActivity.class);
                rankedIntent.putExtra("summoner",summoner);
                startActivity(rankedIntent);
            }
        });
        Picasso.get().load(Constants.Summoner.SUMMONER_ICON_URL + summoner.getProfileIconId() + ".png").into(summonerIcon);

        return view;
    }
}