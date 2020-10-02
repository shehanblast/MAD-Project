package com.example.dbdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeshaniDbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 4;
    private static final String DB_NAME = "vendor";
    private static final String TABLE_NAME = "vendor";

    //column names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CATEGORY = "category";
    private static final String NOTE = "note";
    private static final String ESTIMATEDAMOUNT = "estimated_amount";
    private static final String NUMBER = "number";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String P_AMOUNT = "p_amount";
    private static final String P_DATE = "p_date";
    private static final String FINISHED="finished";


    public DeshaniDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = " CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + CATEGORY + " TEXT, "
                + NOTE + " TEXT, "
                + ESTIMATEDAMOUNT + " TEXT, "
                + NUMBER + " TEXT, "
                + EMAIL + " TEXT, "
                + ADDRESS + " TEXT, "
                + P_AMOUNT + " TEXT, "
                + P_DATE + " TEXT, "
                + FINISHED + " TEXT " +
                ");";


        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_QUERY = " DROP TABLE IF EXISTS " + TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //create again
        onCreate(db);

    }

    public void addVendor(Vendor vendor){


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, vendor.getName());
        contentValues.put(CATEGORY, vendor.getCategory());
        contentValues.put(NOTE, vendor.getNote());
        contentValues.put(ESTIMATEDAMOUNT, vendor.getEstimated_amount());
        contentValues.put(NUMBER, vendor.getNumber());
        contentValues.put(EMAIL, vendor.getEmail());
        contentValues.put(ADDRESS, vendor.getAddress());
        contentValues.put(P_AMOUNT, vendor.getP_amount());
        contentValues.put(P_DATE, vendor.getP_date());
        contentValues.put(FINISHED,vendor.getFinished());


        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();

    }

    //count the vendors table records
    public int countVendor(){
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+ TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();

    }

    //Get all vendors into the list
    public List<Vendor> getAllVendors(){

        List<Vendor> vendors =new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);

        //check the avalability of the data

        //this method returns if the cursor is empty
        if(cursor.moveToFirst()){
            do {
                //create a new Vendor object
                Vendor vendor = new Vendor();

                vendor.setId(cursor.getInt(0));
                vendor.setName(cursor.getString(1));
                vendor.setCategory(cursor.getString(2));
                vendor.setNote(cursor.getString(3));
                vendor.setEstimated_amount(cursor.getString(4));
                vendor.setNumber(cursor.getString(5));
                vendor.setEmail(cursor.getString(6));
                vendor.setAddress(cursor.getString(7));
                vendor.setP_amount(cursor.getString(8));
                vendor.setP_date(cursor.getString(9));
                vendor.setFinished(cursor.getLong(10));

                //vendors=[obj,objs,]
                vendors.add(vendor);
            }while  (cursor.moveToNext());
        }
        return vendors;
    }


    //delete the guest from the database
    public void deleteVendor(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});

        //close the database connection
        db.close();
    }

    //get the single guest
    public Vendor getSingleVendor(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.query(TABLE_NAME,new String[]{ID,NAME,CATEGORY,NOTE,ESTIMATEDAMOUNT,NUMBER,EMAIL,ADDRESS,P_AMOUNT,P_DATE,FINISHED},ID+ "= ?",new String[]{String.valueOf(id)},null,null,null,null);

        Vendor vendor;

        if(cursor != null){
            cursor.moveToFirst();
            vendor =new Vendor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getLong(10)
            );

            return vendor;

        }
        return null;
    }
    //update the vendor
    public int updateSingleVendor(Vendor vendor){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues =new ContentValues();//to struchure the data

        contentValues.put(NAME,vendor.getName());
        contentValues.put(CATEGORY,vendor.getCategory());
        contentValues.put(NOTE,vendor.getNote());
        contentValues.put(ESTIMATEDAMOUNT,vendor.getEstimated_amount());
        contentValues.put(NUMBER,vendor.getNumber());
        contentValues.put(EMAIL,vendor.getEmail());
        contentValues.put(ADDRESS,vendor.getAddress());
        contentValues.put(P_AMOUNT,vendor.getP_amount());
        contentValues.put(P_DATE,vendor.getP_date());
        contentValues.put(FINISHED, vendor.getFinished());


        int status = db.update(TABLE_NAME,contentValues,ID +" =?",new String[]{String.valueOf(vendor.getId())});
        db.close();
        return status;


    }

}
