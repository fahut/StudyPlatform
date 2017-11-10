package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Search extends AppCompatActivity {


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings:
                Intent intent = new Intent(Search.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(Search.this, MessageActivity.class);
                startActivity(intent1);
                return true;

            case R.id.home:
                Intent intent2 = new Intent(Search.this, MainActivity.class);
                startActivity(intent2);
                return true;

            case R.id.mypage:
                Intent intent3 = new Intent(Search.this, MyPageActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(Search.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(Search.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    };

    EditText searchEdit;
    Button searchButton;
    ListView searchListView;

    ArrayList<User> userList;
    ArrayList<User> strongList;

    ArrayAdapter<User> searchAdapter;
    ArrayAdapter<User> strongAdapter;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        searchEdit = (EditText) findViewById(R.id.searchEditText);
        searchButton = (Button) findViewById(R.id.searchUserButton);
        searchListView = (ListView) findViewById(R.id.searchListView);

        userList = new ArrayList<User>();
        strongList = new ArrayList<User>();

        strongAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, strongList);

        myRef = FirebaseDatabase.getInstance().getReference().child("users");

        searchListView.setAdapter(strongAdapter);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userResult = searchEdit.getText().toString().toLowerCase();

                strongAdapter.clear();
                for(User user: userList){
                    if (user.getStrong1().equals(userResult))
                    {
                        strongAdapter.add(user);
                    }
                    if (user.getStrong2().equals(userResult))
                    {
                        strongAdapter.add(user);
                    }
                }

                searchEdit.setText("");
            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User data2 = (User) dataSnapshot.getValue(User.class);

                userList.add(data2);
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
}
