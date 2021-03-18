package com.example.mini_project_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_name = "Agriculture.db";
    public static final String Table_name = "agri";
    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public static final String col_3 = "CROP_NAME";
    public static final String col_4 = "CROP_WEIGHT";
    public static final String col_5 = "CROP_PRICE";


    public DatabaseHelper( Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CROP_NAME TEXT, CROP_WEIGHT INT, CROP_PRICE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public  boolean insertData(String name, String cropname, String cropweight, String cropprice )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,cropname);
        contentValues.put(col_4,cropweight);
        contentValues.put(col_5,cropprice);
        long result = db.insert(Table_name,null,  contentValues) ;
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name,null);
        return res;
    }

    public boolean updateData(String id,String name, String cropname, String cropweight, String cropprice )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,name);
        contentValues.put(col_3,cropname);
        contentValues.put(col_4,cropweight);
        contentValues.put(col_5,cropprice);
        db.update(Table_name, contentValues, "ID = ?",new String[] { id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name,"ID = ?", new String[] { id });
    }
}
