package com.example.leagueofstats.displayMatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueofstats.Constants;
import com.example.leagueofstats.R;
import com.example.leagueofstats.displaySummoner.SummonerActivity;
import com.example.leagueofstats.model.match.Match;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class MatchDetailsRecyclerViewAdapter extends RecyclerView.Adapter<MatchDetailsRecyclerViewAdapter.MyViewHolder>{

    private Context ctx;
    private boolean isRedTeam;
    private Match match;

    public MatchDetailsRecyclerViewAdapter(Context ctx, Match match,boolean isRedTeam) {
        this.ctx = ctx;
        this.match = match;
        this.isRedTeam = isRedTeam;
    }

    @NonNull
    @Override
    public MatchDetailsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View contactView = inflater.inflate(R.layout.blue_red_recycler_view_match_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(contactView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDetailsRecyclerViewAdapter.MyViewHolder holder, int position) {

        if(isRedTeam){
            position = position + 5;
        }

        Picasso.get().load(Constants.Champion.CHAMPION_ICON_URL + match.getParticipants().get(position).getChampionName() + ".png").into(holder.champIcon);

        Picasso.get().load(Constants.Match.SUMMONER_SPELL_URL + match.getParticipants().get(position).getSummoner1Name() + ".png").into(holder.summonerD);
        Picasso.get().load(Constants.Match.SUMMONER_SPELL_URL + match.getParticipants().get(position).getSummoner2Name() + ".png").into(holder.summonerF);


        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem0() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item0);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem1() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item1);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem2() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item2);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem3() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item3);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem4() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item4);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem5() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item5);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem6() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item6);

        holder.sumName.setText(match.getParticipants().get(position).getSummonerName() + "  ");
        holder.champLevel.setText(match.getParticipants().get(position).getChampLevel());
        holder.kills.setText(match.getParticipants().get(position).getKills());
        holder.deaths.setText(match.getParticipants().get(position).getDeaths());
        holder.assists.setText(match.getParticipants().get(position).getAssists());

        holder.minionsKilled.setText(Integer.toString(match.getParticipants().get(position).getTotalMinionsKilled()) + " ");

        double goldEarned = (double) match.getParticipants().get(position).getGoldEarned();
        DecimalFormat formatter = new DecimalFormat("#,###");
        String goldEarnedFormatted = formatter.format(goldEarned);

        holder.goldEarned.setText(goldEarnedFormatted);

        holder.summonerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, SummonerActivity.class);
                i.putExtra("searchedSummoner",holder.sumName.getText());
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public CardView summonerCard;
        public ImageView champIcon;

        public ImageView summonerD;
        public ImageView summonerF;


        public ImageView item0;
        public ImageView item1;
        public ImageView item2;
        public ImageView item3;
        public ImageView item4;
        public ImageView item5;
        public ImageView item6;

        public TextView champLevel;
        public TextView sumName;
        public TextView kills;
        public TextView deaths;
        public TextView assists;
        public TextView minionsKilled;
        public TextView goldEarned;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            summonerCard = itemView.findViewById(R.id.showSummoner);
            champIcon = itemView.findViewById(R.id.summonerIcon);

            summonerD = itemView.findViewById(R.id.summonerD);
            summonerF = itemView.findViewById(R.id.summonerF);

            item0 = itemView.findViewById(R.id.detailsItem0);
            item1 = itemView.findViewById(R.id.detailsItem1);
            item2 = itemView.findViewById(R.id.detailsItem2);
            item3 = itemView.findViewById(R.id.detailsItem3);
            item4 = itemView.findViewById(R.id.detailsItem4);
            item5 = itemView.findViewById(R.id.detailsItem5);
            item6 = itemView.findViewById(R.id.detailsItem6);

            sumName = itemView.findViewById(R.id.sumName);
            champLevel = itemView.findViewById(R.id.champLevel);
            kills = itemView.findViewById(R.id.killsLabel);
            deaths = itemView.findViewById(R.id.deathsLabel);
            assists = itemView.findViewById(R.id.assistsLabel);

            minionsKilled = itemView.findViewById(R.id.minionsKilled);
            goldEarned = itemView.findViewById(R.id.goldEarned);

        }
    }

}

