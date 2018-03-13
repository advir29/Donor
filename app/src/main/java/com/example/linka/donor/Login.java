package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    String email, password;
    public Button regButton, sign;
    EditText emailTxt,pwTxt;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String id="a";
    String id2="b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        sign = findViewById(R.id.loginBtn);
        emailTxt = findViewById(R.id.email);
        pwTxt = findViewById(R.id.uPw1);
        emailTxt.requestFocus();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                            startActivity(new Intent(Login.this, MainPage.class));
                                        }else{
                                            databaseReference = FirebaseDatabase.getInstance().getReference();
                                            databaseReference.child("blood_bank").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.exists()){
                                                        startActivity(new Intent(Login.this, bank_MainPage.class));
                                                    }else{
                                                        Toast.makeText(Login.this,"Records not found. Complete registration.",Toast.LENGTH_SHORT).show();
                                                        String em=mAuth.getCurrentUser().getEmail();
                                                        String tem=em.substring(em.length()-8);
                                                        if(tem.equalsIgnoreCase("bank.com")){
                                                            startActivity(new Intent(Login.this, bank_register.class));
                                                        }else{
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
                            } else {
                                Toast.makeText(Login.this, "Please Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                                emailTxt.setText("");
                                pwTxt.setText("");
                                emailTxt.requestFocus();
                            }
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(Login.this, "Please Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                    emailTxt.setText("");
                    pwTxt.setText("");
                    emailTxt.requestFocus();
                }
            }

        });
        regButton=findViewById(R.id.registerBtn);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
