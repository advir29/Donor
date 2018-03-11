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

public class Login extends AppCompatActivity {
    String email, password;
    public Button regButton, sign;
    EditText emailTxt,pwTxt;
    private FirebaseAuth mAuth;
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
                                startActivity(new Intent(Login.this, MainPage.class));
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
}
