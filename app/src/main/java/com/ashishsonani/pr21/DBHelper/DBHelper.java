package com.ashishsonani.pr21.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "PasswordsVault.db";
    public static final String TABLE_NAME = "PasswordVault";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "uname";
    public static final String COL_PASSWORD = "pass";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists " + TABLE_NAME + "(" + COL_ID + " integer primary key," + COL_USERNAME + " text," + COL_PASSWORD + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                ("DROP TABLE IF EXISTS " + TABLE_NAME)
        );
    }

    public boolean insertPassowrdVault(String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, uname);
        contentValues.put(COL_PASSWORD, pass);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updatePasswordVault(Integer id, String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, uname);
        contentValues.put(COL_PASSWORD, pass);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_ID + "=" + id, null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public Integer deletePassworsVault(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{Integer.toString(id)});
    }

    public ArrayList<ArrayList<String>> getALLPassword() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> uname = new ArrayList<String>();
        ArrayList<String> pass = new ArrayList<String>();
        ArrayList<String> ids = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            uname.add(res.getString(res.getColumnIndex(COL_USERNAME)));
            pass.add(res.getString(res.getColumnIndex(COL_PASSWORD)));
            ids.add(res.getString(res.getColumnIndex(COL_ID)));
            res.moveToNext();
        }
        arrayList.add(uname);
        arrayList.add(pass);
        arrayList.add(ids);
        return arrayList;
    }

}
