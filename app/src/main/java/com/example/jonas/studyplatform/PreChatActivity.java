package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PreChatActivity extends AppCompatActivity {


    ListView chatListView;

    ArrayList<String> channelList;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);

        chatListView = (ListView) findViewById(R.id.chatListView);

        myRef = FirebaseDatabase.getInstance().getReference().child("Chat");

        channelList = new ArrayList<String>();

        final ArrayAdapter channelAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, channelList);
        final ListView listView = (ListView) findViewById(R.id.searchListView);
        listView.setAdapter(channelAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(PreChatActivity.this, MessageActivity.class);
                intent.putExtra("course", s);
                startActivity(intent);


            }


        });




        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String data2 = (String) dataSnapshot.getKey();

                channelList.add(data2);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings:
                Intent intent = new Intent(PreChatActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(PreChatActivity.this, PreChatActivity.class);
                startActivity(intent1);
                return true;

            case R.id.home:
                Intent intent2 = new Intent(PreChatActivity.this, MainActivity.class);
                startActivity(intent2);
                return true;

            case R.id.mypage:
                Intent intent3 = new Intent(PreChatActivity.this, MyPageActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(PreChatActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(PreChatActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            case R.id.search:
                Intent intent7 = new Intent(PreChatActivity.this, Search.class);
                startActivity(intent7);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    };



}
