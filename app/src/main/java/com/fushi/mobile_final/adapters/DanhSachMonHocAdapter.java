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
import com.fushi.mobile_final.MonHocChiTiet;
import com.fushi.mobile_final.R;
import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.MonHocTab;

import java.util.ArrayList;

public class DanhSachMonHocAdapter  extends ArrayAdapter<MonHocTab> {
    public DanhSachMonHocAdapter(Context context, ArrayList<MonHocTab> monHocTabs) {
        super(context, 0, monHocTabs);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final MonHocTab monHocTab = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_danh_sach_mon_hoc_view, parent, false);
        }
        TextView txtMaMonHoc = (TextView) convertView.findViewById(R.id.maMonHoc);
        TextView txtTenMonHoc = (TextView) convertView.findViewById(R.id.tenMonHoc);
        TextView txtTenBoMon = (TextView) convertView.findViewById(R.id.maBoMon);


//        TextView txtProductName = (TextView) convertView.findViewById(R.id.productName);
//        TextView txtProductPrice = (TextView) convertView.findViewById(R.id.productPrice);
//
//        // Populate the data into the template view using the data object
        txtMaMonHoc.setText(monHocTab.getMaMonHoc());
        txtTenMonHoc.setText("Môn : " + monHocTab.getTenMonHoc());
        txtTenBoMon.setText("Bộ môn :" + monHocTab.getMaBoMon());


        TextView angle = convertView.findViewById(R.id.angle_right);
        angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MonHocChiTiet.class);
                intent.putExtra("maMonHoc",monHocTab.getMaMonHoc());
                getContext().startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
