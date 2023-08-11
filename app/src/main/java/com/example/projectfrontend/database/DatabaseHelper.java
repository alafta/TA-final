package com.example.projectfrontend.database;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "dbtriz.db";
    private static final int DB_VERSION = 1;

    @SuppressLint("SdCardPath")
    private static final String DB_PATH = "/data/data/com.example.projectfrontend/databases/";

    private static final String TABLE_PARAMETER = "Parameter";
    private static final String TABLE_PS = "Prinsipal_Solusi";
    private static final String TABLE_PENJELASANPS = "Penjelasan_ps";
    private static final String TABLE_ILUSTRASIPS = "Ilustrasi_ps";
    private static final String TABLE_CASESTUDY = "study_case";
    private static final String TABLE_KONTRADIKSI = "Kontradiksi";

    Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        myContext =context;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        boolean dbexist = checkDataBase();
        if(dbexist){
            Log.v("dbtriz.db","db exists");
            myContext.deleteDatabase(DB_NAME);
            //openDataBase();
            //do nothing - database already exist
        }else{
            Log.v("dbtriz.db","dbnot exists");
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
        }
    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            //database does't exist yet.
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null;
    }
    public Cursor readDataTableP(){
        String query = "SELECT * FROM " +TABLE_PARAMETER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readDataTablePs(){
        String query = "SELECT * FROM " +TABLE_PS ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readDataTablePsById(int id){
        String query = "SELECT * FROM " +TABLE_PS + " WHERE id_ps = " + id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readPenjelasanPsById(int psId) {
        String query = "SELECT * FROM " + TABLE_PENJELASANPS + " WHERE id_ps = " + psId;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readIlustrasiPsById(int psId) {
        String query = "SELECT * FROM " + TABLE_ILUSTRASIPS + " WHERE id_ps = " + psId;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readDataTableCs(){
        String query = "SELECT * FROM " +TABLE_CASESTUDY;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readKontradiksi(int improvingId, int worseningId) {
        String query = "SELECT * FROM " + TABLE_KONTRADIKSI + " WHERE id_improveP = " + improvingId +
                " AND id_worseningP = " + worseningId;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
