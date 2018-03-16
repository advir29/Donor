package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InetAddress;

public class Login extends AppCompatActivity {
    String email, password;
    public Button regButton, sign;
    EditText emailTxt,pwTxt;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    ConstraintLayout layout;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        sign = findViewById(R.id.loginBtn);
        regButton=findViewById(R.id.registerBtn);
        emailTxt = findViewById(R.id.email);
        pwTxt = findViewById(R.id.uPw1);
        emailTxt.requestFocus();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInternetAvailable()){
                    setDisable();
                    email = emailTxt.getText().toString().trim();
                    password = pwTxt.getText().toString().trim();
                    try{
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    final String id=mAuth.getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference();
                                    databaseReference.child("client").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.exists()){
                                                layout.setVisibility(View.INVISIBLE);
                                                startActivity(new Intent(Login.this, MainPage.class).putExtra("ID",id));
                                            }else{
                                                databaseReference = FirebaseDatabase.getInstance().getReference();
                                                databaseReference.child("blood_bank").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()) {
                                                            layout.setVisibility(View.INVISIBLE);
                                                            startActivity(new Intent(Login.this, bank_MainPage.class));
                                                        } else {
                                                            databaseReference = FirebaseDatabase.getInstance().getReference();
                                                            databaseReference.child("admin").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    if (dataSnapshot.exists()) {
                                                                        layout.setVisibility(View.INVISIBLE);
                                                                        startActivity(new Intent(Login.this, donor_admin.class));
                                                                    } else {
                                                                        layout.setVisibility(View.INVISIBLE);
                                                                        Toast.makeText(Login.this, "Records not found. Complete registration.", Toast.LENGTH_SHORT).show();
                                                                        String em = mAuth.getCurrentUser().getEmail();
                                                                        String tem = em.substring(em.length() - 9);
                                                                        if (tem.equalsIgnoreCase("@bank.com")) {
                                                                            layout.setVisibility(View.INVISIBLE);
                                                                            startActivity(new Intent(Login.this, bank_register.class));
                                                                        } else {
                                                                            layout.setVisibility(View.INVISIBLE);
                                                                            startActivity(new Intent(Login.this, RegisterForm.class));
                                                                        }
                                                                    }
                                                                }
                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    setEnable();
                                    Toast.makeText(Login.this, "Please Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                                    emailTxt.setText("");
                                    pwTxt.setText("");
                                    emailTxt.requestFocus();
                                }
                            }
                        });
                    }catch (Exception e){
                        setEnable();
                        Toast.makeText(Login.this, "Please Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                        emailTxt.setText("");
                        pwTxt.setText("");
                        emailTxt.requestFocus();
                    }
                }else{
                    Toast.makeText(Login.this, "Check internet connectivity.", Toast.LENGTH_LONG).show();
                }
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInternetAvailable()){
                    startActivity(new Intent(Login.this, Register.class));
                }else{
                    Toast.makeText(Login.this, "Check internet connectivity.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setEnable() {
        layout.setVisibility(View.INVISIBLE);
        sign.setEnabled(true);
        regButton.setEnabled(true);
        emailTxt.setEnabled(true);
        pwTxt.setEnabled(true);
    }

    private void setDisable() {
        layout.setVisibility(View.VISIBLE);
        sign.setEnabled(false);
        regButton.setEnabled(false);
        emailTxt.setEnabled(false);
        pwTxt.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isInternetAvailable()){
        }else{
            Toast.makeText(Login.this, "Check internet connectivity.", Toast.LENGTH_LONG).show();
        }

    }
    public boolean isInternetAvailable() {
        try{
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        }catch (Exception e){
            return  false;
        }
    }
    @Override
    public void onBackPressed() {

    }
}
