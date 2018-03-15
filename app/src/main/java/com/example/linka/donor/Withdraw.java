package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class Withdraw extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    TextView ttf1,ttf2;
    FirebaseAuth mAuth;
    Button withdrawBtn;
    Spinner b_groupW;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ttf1=findViewById(R.id.bUI);
        ttf2=findViewById(R.id.Wuni);
        withdrawBtn=findViewById(R.id.withdrawBtn);
        mAuth = FirebaseAuth.getInstance();
        b_groupW=findViewById(R.id.b_groupW);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home: {
                        Intent in = new Intent(Withdraw.this, MainPage.class);
                        startActivity(in);
                        break;
                    }case R.id.search:{
                        Intent in= new Intent(Withdraw.this, Search.class);
                        startActivity(in);break;
                    }case R.id.history:{
                        Intent in=new Intent(Withdraw.this, History.class);
                        startActivity(in);break;
                    }case R.id.transfer:{
                        Intent in= new Intent(Withdraw.this, Transfer.class);
                        startActivity(in);break;
                    }case R.id.withdraw:{
                        Intent in= new Intent(Withdraw.this, Withdraw.class);
                        startActivity(in);break;
                    }case R.id.logout:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(Withdraw.this, Login.class);
                        startActivity(in);break;
                    }}
                return false;
            }
        });
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tf1=ttf1.getText().toString();
                final String tf2=ttf2.getText().toString();
                final String b_gpW=b_groupW.getSelectedItem().toString();
                if(!TextUtils.isEmpty(tf1)&&!TextUtils.isEmpty(tf2)){
                    final String id=mAuth.getCurrentUser().getUid();
                    databaseReference.child("client").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final String unit = dataSnapshot.child(id).child("units").getValue(String.class);
                            final String toUni =dataSnapshot.child(tf1).child("units").getValue(String.class);
                            int units=Integer.parseInt(unit);
                            int toUn=Integer.parseInt(toUni);
                            int tttf2=Integer.parseInt(ttf2.getText().toString());
                            if(units>=tttf2){
                                String datetime= DateFormat.getTimeInstance().format(new Date());
                                transaction tr=new transaction(datetime,tf1,id,"w",tf2);
                                int Nunit=units-tttf2;
                                int NNuni=toUn+tttf2;
                                String Nuni=Integer.toString(Nunit);
                                String NNun=Integer.toString(NNuni);
                                databaseReference.child("transaction").push().setValue(tr);
                                databaseReference.child("client").child(id).child("units").setValue(Nuni);
                                databaseReference.child("client").child(tf1).child("units").setValue(NNun);
                            }else{
                                Toast.makeText(Withdraw.this,"You don't have sufficient credits.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }else{
                    Toast.makeText(Withdraw.this,"Please fill all fields.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
