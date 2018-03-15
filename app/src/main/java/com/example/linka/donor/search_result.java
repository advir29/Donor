package com.example.linka.donor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class search_result extends AppCompatActivity {
    ListView search_list;
    private RecordAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        search_list=findViewById(R.id.search_result);
        ArrayList<record> rec=getIntent().getParcelableArrayListExtra("key");
        adapter= new RecordAdapter(search_result.this,rec);
        search_list.setAdapter(adapter);
    }
}
