package com.fushi.mobile_final.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fushi.mobile_final.BoMonChiTiet;
import com.fushi.mobile_final.R;
import com.fushi.mobile_final.models.BoMonTab;

import java.util.ArrayList;

public class DanhSachBoMonAdapter extends ArrayAdapter<BoMonTab> {
    public DanhSachBoMonAdapter(Context context, ArrayList<BoMonTab> boMonTabs) {
        super(context, 0, boMonTabs);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final BoMonTab boMonTab = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_danh_sach_bo_mon, parent, false);
        }
        // Lookup view for data population
        TextView txtMaBoMon = (TextView) convertView.findViewById(R.id.maBoMon);
        TextView txtTenBoMon = (TextView) convertView.findViewById(R.id.tenBoMon);
        TextView txtTenKhoa = (TextView) convertView.findViewById(R.id.maKhoa);


//        TextView txtProductName = (TextView) convertView.findViewById(R.id.productName);
//        TextView txtProductPrice = (TextView) convertView.findViewById(R.id.productPrice);
//
//        // Populate the data into the template view using the data object
        txtMaBoMon.setText(boMonTab.getMaBoMon());
        txtTenBoMon.setText("Bộ môn: " + boMonTab.getTenBoMon());
        txtTenKhoa.setText("Mã khoa :" + boMonTab.getMaKhoa());


        TextView angle = convertView.findViewById(R.id.angle_right);
        angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BoMonChiTiet.class);
                intent.putExtra("maBoMon",boMonTab.getMaBoMon());
                getContext().startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
