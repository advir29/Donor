package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    String email, password;
    EditText emailTxt,pwTxt;
    TextView signIn,jump;
    private FirebaseAuth mAuth;
    public Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signIn=findViewById(R.id.sign);
        mAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.uEmail);
        pwTxt = findViewById(R.id.uuPw);
        emailTxt.requestFocus();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        jump=findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, bank_register.class));
            }
        });


        submit = findViewById(R.id.reg);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=emailTxt.getText().toString().trim();
                password=pwTxt.getText().toString().trim();
                try{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(Register.this, RegisterForm.class));
                            } else {
                                Toast.makeText(Register.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                emailTxt.setText("");
                                pwTxt.setText("");
                                emailTxt.requestFocus();
                            }
                        }
                    });
                }catch(Exception e) {
                    Toast.makeText(Register.this, "Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
