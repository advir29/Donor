package com.example.linka.donor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterForm extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Spinner blood_gp;
    EditText first_name, last_name, height, weight, age;
    public Button launch;
    DatabaseReference db;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        db=FirebaseDatabase.getInstance().getReference();
        rg = findViewById(R.id.rbGroup);
        blood_gp=findViewById(R.id.b_group);
        launch = findViewById(R.id.subButton);
        first_name=findViewById(R.id.fn);
        last_name=findViewById(R.id.ln);
        height=findViewById(R.id.ht);
        weight=findViewById(R.id.wt);
        age=findViewById(R.id.age);
        blood_gp=findViewById(R.id.b_group);
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserInfo();
            }
        });
    }
    private  void addUserInfo(){
        String f_name = first_name.getText().toString().trim();
        String l_name = last_name.getText().toString().trim();
        String ht = height.getText().toString().trim();
        String wt = weight.getText().toString().trim();
        String ag =age.getText().toString().trim();
        rg=findViewById(R.id.rbGroup);
        int idSelect=rg.getCheckedRadioButtonId();
        rb=findViewById(idSelect);
        String gender=rb.getText().toString();
        blood_gp=findViewById(R.id.b_group);
        String b_gp=blood_gp.getSelectedItem().toString();
        String uni="0";
        if(!TextUtils.isEmpty(f_name)&&!TextUtils.isEmpty(l_name)&&!TextUtils.isEmpty(ht)&&!TextUtils.isEmpty(wt)&&!TextUtils.isEmpty(ag)&&!TextUtils.isEmpty(gender)){
            String id=mAuth.getInstance().getCurrentUser().getUid();
            b_group b_gup=new b_group(f_name,l_name,ht,wt,ag,gender,b_gp,uni);
            try{
                db.child("client").child(id).setValue(b_gup);
                startActivity(new Intent(RegisterForm.this, MainPage.class));
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