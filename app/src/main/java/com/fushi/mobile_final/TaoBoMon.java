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
import android.widget.TextView;
import android.widget.Toast;

import com.fushi.mobile_final.models.KhoaTab;

import java.util.ArrayList;

public class TaoBoMon extends AppCompatActivity {
    Spinner spinnerKhoa;
    DatabaseHelper db ;
    Button btnTao;
    Button btnHuy;
    EditText txtMaBoMon;
    EditText txtTenBoMon;
    EditText txtMoTa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_bo_mon);

        this.db =  new DatabaseHelper(this);
        btnTao = findViewById(R.id.btnTao);
        btnHuy = findViewById(R.id.btnHuy);
        txtMaBoMon = findViewById(R.id.maBoMon);
        txtTenBoMon = findViewById(R.id.tenBoMon);
        txtMoTa = findViewById(R.id.moTa);



        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_tao_bo_mon);

        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        this.spinnerKhoa = (Spinner) findViewById(R.id.maKhoa);

        ArrayList<KhoaTab> khoaTabs = this.db.layDanhSachKhoa();

        // (@resource) android.R.layout.simple_spinner_item:
        //   The resource ID for a layout file containing a TextView to use when instantiating views.
        //    (Layout for one ROW of Spinner)
        ArrayAdapter<KhoaTab> adapter = new ArrayAdapter<KhoaTab>(this,
                R.layout.activity_spiner_item,
                khoaTabs);

        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerKhoa.setAdapter(adapter);
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoBoMon.this.taoBoMon();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoBoMon.this.xacNhan();
            }
        });


    }

    private void taoBoMon(){
       try {
           String txtMaBoMon = this.txtMaBoMon.getText().toString();
           if(txtMaBoMon.trim().equals("")){
               Toast.makeText(TaoBoMon.this,"Mã bộ môn không được trống",Toast.LENGTH_SHORT).show();
               return;
           }

           String txtTenBoMon = this.txtTenBoMon.getText().toString();
           if(txtTenBoMon.trim().equals("")){
               Toast.makeText(TaoBoMon.this,"Tên bộ môn không được trống",Toast.LENGTH_SHORT).show();
               return;
           }

           String maKhoa = this.spinnerKhoa.getSelectedItem().toString();
           String moTa = this.txtMoTa.getText().toString();

           String result = this.db.taoBoMon(txtMaBoMon,txtTenBoMon,maKhoa,moTa);
           Toast.makeText(TaoBoMon.this,result,Toast.LENGTH_SHORT).show();

           if(result == "Thành công"){
               this.troLaiDanhSach();
           }

       } catch (Exception e){
           Log.d("Error",e.getMessage());
       }

    }

    private void troLaiDanhSach(){
        Intent intent = new Intent(TaoBoMon.this,MainActivity.class);
        startActivity(intent);
    }

    private void xacNhan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TaoBoMon.this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn hủy");
        builder.setPositiveButton("Xác nhận",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaoBoMon.this.troLaiDanhSach();
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