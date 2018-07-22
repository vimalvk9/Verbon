package com.example.sobbr.verbon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sobbR on 10/25/2016.
 */

public class DbOperations extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "product_info.db";
    private static final String CREATE_QUERY = "create table "+ProductContract.ProductEntry.TABLE_NAME+"" +
            "("+ProductContract.ProductEntry.ID+ " TEXT NOT NULL UNIQUE," +
            "primary key("+ProductContract.ProductEntry.ID+"));";
    DbOperations(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);
        Log.d("Database operations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database Opereations","Table Created...");

    }

    public void addInformation(SQLiteDatabase db,String id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductContract.ProductEntry.ID,id);
        db.insert(ProductContract.ProductEntry.TABLE_NAME,null,contentValues);
        Log.d("Database operations","One row inserted");
    }

    public Cursor getInformation(SQLiteDatabase db){
        String[] projections = {ProductContract.ProductEntry.ID};
        Cursor cursor = db.query(ProductContract.ProductEntry.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void deleteUser(DbOperations DOP,String user) {
        String selection = ProductContract.ProductEntry.ID +" = ?";
        String args[] = {user};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(ProductContract.ProductEntry.TABLE_NAME, selection, args);
    }
}
