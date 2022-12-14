package com.example.leagueofstats.displaySummoner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueofstats.Constants;
import com.example.leagueofstats.R;
import com.example.leagueofstats.model.match.Match;
import com.example.leagueofstats.model.match.MatchComparator;
import com.example.leagueofstats.model.match.SelectListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.MyViewHolder>{

    private Context ctx;
    private ArrayList<Match> matches;
    private SelectListener listener;

    public MatchRecyclerViewAdapter(Context ctx, ArrayList<Match> matches, SelectListener listener){
        this.ctx = ctx;
        this.matches = matches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatchRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View contactView = inflater.inflate(R.layout.recycler_view_match_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(contactView);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MatchComparator c = new MatchComparator();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            matches.sort(c);
        }

        if(matches.get(position).getCurrentPlayer().isWin()){
            holder.champIcon.setBackgroundColor(Color.rgb(66,107,214));
        }else{
            holder.champIcon.setBackgroundColor(Color.rgb(208,76,88));
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(matches.get(holder.getAdapterPosition()));
            }
        });

        Picasso.get().load(Constants.Champion.CHAMPION_ICON_URL + matches.get(position).getCurrentPlayer().getChampionName() + ".png").into(holder.champIcon);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem0() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item0);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem1() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item1);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem2() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item2);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem3() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item3);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem4() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item4);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem5() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item5);
        Picasso.get().load(Constants.Champion.CHAMPION_ITEM_URL + matches.get(position).getCurrentPlayer().getItem6() + ".png").placeholder(ctx.getResources().getDrawable(R.drawable.missing_item_icon)).into(holder.item6);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

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

            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
