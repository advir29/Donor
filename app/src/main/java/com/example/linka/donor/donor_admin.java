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

public class donor_admin extends AppCompatActivity {
    String email,pw;
    EditText email_t,pw_t;
    private FirebaseAuth mAuth;
    Button regi,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_admin);
        mAuth = FirebaseAuth.getInstance();
        email_t=findViewById(R.id.admin_bank_email);
        pw_t=findViewById(R.id.admin_bank_password);
        regi=findViewById(R.id.admin_register);
        logout=findViewById(R.id.admin_logout);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=email_t.getText().toString().trim();
                pw=pw_t.getText().toString().trim();
                try{
                    mAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(donor_admin.this, "New blood bank added.", Toast.LENGTH_SHORT).show();
                                email_t.setText("");
                                pw_t.setText("");
                                email_t.requestFocus();
                            } else {
                                Toast.makeText(donor_admin.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                email_t.setText("");
                                pw_t.setText("");
                                email_t.requestFocus();
                            }
                        }
                    });
                }catch(Exception e) {
                    Toast.makeText(donor_admin.this, "Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(donor_admin.this,Login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
