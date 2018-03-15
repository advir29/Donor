package com.example.linka.donor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by linka on 15-03-2018.
 */

public class RecordAdapter extends BaseAdapter {
    Activity context;
    ArrayList<record> records;
    private static LayoutInflater inflater=null;
    public RecordAdapter(Activity context, ArrayList<record> records){
        this.context=context;
        this.records=records;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int i) {
        return records.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView= view;
        itemView =(itemView==null)?inflater.inflate(R.layout.record,null):itemView;
        TextView bank_a= itemView.findViewById(R.id.bank_a);
        TextView add_a= itemView.findViewById(R.id.add_a);
        TextView city_a= itemView.findViewById(R.id.city_a);
        TextView district_a= itemView.findViewById(R.id.district_a);
        TextView state_a= itemView.findViewById(R.id.state_a);
        TextView units_available= itemView.findViewById(R.id.units_available);
        record selectedRecord=records.get(i);
        bank_a.setText(selectedRecord.bname);
        add_a.setText(selectedRecord.address);
        city_a.setText(selectedRecord.city);
        district_a.setText(selectedRecord.district);
        state_a.setText(selectedRecord.state);
        units_available.setText("Available Units : "+selectedRecord.unit);
        return itemView;
    }
}
