package com.fushi.mobile_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fushi.mobile_final.models.MonHocTab;

public class MonHocChiTiet extends AppCompatActivity {
    DatabaseHelper db;
    String maMonHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc_chi_tiet);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_tao_bo_mon);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        TextView title = view.findViewById(R.id.title);
        title.setText("Môn học chi tiết");



        db = new DatabaseHelper(this);
        Bundle b = getIntent().getExtras();
        maMonHoc = (String) b.get("maMonHoc");

        final MonHocTab monHocTab = db.layMonHoc(maMonHoc);
        String tenBoMon = db.layBoMon(monHocTab.getMaBoMon()).getTenBoMon();

        ((TextView)findViewById(R.id.maMonHoc)).setText(monHocTab.getMaMonHoc());
        ((TextView)findViewById(R.id.tenMonHoc)).setText(monHocTab.getTenMonHoc());
        ((TextView)findViewById(R.id.maBoMon)).setText(monHocTab.getMaBoMon() + " - "+ tenBoMon);
        ((TextView)findViewById(R.id.soTinChi)).setText(monHocTab.getSoTinChi().toString());
        ((TextView)findViewById(R.id.soTiet)).setText(monHocTab.getSoTiet().toString());
        ((TextView)findViewById(R.id.moTa)).setText(monHocTab.getMoTa());

        Button btnTao = (Button)findViewById(R.id.btnChinhSua);
        Button btnXoa = (Button)findViewById(R.id.btnXoa);
        TextView btnBack = (TextView) findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonHocChiTiet.this,DanhSachMonHoc.class);
                startActivity(intent);
            }
        });

        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonHocChiTiet.this,TaoMonHoc.class);
                intent.putExtra("maMonHoc",monHocTab.getMaMonHoc());
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonHocChiTiet.this.xacNhan();
            }
        });

    }

    private void xacNhan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MonHocChiTiet.this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn xóa");
        builder.setPositiveButton("Xác nhận",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MonHocChiTiet.this.db.xoaMonHoc(MonHocChiTiet.this.maMonHoc);
                        MonHocChiTiet.this.troLaiDanhSach();
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
        Intent intent = new Intent(MonHocChiTiet.this,DanhSachMonHoc.class);
        startActivity(intent);
    }
}