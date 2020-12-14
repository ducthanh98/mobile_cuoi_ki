package com.fushi.mobile_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.fushi.mobile_final.models.BoMonTab;
import com.fushi.mobile_final.models.KhoaTab;
import com.fushi.mobile_final.models.MonHocTab;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DaoTao";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public ArrayList<BoMonTab> layDanhSachBoMon() {
        ArrayList<BoMonTab> boMonTabs = new ArrayList<BoMonTab>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM BoMonTab";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            BoMonTab boMonTab = new BoMonTab();
            boMonTab.setMaBoMon(cursor.getString(cursor.getColumnIndex("maBoMon")));
            boMonTab.setTenBoMon(cursor.getString(cursor.getColumnIndex("tenBoMon")));
            boMonTab.setMaKhoa(cursor.getString(cursor.getColumnIndex("maKhoa")));
            boMonTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));


            boMonTabs.add(boMonTab);
            cursor.moveToNext();

        }
        cursor.close();

        return boMonTabs;
    }

    public BoMonTab layBoMon(String maBoMon) {
        BoMonTab boMonTab = new BoMonTab();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM BoMonTab WHERE maBoMon = ?";
        Cursor cursor = db.rawQuery(query, new String[]{maBoMon});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            boMonTab.setMaBoMon(cursor.getString(cursor.getColumnIndex("maBoMon")));
            boMonTab.setTenBoMon(cursor.getString(cursor.getColumnIndex("tenBoMon")));
            boMonTab.setMaKhoa(cursor.getString(cursor.getColumnIndex("maKhoa")));
            boMonTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));


            cursor.moveToNext();

        }
        cursor.close();

        return boMonTab;
    }


    public KhoaTab layKhoa(String maKhoa) {
        KhoaTab khoaTab = new KhoaTab();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM KhoaTab WHERE maKhoa = ?";
        Cursor cursor = db.rawQuery(query, new String[]{maKhoa});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            khoaTab.setMaKhoa(cursor.getString(cursor.getColumnIndex("maKhoa")));
            khoaTab.setTenKhoa(cursor.getString(cursor.getColumnIndex("tenKhoa")));
            khoaTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));

            cursor.moveToNext();

        }
        cursor.close();

        return khoaTab;
    }



    public String taoBoMon(String maBonMon, String tenBoMon, String maKhoa, String moTa) {
        SQLiteDatabase dbReader = this.getReadableDatabase();

        Cursor cusor = dbReader.rawQuery("SELECT maBoMon FROM BoMonTab WHERE maBoMon = ?", new String[]{maBonMon});
        if (cusor.getCount() > 0) {
            cusor.close();
            return "Mã bộ môn đã tồn tại";
        }


        SQLiteDatabase dbWriter = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maBoMon", maBonMon);
        contentValues.put("tenBoMon", tenBoMon);
        contentValues.put("maKhoa", maKhoa);
        contentValues.put("moTa", moTa);
        dbWriter.insert("BoMonTab", null, contentValues);
        return "Thành công";
    }

    public String capNhatBoMon(String maBonMon, String tenBoMon, String maKhoa, String moTa) {

        SQLiteDatabase dbWriter = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maBoMon", maBonMon);
        contentValues.put("tenBoMon", tenBoMon);
        contentValues.put("maKhoa", maKhoa);
        contentValues.put("moTa", moTa);

        dbWriter.update("BoMonTab", contentValues, "maBoMon = ?", new String[]{maBonMon});
        return "Thành công";
    }

    public String xoaBoMon(String maBonMon) {
        SQLiteDatabase dbWriter = this.getWritableDatabase();

        try {
            dbWriter.beginTransaction();

            dbWriter.delete("MonHocTab", "maBoMon = ?", new String[]{maBonMon});
            dbWriter.delete("BoMonTab", "maBoMon = ?", new String[]{maBonMon});

            dbWriter.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            dbWriter.endTransaction();
            return "Đã có lỗi xảy ra";

        }
        dbWriter.endTransaction();
        return "Thành công";
    }

    public ArrayList<MonHocTab> layDanhSachMonHoc() {
        ArrayList<MonHocTab> monHocTabs = new ArrayList<MonHocTab>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM MonHocTab ";
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            MonHocTab monHocTab = new MonHocTab();
            monHocTab.setMaMonHoc(cursor.getString(cursor.getColumnIndex("maMonHoc")));
            monHocTab.setTenMonHoc(cursor.getString(cursor.getColumnIndex("tenMonHoc")));
            monHocTab.setMaBoMon(cursor.getString(cursor.getColumnIndex("maBoMon")));
            monHocTab.setSoTinChi(cursor.getInt(cursor.getColumnIndex("soTinChi")));
            monHocTab.setSoTiet(cursor.getInt(cursor.getColumnIndex("soTiet")));
            monHocTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));


            monHocTabs.add(monHocTab);
            cursor.moveToNext();

        }
        cursor.close();

        return monHocTabs;
    }

    public MonHocTab layMonHoc(String maMonHoc) {
        MonHocTab monHocTab = new MonHocTab();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM MonHocTab WHERE maMonHoc = ?";
        Cursor cursor = db.rawQuery(query,new String[]{maMonHoc});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            monHocTab.setMaMonHoc(cursor.getString(cursor.getColumnIndex("maMonHoc")));
            monHocTab.setTenMonHoc(cursor.getString(cursor.getColumnIndex("tenMonHoc")));
            monHocTab.setMaBoMon(cursor.getString(cursor.getColumnIndex("maBoMon")));
            monHocTab.setSoTinChi(cursor.getInt(cursor.getColumnIndex("soTinChi")));
            monHocTab.setSoTiet(cursor.getInt(cursor.getColumnIndex("soTiet")));
            monHocTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));


            cursor.moveToNext();

        }
        cursor.close();

        return monHocTab;
    }

    public String taoMonHoc(String maMonHoc, String tenMonHoc, String maBoMon,Integer soTinChi,Integer soTiet, String moTa) {
        SQLiteDatabase dbReader = this.getReadableDatabase();

        Cursor cusor = dbReader.rawQuery("SELECT tenMonHoc FROM MonHocTab WHERE maMonHoc = ?", new String[]{maMonHoc});
        if (cusor.getCount() > 0) {
            cusor.close();
            return "Mã môn học đã tồn tại";
        }

        SQLiteDatabase dbWriter = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maMonHoc", maMonHoc);
        contentValues.put("tenMonHoc", tenMonHoc);
        contentValues.put("maBoMon", maBoMon);
        contentValues.put("soTinChi", soTinChi);
        contentValues.put("soTiet", soTiet);
        contentValues.put("moTa", moTa);

        dbWriter.insert("MonHocTab",null,contentValues);
        return "Thành công";
    }

    public String capNhatMonHoc(String maMonHoc, String tenMonHoc, String maBoMon,Integer soTinChi,Integer soTiet, String moTa) {

        SQLiteDatabase dbWriter = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maMonHoc", maMonHoc);
        contentValues.put("tenMonHoc", tenMonHoc);
        contentValues.put("maBoMon", maBoMon);
        contentValues.put("soTinChi", soTinChi);
        contentValues.put("soTiet", soTiet);
        contentValues.put("moTa", moTa);

        dbWriter.update("MonHocTab", contentValues, "maMonHoc = ?", new String[]{maMonHoc});
        return "Thành công";
    }

    public boolean xoaMonHoc(String maMonHoc) {
        SQLiteDatabase dbWriter = this.getWritableDatabase();
        dbWriter.delete("MonHocTab", "maMonHoc = ?", new String[]{maMonHoc});
        return true;
    }

    public ArrayList<KhoaTab> layDanhSachKhoa() {
        ArrayList<KhoaTab> khoaTabs = new ArrayList<KhoaTab>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM KhoaTab";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            KhoaTab khoaTab = new KhoaTab();
            khoaTab.setMaKhoa(cursor.getString(cursor.getColumnIndex("maKhoa")));
            khoaTab.setTenKhoa(cursor.getString(cursor.getColumnIndex("tenKhoa")));
            khoaTab.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));


            khoaTabs.add(khoaTab);
            cursor.moveToNext();

        }
        cursor.close();

        return khoaTabs;
    }


}
