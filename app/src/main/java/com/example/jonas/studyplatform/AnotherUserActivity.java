package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class AnotherUserActivity extends AppCompatActivity {

    Button writeButton;

    TextView nameText;
    TextView eduText;
    TextView usernameText;
    TextView strong1Text;
    TextView strong2Text;
    TextView weak1Text;
    TextView weak2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);

        Bundle bundle = getIntent().getExtras();
        String data1 = bundle.getString("name");
        String data2 = bundle.getString("edu");
        String data3 = bundle.getString("username");
        String data4 = bundle.getString("strong1");
        String data5 = bundle.getString("strong2");
        String data6 = bundle.getString("weak1");
        String data7 = bundle.getString("weak2");

        nameText = (TextView) findViewById(R.id.displayNameTextView);
        nameText.setText(getString(R.string.name) + ": " + data1);

        eduText = (TextView) findViewById(R.id.displayEducationTextView);
        eduText.setText(getString(R.string.edu) + ": " + data2);

        usernameText = (TextView) findViewById(R.id.displayUsernameTextView);
        usernameText.setText(getString(R.string.username) + ": " + data3);

        strong1Text = (TextView) findViewById(R.id.displayStrong1TextView);
        strong1Text.setText(getString(R.string.strong) + ": " + data4);

        strong2Text = (TextView) findViewById(R.id.displayStrong2TextView);
        strong2Text.setText(getString(R.string.strong) + ": " + data5);

        weak1Text = (TextView) findViewById(R.id.displayWeak1TextView);
        weak1Text.setText(getString(R.string.weak) + ": " + data6);

        weak2Text = (TextView) findViewById(R.id.displayWeak2TextView);
        weak2Text.setText(getString(R.string.weak) + ": " + data7);



    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(AnotherUserActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.message:
                Intent intent1 = new Intent(AnotherUserActivity.this, PreChatActivity.class);
                startActivity(intent1);
                return true;

            case R.id.search:
                Intent intent2 = new Intent(AnotherUserActivity.this, Search.class);
                startActivity(intent2);
                return true;

            case R.id.home:
                Intent intent3 = new Intent(AnotherUserActivity.this, MainActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(AnotherUserActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(AnotherUserActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            case R.id.mypage:
                Intent intent7 = new Intent(AnotherUserActivity.this, MyPageActivity.class);
                startActivity(intent7);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
