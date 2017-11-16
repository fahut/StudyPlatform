package com.example.jonas.studyplatform;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonas on 15/11/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    public  UserAdapter(Activity context, ArrayList<User> users){
        super(context,0,users);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        User currentUser = getItem(position);


        ImageView icon = (ImageView) listItemView.findViewById(R.id.icon);

        //Hardcoded strings here cannot be converted

        TextView userNameTextView = (TextView) listItemView.findViewById(R.id.name);
        userNameTextView.setText("Username: " + currentUser.getUsername());

        TextView strong1TextView = (TextView) listItemView.findViewById(R.id.strong1);
        strong1TextView.setText("Can teach: " + currentUser.getStrong1());

        TextView strong2TextView = (TextView) listItemView.findViewById(R.id.strong2);
        strong2TextView.setText("Can teach: " + currentUser.getStrong2());


        return listItemView;

    }



}
