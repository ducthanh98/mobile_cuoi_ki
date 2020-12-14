package com.fushi.mobile_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.MonHocTab;

import java.util.ArrayList;
import java.util.List;

public class BoMonChiTiet extends AppCompatActivity {
    DatabaseHelper db;
    BoMonTab boMonTab = new BoMonTab();
    List<MonHocTab> monHocTabs = new ArrayList<MonHocTab>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_mon_chi_tiet);

        db = new DatabaseHelper(this);
        Bundle b = getIntent().getExtras();
        String maBoMon = (String) b.get("maBoMon");

        boMonTab = db.layBoMon(maBoMon);
        monHocTabs = db.layDanhSachMonHoc(maBoMon);
        Log.d("log","log");

    }
}