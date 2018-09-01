package com.example.preetham.foodsaver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Donate_NGO_Activity extends AppCompatActivity {
EditText name,contact,amount;
Button submit;
    ActionBar actionBar;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate__ngo_);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));

        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.contact);
        amount=(EditText)findViewById(R.id.amount);
        submit=(Button) findViewById(R.id.donate);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ngo_activity");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(contact.getText().toString()) || TextUtils.isEmpty(amount.getText().toString()) )
                {
                    if (TextUtils.isEmpty(name.getText().toString())) {
                        name.setError("field cannot be empty");
                        name.requestFocus();
                    }
                    if (TextUtils.isEmpty(contact.getText().toString())) {
                        contact.setError("field cannot be empty");
                        contact.requestFocus();
                    }

                    if (TextUtils.isEmpty(amount.getText().toString())) {
                        amount.setError("field cannot be empty");
                        amount.requestFocus();
                    }

                }
                else {
                final String name1=name.getText().toString();
                String contact2=contact.getText().toString();
                String amount2=amount.getText().toString();
                final ngoactivity add = new ngoactivity(name1,contact2,amount2);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(String.valueOf(name1)).exists()) {
                            // run some code
                            Toast.makeText(getApplicationContext(), "Detils Already Added", Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.push().setValue(add);

                            Toast.makeText(getApplicationContext(), "Details Added successfully", Toast.LENGTH_SHORT).show();
//                            Intent i=new Intent(getApplicationContext(),HomeActivity.class);
//                            startActivity(i);
                            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                            if (launchIntent != null) {
                                startActivity(launchIntent);//null pointer check in case package name was not found
                            }
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }}
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
