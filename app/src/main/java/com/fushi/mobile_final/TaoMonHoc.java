package com.fushi.mobile_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.KhoaTab;

import java.util.ArrayList;

public class TaoMonHoc extends AppCompatActivity {


    Spinner spinnerBoMon;
    DatabaseHelper db ;
    Button btnTao;
    Button btnHuy;
    EditText txtMaMonHoc;
    EditText txtTenMonHoc;
    EditText txtSoTinChi;
    EditText txtSoTiet;
    EditText txtMoTa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_mon_hoc);

        this.db =  new DatabaseHelper(this);
        btnTao = findViewById(R.id.btnTao);
        btnHuy = findViewById(R.id.btnHuy);
        txtMaMonHoc = findViewById(R.id.maMonHoc);
        txtTenMonHoc = findViewById(R.id.tenMonHoc);
        txtSoTiet = findViewById(R.id.soTiet);
        txtTenMonHoc = findViewById(R.id.soTinChi);
        txtMoTa = findViewById(R.id.moTa);



        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_tao_bo_mon);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        this.spinnerBoMon = (Spinner) findViewById(R.id.maBoMon);

        ArrayList<BoMonTab> boMonTabs = this.db.layDanhSachBoMon();

        // (@resource) android.R.layout.simple_spinner_item:
        //   The resource ID for a layout file containing a TextView to use when instantiating views.
        //    (Layout for one ROW of Spinner)
        ArrayAdapter<BoMonTab> adapter = new ArrayAdapter<BoMonTab>(this,
                R.layout.activity_spiner_item,
                boMonTabs);

        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerBoMon.setAdapter(adapter);
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoMonHoc.this.taoMonHoc();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoMonHoc.this.xacNhan();
            }
        });


    }

    private void taoMonHoc(){
        try {
            String txtMaMonHoc = this.txtMaMonHoc.getText().toString();
            if(txtMaMonHoc.trim().equals("")){
                Toast.makeText(TaoMonHoc.this,"Mã môn học không được trống",Toast.LENGTH_SHORT).show();
                return;
            }

            String txtTenMonHoc = this.txtTenMonHoc.getText().toString();
            if(txtTenMonHoc.trim().equals("")){
                Toast.makeText(TaoMonHoc.this,"Tên môn học không được trống",Toast.LENGTH_SHORT).show();
                return;
            }

            String maBoMon = this.spinnerBoMon.getSelectedItem().toString();
            String moTa = this.txtMoTa.getText().toString();


            Integer soTinChi = Integer.parseInt(this.txtSoTinChi.getText().toString());
            if(soTinChi < 1){
                Toast.makeText(TaoMonHoc.this,"Số tín chỉ không hợp lệ",Toast.LENGTH_SHORT).show();
                return;
            }

            Integer soTiet = Integer.parseInt(this.txtSoTiet.getText().toString());
            if(soTiet < 1){
                Toast.makeText(TaoMonHoc.this,"Số tiết không hợp lệ",Toast.LENGTH_SHORT).show();
                return;
            }


            String result = this.db.taoMonHoc(txtMaMonHoc,txtTenMonHoc,maBoMon,soTinChi,soTiet,moTa);
            Toast.makeText(TaoMonHoc.this,result,Toast.LENGTH_SHORT).show();

            if(result == "Thành công"){
                this.troLaiDanhSach();
            }

        }
        catch (NumberFormatException e){
            Toast.makeText(TaoMonHoc.this,"Số tín chỉ hoặc số tiết không hợp lệ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("Error",e.getMessage());
        }

    }

    private void troLaiDanhSach(){
        Intent intent = new Intent(TaoMonHoc.this,BoMonChiTiet.class);
        startActivity(intent);
    }

    private void xacNhan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TaoMonHoc.this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn hủy");
        builder.setPositiveButton("Xác nhận",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaoMonHoc.this.troLaiDanhSach();
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
}