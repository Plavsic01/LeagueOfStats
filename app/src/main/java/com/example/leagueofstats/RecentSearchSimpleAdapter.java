package com.example.leagueofstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.leagueofstats.recentlySearchedDatabase.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentSearchSimpleAdapter extends SimpleAdapter implements Filterable{

    private Context context;
    private int resource;
    private ArrayList<HashMap<String,String>> data;
    private ArrayList<HashMap<String,String>> filteredData;
    DatabaseManager dbManager;

    public RecentSearchSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,DatabaseManager dbManager) {
        super(context, data, resource, from, to);
        this.context = context;
        this.resource = resource;
        this.data = (ArrayList<HashMap<String, String>>) data;
        this.dbManager = dbManager;
        this.filteredData = this.data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource,null);
        TextView searchName = (TextView) convertView.findViewById(R.id.searchName);
        searchName.setText(filteredData.get(position).get("summonerName"));

        ImageButton delBtn = (ImageButton) convertView.findViewById(R.id.delBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // brisanje iz baze podataka
                dbManager.delete(Long.parseLong(filteredData.get(position).get("id")));

                // kreiranje mape u kojoj ce biti smesten obrisan podatak
                HashMap<String,String> deleted = filteredData.remove(position);
                //brisanje iz data kolekcije zbog toga sto kada se obrise podatak
                // tokom filtriranja on ostane u data kolekciji, a u filteredData je obrisan
                // zbog toga se i dalje prikazuje
                data.remove(deleted);

                notifyDataSetChanged();


            }
        });

        return convertView;
    }


    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.count = data.size();
                    filterResults.values = data;
                }else {
                    ArrayList<HashMap<String, String>> noviPodaci = new ArrayList<>();

                    String searchStr = charSequence.toString().toLowerCase();
                    for(int i = 0; i < data.size(); i++){

                        HashMap<String,String> summoner = new HashMap<>();

                        if(data.get(i).get("summonerName").contains(searchStr)){
                            summoner.put("id",data.get(i).get("id"));
                            summoner.put("summonerName",data.get(i).get("summonerName"));
                            noviPodaci.add(summoner);
                        }
                    }

                    filterResults.count = noviPodaci.size();
                    filterResults.values = noviPodaci;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredData = (ArrayList<HashMap<String, String>>) filterResults.values;

                notifyDataSetChanged();


            }
        };

        return filter;
    }


}
