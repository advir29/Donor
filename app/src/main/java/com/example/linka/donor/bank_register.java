package com.example.linka.donor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bank_register extends AppCompatActivity {
    Button bank;
    EditText bank_name,add1,city_name,district_name,state_name;
    FirebaseAuth mAuth;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_register);
        db=FirebaseDatabase.getInstance().getReference();
        bank=findViewById(R.id.bank_sub);
        bank_name=findViewById(R.id.bank_name);
        add1=findViewById(R.id.add1);
        city_name=findViewById(R.id.city_name);
        district_name=findViewById(R.id.district_name);
        state_name=findViewById(R.id.state_name);
        bank_name.requestFocus();
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBankInfo();
                startActivity(new Intent(bank_register.this, bank_MainPage.class));
            }
        });
    }

    private void addBankInfo() {
        String b_name=bank_name.getText().toString().trim();
        String ad1=add1.getText().toString().trim();
        String c_name=city_name.getText().toString().trim();
        String d_name=district_name.getText().toString().trim();
        String s_name=state_name.getText().toString().trim();
        if(!TextUtils.isEmpty(b_name)&&!TextUtils.isEmpty(ad1)&&!TextUtils.isEmpty(c_name)&&!TextUtils.isEmpty(d_name)&&!TextUtils.isEmpty(s_name)){
            String id=mAuth.getInstance().getCurrentUser().getUid();
            bank bb=new bank(b_name,ad1,c_name,d_name,s_name,"0","0","0","0","0","0","0","0","0");
            try{
                db.child("blood_bank").child(id).setValue(bb);
                startActivity(new Intent(bank_register.this, bank_MainPage.class));
            }catch (Exception e){
                Toast.makeText(this,"Something Went Wrong. Try again.",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please fill all the required fields.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
