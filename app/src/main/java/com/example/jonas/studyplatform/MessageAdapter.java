package com.example.jonas.studyplatform;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonas on 23/11/2017.
 */



public class MessageAdapter extends ArrayAdapter {
    public  MessageAdapter(Activity context, ArrayList<String> messages){
        super(context,0,messages);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;

        int type = getItemViewType(position);

        if(type==1) {
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false);
            }
        }
        else
        {
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item2, parent, false);
            }
        }

        String str = (String) getItem(position);
        List<String> list = Arrays.asList(str.split(":"));
        String currentMessage = list.get(1);
        String currentUser = list.get(0);
        String uid = list.get(2);


        TextView userText = (TextView) listItemView.findViewById(R.id.userMessage);
        userText.setText(currentUser + ":");
        TextView ChatText = (TextView) listItemView.findViewById(R.id.txtMessage);
        ChatText.setText("" + currentMessage);


        return listItemView;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        String str = (String) getItem(position);
        List<String> list = Arrays.asList(str.split(":"));

        String uid2 = list.get(2);
        String ivan = PreChatActivity.getUs();

        if (ivan.equals(uid2)) {

            return 0;
        }
        else {
            return 1;
        }
    }



}
