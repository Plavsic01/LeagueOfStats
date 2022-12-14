package com.example.leagueofstats.displayRanked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueofstats.R;
import com.example.leagueofstats.model.ranked.Ranked;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RankedRecyclerViewAdapter extends RecyclerView.Adapter<RankedRecyclerViewAdapter.MyViewHolder>{

    private Context ctx;
    private ArrayList<Ranked> rankedData;

    public RankedRecyclerViewAdapter(Context ctx, ArrayList<Ranked> rankedData){
        this.ctx = ctx;
        this.rankedData = rankedData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View contactView = inflater.inflate(R.layout.recycler_view_ranked_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(contactView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.rankedIcon.setImageResource(rankedData.get(position).getRankImg());
        holder.rankedType.setText(rankedData.get(position).getQueueType());
        holder.rankedTierRank.setText(rankedData.get(position).getTier() + " " + rankedData.get(position).getRank());
        holder.rankedPoints.setText(rankedData.get(position).getLeaguePoints() + " LP");
        holder.rankedWinsLossses.setText(rankedData.get(position).getWins() + "W " + rankedData.get(position).getLosses() + "L");
        holder.rankedWinRate.setText(rankedData.get(position).getWinRate());

    }

    @Override
    public int getItemCount() {
        return rankedData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView rankedIcon;
        public TextView rankedType;
        public TextView rankedTierRank;
        public TextView rankedPoints;
        public TextView rankedWinsLossses;
        public TextView rankedWinRate;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rankedIcon = itemView.findViewById(R.id.rankedIcon);
            rankedType = itemView.findViewById(R.id.rankedType);
            rankedTierRank = itemView.findViewById(R.id.rankedTierRank);
            rankedPoints = itemView.findViewById(R.id.rankedPoints);
            rankedWinsLossses = itemView.findViewById(R.id.rankedWinsLosses);
            rankedWinRate = itemView.findViewById(R.id.rankedWinRate);




        }
    }

}
