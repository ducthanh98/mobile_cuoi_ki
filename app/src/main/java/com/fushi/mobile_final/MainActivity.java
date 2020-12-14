package com.fushi.mobile_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fushi.mobile_final.adapters.DanhSachBoMonAdapter;
import com.fushi.mobile_final.models.BoMonTab;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                Intent intent = new Intent(MainActivity.this, TaoBoMon.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.list_view);

        db = new DatabaseHelper(this);
        ArrayList<BoMonTab> boMonTabs = db.layDanhSachBoMon();

        DanhSachBoMonAdapter danhSachBoMonAdapter = new DanhSachBoMonAdapter(this,boMonTabs);

        listView.setAdapter(danhSachBoMonAdapter);
    }
}