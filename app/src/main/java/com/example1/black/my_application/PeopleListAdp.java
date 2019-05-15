package com.example1.black.my_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeopleListAdp extends ArrayAdapter<People> {

    ArrayList<People> peopleList;

    public PeopleListAdp(Context context,  ArrayList<People> peopleList) {
        super(context,R.layout.single_row,peopleList);
        this.peopleList = peopleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.single_row,parent,false);

        TextView nameT = (TextView) customView.findViewById(R.id.nameT);
        TextView descT = (TextView) customView.findViewById(R.id.descT);

        ImageView peopleimg = (ImageView) customView.findViewById(R.id.peopleimg1);

        nameT.setText(peopleList.get(position).name);
        descT.setText(
                "Popularity: " + peopleList.get(position).popularity);

        Picasso.get().load(peopleList.get(position).imagepath).into(peopleimg);


        return customView;
    }

    public PeopleListAdp(Context context, int resource) {
        super(context, R.layout.single_row ,resource);
    }
}



