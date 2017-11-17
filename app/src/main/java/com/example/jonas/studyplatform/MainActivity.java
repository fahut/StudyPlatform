
package com.example.jonas.studyplatform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.jonas.studyplatform.R.id.nameTextView;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    RelativeLayout layoutHome;
    ValueEventListener ev;
    DatabaseReference userRef;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView userName;
        userName = (TextView) findViewById(nameTextView);

        TextView velkommen = (TextView)findViewById(R.id.welcomeTextView);

        final TextView tvCount = (TextView)findViewById(R.id.numberOfUsersTV);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);



        myRef = FirebaseDatabase.getInstance().getReference().child("users");

        mAuth = FirebaseAuth.getInstance();


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        //getCurrentUser
        userRef = myRef.child(mAuth.getCurrentUser().getUid());


        ImageButton facebookBtn = (ImageButton)findViewById(R.id.faceBtn);
        ImageButton twitterBtn = (ImageButton)findViewById(R.id.twitterBtn);



        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=/https://www.facebook.com/viauniversitycollege/"));
                    startActivity(intent);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("\"fb://page/https://www.facebook.com/viauniversitycollege/")));
                }
            }
        });




        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=/https://twitter.com/viauniversity"));
                    startActivity(intent);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("\"fb://page/https://https://twitter.com/viauniversity/")));
                }
            }
        });


        myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                            long count = dataSnapshot
                                                    .child("users")
                                                    .getChildrenCount();

                                            tvCount.setText("There are currently: " + String.valueOf(count) + " users of SocialEd");

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

                User currentUser = dataSnapshot.getValue(User.class);

                userName.setText(currentUser.getName());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        userRef.addValueEventListener(ev);

    }



    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        String storeColor = spf.getString(getString(R.string.key_color),"#2bff00");
        layoutHome = (RelativeLayout)findViewById(R.id.layoutHome);
        layoutHome.setBackgroundColor(Color.parseColor(storeColor));

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
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(MainActivity.this, PreChatActivity.class);
                startActivity(intent1);
                return true;

            case R.id.search:
                Intent intent2 = new Intent(MainActivity.this, Search.class);
                startActivity(intent2);
                return true;

            case R.id.mypage:
                Intent intent3 = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(MainActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    };
}
