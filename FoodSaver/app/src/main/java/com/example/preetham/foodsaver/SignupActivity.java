package com.example.preetham.foodsaver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button signin;
    EditText editTextemail,editTextpassword,edittextuser,edittextcontact;
    TextView nav_login;

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;

    FirebaseDatabase database1;
    DatabaseReference myRef1;
    FirebaseUser currentFirebaseUser;

    private void registerUser()
    {
        final String email=editTextemail.getText().toString();
        String password=editTextpassword.getText().toString();
        final String username= edittextuser.getText().toString().trim();
        final String contact= edittextcontact.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please enter a valid email");
            editTextemail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            editTextpassword.setError("Minimum lenght of password should be 6");
            editTextpassword.requestFocus();
            return;
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this,"User Successfully Registered",Toast.LENGTH_LONG).show();

                   database1 = FirebaseDatabase.getInstance();
                    myRef1 = database1.getReference("Users");
                    currentFirebaseUser=FirebaseAuth.getInstance().getCurrentUser();

                    Log.d("uid","cuid"+currentFirebaseUser.getUid());

                    myRef1.child(currentFirebaseUser.getUid()).child("name").setValue(username);
                    myRef1.child(currentFirebaseUser.getUid()).child("number").setValue(contact);
                    myRef1.child(currentFirebaseUser.getUid()).child("emailid").setValue(email);

                    Intent i =new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(i);

                }
                else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }}
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        signin=(Button)findViewById(R.id.signupbutton);
        editTextemail=(EditText)findViewById(R.id.email);
        editTextpassword=(EditText)findViewById(R.id.password);
        edittextuser =(EditText)findViewById(R.id.username);
        edittextcontact=(EditText)findViewById(R.id.contact);
        nav_login=(TextView)findViewById(R.id.loginnav);

        nav_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
}
