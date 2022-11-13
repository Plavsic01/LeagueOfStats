package com.example.leagueofstats.displaySummoner;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.Match;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SummonerRecyclerViewAdapter extends RecyclerView.Adapter<SummonerRecyclerViewAdapter.MyViewHolder>{

    Context ctx;
    ArrayList<Match> matches;

    public SummonerRecyclerViewAdapter(Context ctx, ArrayList<Match> matches){
        this.ctx = ctx;
        this.matches = matches;
    }

    @NonNull
    @Override
    public SummonerRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View contactView = inflater.inflate(R.layout.recycler_view_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(contactView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MatchComparator c = new MatchComparator();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            matches.sort(c);
        }

        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/champion/" + matches.get(position).getCurrentPlayer().getChampionName() + ".png").into(holder.champIcon);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem0() + ".png").into(holder.item0);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem1() + ".png").into(holder.item1);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem2() + ".png").into(holder.item2);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem3() + ".png").into(holder.item3);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem4() + ".png").into(holder.item4);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem5() + ".png").into(holder.item5);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.18.1/img/item/" + matches.get(position).getCurrentPlayer().getItem6() + ".png").into(holder.item6);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView champIcon;
        public ImageView item0;
        public ImageView item1;
        public ImageView item2;
        public ImageView item3;
        public ImageView item4;
        public ImageView item5;
        public ImageView item6;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            champIcon = itemView.findViewById(R.id.champIcon);
            item0 = itemView.findViewById(R.id.item0);
            item1 = itemView.findViewById(R.id.item1);
            item2 = itemView.findViewById(R.id.item2);
            item3 = itemView.findViewById(R.id.item3);
            item4 = itemView.findViewById(R.id.item4);
            item5 = itemView.findViewById(R.id.item5);
            item6 = itemView.findViewById(R.id.item6);

        }
    }

}
