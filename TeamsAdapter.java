package com.example.ubundu.canteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ubundu on 7/6/16.
 */
public class TeamsAdapter extends ArrayAdapter<Team> {

    Context context;
    private ArrayList<Team> teams;

    public TeamsAdapter(Context context, int textViewResourceId, ArrayList<Team> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.teams = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team, null);
        }
        Team o = teams.get(position);
        if (o != null) {
            TextView pos = (TextView) v.findViewById(R.id.position);
            TextView name = (TextView) v.findViewById(R.id.brf1);
            TextView wins = (TextView) v.findViewById(R.id.brf2);
            TextView draws = (TextView) v.findViewById(R.id.lunch1);
            TextView losses = (TextView) v.findViewById(R.id.lunch2);
            TextView points = (TextView) v.findViewById(R.id.lunch3);
            TextView snk1=(TextView)v.findViewById(R.id.snk1);
            TextView snk2=(TextView)v.findViewById(R.id.snk2);

            pos.setText(String.valueOf(o.getPosition()));
            name.setText(String.valueOf(o.getName()));
            wins.setText(String.valueOf(o.getWins()));
            draws.setText(String.valueOf(o.getDraws()));
            losses.setText(String.valueOf(o.getLosses()));
            points.setText(String.valueOf(o.getPoints()));
            snk1.setText(String.valueOf(o.getSnack1()));
            snk2.setText(String.valueOf(o.getSnack2()));
        }
        return v;
    }
}