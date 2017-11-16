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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText searchEdit;
    Button searchButton;
    ListView searchListView;

    private FirebaseAuth mAuth;

    ArrayList<User> userList;
    ArrayList<User> strongList;

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

        searchEdit = (EditText) findViewById(R.id.searchEditText);
        searchButton = (Button) findViewById(R.id.searchUserButton);
        searchListView = (ListView) findViewById(R.id.searchListView);

        userList = new ArrayList<User>();
        strongList = new ArrayList<User>();

        mAuth = FirebaseAuth.getInstance();

        final UserAdapter userAdapter = new UserAdapter(this, strongList);
        final ListView listView = (ListView) findViewById(R.id.searchListView);
        listView.setAdapter(userAdapter);

        myRef = FirebaseDatabase.getInstance().getReference().child("users");





        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userResult = searchEdit.getText().toString().toLowerCase();

                userAdapter.clear();
                for(User user: userList){
                    if (user.getStrong1().equals(userResult))
                    {
                        userAdapter.add(user);
                    }
                    if (user.getStrong2().equals(userResult))
                    {
                        userAdapter.add(user);
                    }
                    if (user.getUsername().equals(userResult))
                    {
                        userAdapter.add(user);
                    }
                }

                searchEdit.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User u = (User) listView.getItemAtPosition(position);

                Intent intent = new Intent(Search.this, AnotherUserActivity.class);
                intent.putExtra("name", u.getName());
                intent.putExtra("edu", u.getEducation());
                intent.putExtra("username", u.getUsername());
                intent.putExtra("strong1", u.getStrong1());
                intent.putExtra("strong2", u.getStrong2());
                intent.putExtra("weak1", u.getWeak1());
                intent.putExtra("weak2", u.getWeak2());
                startActivity(intent);

                //what to do - Sent key to AnotherUserActivity and open this activity


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
}
