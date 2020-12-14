package com.fushi.mobile_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fushi.mobile_final.adapters.DanhSachBoMonAdapter;
import com.fushi.mobile_final.adapters.DanhSachMonHocAdapter;
import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.MonHocTab;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DanhSachMonHoc extends AppCompatActivity {
    ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_hoc);


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        TextView name = view.findViewById(R.id.tao_bo_mon);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachMonHoc.this, TaoMonHoc.class);
                startActivity(intent);
            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Danh sách môn học");

        listView = (ListView) findViewById(R.id.list_view);

        db = new DatabaseHelper(this);
        ArrayList<MonHocTab> monHocTabs = db.layDanhSachMonHoc();

        DanhSachMonHocAdapter danhSachMonHocAdapter = new DanhSachMonHocAdapter(this,monHocTabs);

        listView.setAdapter(danhSachMonHocAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.mon_hoc);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.bo_mon: {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }

                    case R.id.mon_hoc: {
                        startActivity(new Intent(getApplicationContext(),DanhSachMonHoc.class));
                        overridePendingTransition(0,0);
                        return true;
                    }


                }

                return false;
            }
        });

    }
}