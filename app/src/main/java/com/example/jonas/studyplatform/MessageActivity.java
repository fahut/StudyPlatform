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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    ImageButton mSendButton;
    private FirebaseAuth mAuth;
    EditText messageEditText;
    ListView lstView;
    ArrayList<String> exList;
    ArrayAdapter<String> itemsAdapter;
    DatabaseReference newRef;
    DatabaseReference myRef;
    User currentUser;

    ValueEventListener ev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);

        mAuth = FirebaseAuth.getInstance();

        mSendButton = (ImageButton) findViewById(R.id.sendMessageButton);
        messageEditText = (EditText) findViewById(R.id.messageEditText);

        Bundle bundle = getIntent().getExtras();
        String data1 = bundle.getString("course");

        newRef = FirebaseDatabase.getInstance().getReference().child("Chat").child(data1);

        mAuth = FirebaseAuth.getInstance();

        exList = new ArrayList<String>();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exList);

        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

        lstView = (ListView) findViewById(R.id.msgListView);
        lstView.setAdapter(itemsAdapter);


        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message friendlyMessage = new Message(currentUser.getUsername(), messageEditText.getText().toString());
                if (friendlyMessage.getPost().equals("")) {
                    Toast.makeText(MessageActivity.this, getString(R.string.empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    newRef.push().setValue(friendlyMessage);
                    messageEditText.setText("");
                }


            }
        });


        newRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message data1 = (Message) dataSnapshot.getValue(Message.class);

                itemsAdapter.add(data1.toString());
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

        ev = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                currentUser = dataSnapshot.getValue(User.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        myRef.addValueEventListener(ev);
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
                Intent intent = new Intent(MessageActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent7 = new Intent(MessageActivity.this, PreChatActivity.class);
                startActivity(intent7);
                return true;

            case R.id.home:
                Intent intent1 = new Intent(MessageActivity.this, MainActivity.class);
                startActivity(intent1);
                return true;

            case R.id.search:
                Intent intent2 = new Intent(MessageActivity.this, Search.class);
                startActivity(intent2);
                return true;

            case R.id.mypage:
                Intent intent3 = new Intent(MessageActivity.this, MyPageActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(MessageActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(MessageActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    };
}
