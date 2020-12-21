package com.fushi.mobile_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.MonHocTab;

import java.util.ArrayList;
import java.util.List;

public class BoMonChiTiet extends AppCompatActivity {
    DatabaseHelper db;
    BoMonTab boMonTab = new BoMonTab();
    String maBoMon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_mon_chi_tiet);


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_tao_bo_mon);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        TextView title = view.findViewById(R.id.title);
        title.setText("Bô môn chi tiết");



        db = new DatabaseHelper(this);
        Bundle b = getIntent().getExtras();
        maBoMon = (String) b.get("maBoMon");

        boMonTab = db.layBoMon(maBoMon);
        String tenKhoa = db.layKhoa(boMonTab.getMaKhoa()).getTenKhoa();


        ((TextView)findViewById(R.id.maBoMon)).setText(boMonTab.getMaBoMon());
        ((TextView)findViewById(R.id.tenBoMon)).setText(boMonTab.getTenBoMon());
        ((TextView)findViewById(R.id.khoa)).setText(tenKhoa);
        ((TextView)findViewById(R.id.moTa)).setText(boMonTab.getMoTa());

        Button btnTao = (Button)findViewById(R.id.btnChinhSua);
        Button btnXoa = (Button)findViewById(R.id.btnXoa);
        TextView btnBack = (TextView) findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoMonChiTiet.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoMonChiTiet.this,TaoBoMon.class);
                intent.putExtra("maBoMon",boMonTab.getMaBoMon());
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoMonChiTiet.this.xacNhan();
            }
        });

    }


    private void xacNhan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(BoMonChiTiet.this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn xóa");
        builder.setPositiveButton("Xác nhận",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BoMonChiTiet.this.db.xoaBoMon(BoMonChiTiet.this.maBoMon);
                        BoMonChiTiet.this.troLaiDanhSach();
                    }
                });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void troLaiDanhSach(){
        Intent intent = new Intent(BoMonChiTiet.this,MainActivity.class);
        startActivity(intent);
    }

}