package com.example.leagueofstats.displayMatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueofstats.Constants;
import com.example.leagueofstats.R;
import com.example.leagueofstats.model.match.Match;
import com.squareup.picasso.Picasso;

public class MatchDetailsRecyclerViewAdapter extends RecyclerView.Adapter<MatchDetailsRecyclerViewAdapter.MyViewHolder>{

    private Context ctx;
    private Match match;

    public MatchDetailsRecyclerViewAdapter(Context ctx, Match match) {
        this.ctx = ctx;
        this.match = match;
    }

    @NonNull
    @Override
    public MatchDetailsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View contactView = inflater.inflate(R.layout.blue_recycler_view_match_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(contactView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDetailsRecyclerViewAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(Constants.Champion.CHAMPION_ICON_URL + match.getParticipants().get(position).getChampionName() + ".png").into(holder.champIcon);

        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.21.1/img/spell/SummonerFlash.png").into(holder.summonerD);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.21.1/img/spell/SummonerFlash.png").into(holder.summonerF);


        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem0() + ".png").into(holder.item0);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem1() + ".png").into(holder.item1);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem2() + ".png").into(holder.item2);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem3() + ".png").into(holder.item3);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem4() + ".png").into(holder.item4);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem5() + ".png").into(holder.item5);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + match.getParticipants().get(position).getItem6() + ".png").into(holder.item6);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


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


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

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

        }
    }

}

