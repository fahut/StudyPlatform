package com.example.jonas.studyplatform;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Jonas on 07/10/2017.
 */

public class MessageAdapter extends ArrayAdapter<Chats> {

        public MessageAdapter(Activity context, ArrayList<Chats> messages)
        {
            super(context,0,messages);

        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.chatbubble,parent,false);
        }
        Chats currentMessage = getItem(position);

        return listItemView;

    }
}
