package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydb.db";
    private static final String TABLE_NAME = "budget";
    private static final int DATABASE_VERSION = 2;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CATE = "category";
    private static final String AMOUNT = "amount";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " text, " +
                CATE + " text, " +
                AMOUNT + " text, " +
                STARTED + " text, " +
                FINISHED + " text) ";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);

    }

    public void addBudget(budget budget){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,budget.getName());
        contentValues.put(CATE,budget.getCategory());
        contentValues.put(AMOUNT,budget.getAmount());
        contentValues.put(STARTED,budget.getStarted());
        contentValues.put(FINISHED,budget.getFinished());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }

    public int countBudget(){
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //Get all guests into the list
    public List<budget> getAllBudgets(){

        List<budget> budgets =new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);

        //check the avalability of the data

        //this method returns if the cursor is empty
        if(cursor.moveToFirst()){
            do {
                //create a new GuEst object
                budget budge = new budget();

                budge.setId(cursor.getInt(0));
                budge.setName(cursor.getString(1));
                budge.setCategory(cursor.getString(2));
                budge.setAmount(cursor.getString(3));
                budge.setStarted(cursor.getLong(4));
                budge.setFinished(cursor.getLong(5));


                budgets.add(budge);
            }while  (cursor.moveToNext());
        }
        return budgets;
    }

    public void deleteBudget(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});

        //close the database connection
        db.close();
    }

    //get the single guest
    public budget getSingleBudget(int id){
        SQLiteDatabase db=getWritableDatabase();

        Cursor cursor= db.query(TABLE_NAME,new String[]{ID,NAME,CATE,AMOUNT,STARTED,FINISHED},ID+ "= ?",new String[]{String.valueOf(id)},null,null,null);

        budget budgett;

        if(cursor != null){
            cursor.moveToFirst();

            budgett =new budget(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getLong(4),
                    cursor.getLong(5)



            );
            return budgett;

        }
        return null;
    }

    public int updateBudget(budget budget){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,budget.getName());
        contentValues.put(CATE,budget.getCategory());
        contentValues.put(AMOUNT,budget.getAmount());
        contentValues.put(STARTED,budget.getStarted());
        contentValues.put(FINISHED,budget.getFinished());

        int status = sqLiteDatabase.update(TABLE_NAME,contentValues,ID +" =?",new String[]{String.valueOf(budget.getId())});
        sqLiteDatabase.close();
        return status;

    }

}
