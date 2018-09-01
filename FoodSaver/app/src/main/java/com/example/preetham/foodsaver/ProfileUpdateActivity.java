package com.example.preetham.foodsaver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileUpdateActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseUser currentFirebaseUser;
    FirebaseDatabase database1;

    EditText user, addr, con, emailid;
    Button update, save;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));

        save = (Button) findViewById(R.id.savebutton);

        database1 = FirebaseDatabase.getInstance();

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = database1.getReference("Users").child(currentFirebaseUser.getUid());
        Toast.makeText(this, currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String contact = dataSnapshot.child("number").getValue().toString();
                String email = dataSnapshot.child("emailid").getValue().toString();

                //Run this now okay
                // Log.i("address,name",address+name);

                user = (EditText) findViewById(R.id.username);
                addr = (EditText) findViewById(R.id.address);
                con = (EditText) findViewById(R.id.contact);
                emailid = (EditText) findViewById(R.id.email);

                user.setText(name);
                emailid.setText(email);
                con.setText(contact);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(con.getText().toString()) || TextUtils.isEmpty(emailid.getText().toString()))
                        {
                            if (TextUtils.isEmpty(user.getText().toString())) {
                                user.setError("field cannot be empty");
                                user.requestFocus();
                            }
                            if (TextUtils.isEmpty(con.getText().toString())) {
                                con.setError("field cannot be empty");
                                con.requestFocus();
                            }

                            if (TextUtils.isEmpty(emailid.getText().toString())) {
                                emailid.setError("field cannot be empty");
                                emailid.requestFocus();
                            }
                        }
                        else {

                        updateuserinfo();
                    }}
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateuserinfo() {

        database1 = FirebaseDatabase.getInstance();
        myRef = database1.getReference("Users");
        currentFirebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        myRef.child(currentFirebaseUser.getUid()).child("name").setValue(user.getText().toString());
        myRef.child(currentFirebaseUser.getUid()).child("number").setValue(con.getText().toString());
        myRef.child(currentFirebaseUser.getUid()).child("emailid").setValue(emailid.getText().toString());
        Toast.makeText(this, "User Info Updated Successfully", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(ProfileUpdateActivity.this,HomeActivity.class);
        startActivity(i);
        this.finish();
    }
}
