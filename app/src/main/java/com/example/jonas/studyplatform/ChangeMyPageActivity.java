package com.example.jonas.studyplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.jonas.studyplatform.R.id.editButton;

public class ChangeMyPageActivity extends AppCompatActivity {

    Button update2Button;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;

    EditText changeName;
    EditText changeEducation;
    EditText changeUsername;
    EditText changeStrong1;
    EditText changeStrong2;
    EditText changeWeak1;
    EditText changeWeak2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_page);

        myRef = FirebaseDatabase.getInstance().getReference();

        changeName = (EditText) findViewById(R.id.changeNameEditText);
        changeEducation = (EditText) findViewById(R.id.changeEducationEditText);
        changeUsername = (EditText) findViewById(R.id.changeUsernameEditText);
        changeStrong1 = (EditText) findViewById(R.id.changeStrong1EditText);
        changeStrong2 = (EditText) findViewById(R.id.changeStrong2EditText);
        changeWeak1 = (EditText) findViewById(R.id.changeWeak1EditText);
        changeWeak2 = (EditText) findViewById(R.id.changeWeak2EditText) ;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.mipmap.imageedit_1_9052204102);

        mAuth = FirebaseAuth.getInstance();


        update2Button = (Button) findViewById(editButton);
        update2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String cn = changeName.getText().toString().toUpperCase();
                String ced = changeEducation.getText().toString().toUpperCase();
                String cu = changeUsername.getText().toString().toUpperCase();
                String cs1 = changeStrong1.getText().toString().toUpperCase();
                String cs2 = changeStrong2.getText().toString().toUpperCase();
                String cw1 = changeWeak1.getText().toString().toUpperCase();
                String cw2 = changeWeak2.getText().toString().toUpperCase();

                User user = new User(cu, cn, ced, cs1, cs2, cw1, cw2, mAuth.getCurrentUser().getUid());
                updateCurrentUser(user);

                Intent i = new Intent(ChangeMyPageActivity.this, MyPageActivity.class);
                startActivity(i);
            }

        });

        DatabaseReference userRef2 = myRef.child("users").child(mAuth.getCurrentUser().getUid());

        userRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);


                    changeName.setText(currentUser.getName());
                    changeEducation.setText(currentUser.getEducation());
                    changeUsername.setText(currentUser.getUsername());
                    changeStrong1.setText(currentUser.getStrong1());
                    changeStrong2.setText(currentUser.getStrong2());
                    changeWeak1.setText(currentUser.getWeak1());
                    changeWeak2.setText(currentUser.getWeak2());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateCurrentUser(User newUser) {
        String username = newUser.getUsername();
        String education = newUser.getEducation();
        String name = newUser.getName();
        String strong1 = newUser.getStrong1();
        String strong2 = newUser.getStrong2();
        String weak1 = newUser.getWeak1();
        String weak2 = newUser.getWeak2();

        User user = new User(username, name, education, strong1, strong2, weak1, weak2, mAuth.getCurrentUser().getUid());

        if(mAuth == null) return;
        myRef.child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);

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
                Intent intent = new Intent(ChangeMyPageActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.home:
                Intent intent1 = new Intent(ChangeMyPageActivity.this, MainActivity.class);
                startActivity(intent1);
                return true;

            case R.id.search:
                Intent intent2 = new Intent(ChangeMyPageActivity.this, Search.class);
                startActivity(intent2);
                return true;

            case R.id.mypage:
                Intent intent3 = new Intent(ChangeMyPageActivity.this, MyPageActivity.class);
                startActivity(intent3);
                return true;

            case R.id.signoff:
                Intent intent4 = new Intent(ChangeMyPageActivity.this, SignOffActivity.class);
                startActivity(intent4);
                return true;

            case R.id.message:
                Intent intent5 = new Intent(ChangeMyPageActivity.this, MessageActivity.class);
                startActivity(intent5);
                return true;

            case R.id.about:
                Intent intent6 = new Intent(ChangeMyPageActivity.this, AboutActivity.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    };
}
