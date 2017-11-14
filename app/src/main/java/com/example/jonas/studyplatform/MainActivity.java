package com.example.jonas.studyplatform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;

import static com.example.jonas.studyplatform.R.id.layoutHome;
import static com.example.jonas.studyplatform.R.id.nameTextView;
import static com.example.jonas.studyplatform.R.xml.preferences;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutHome;

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(MainActivity.this, MessageActivity.class);
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        

        TextView userName;
        userName = (TextView) findViewById(nameTextView);

        TextView velkommen = (TextView)findViewById(R.id.welcomeTextView);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.drawable.bubble1);







        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        //getCurrentUser
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name2 = user.getDisplayName();
            String email2 = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            userName.setText(email2);
        }


        //set up textviews




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
}
