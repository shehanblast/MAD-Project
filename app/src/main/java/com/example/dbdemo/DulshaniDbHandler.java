package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DulshaniDbHandler extends SQLiteOpenHelper {

    private static final int VERSION=2;
    private static final String DB_NAME="guestdb";
    private static final String TABLE_NAME="guestdb";

    //coloms names
    private static final String ID="id";
    private static final String NAME="name";
    private static final String EMAIL="email";
    private static final String STARTED="started";
    private static final String FINISHED="finished";

    public DulshaniDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY =" CREATE TABLE "+TABLE_NAME+" "+
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + STARTED + " TEXT, "
                + FINISHED + " TEXT " +
                ");";

        //run the create query
        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_QUERY ="DROP TABLE IF EXISTS "+ TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

    }

    public void addGuEst(GuEst guEst){
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();

        ContentValues contentValues =new ContentValues();//to struchure the data

        contentValues.put(NAME,guEst.getName());
        contentValues.put(EMAIL,guEst.getEmail());
        contentValues.put(STARTED,guEst.getStarted());
        contentValues.put(FINISHED,guEst.getFinished());

        //save to the table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close the database
        sqLiteDatabase.close();

    }

    //count the guest table records
    public int countGuEst(){
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+ TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();

    }

    //Get all guests into the list
    public List<GuEst> getAllGuEsts(){

        List<GuEst> guEsts =new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);

        //check the avalability of the data

        //this method returns if the cursor is empty
        if(cursor.moveToFirst()){
            do {
                //create a new GuEst object
                GuEst guEst = new GuEst();

                guEst.setId(cursor.getInt(0));
                guEst.setName(cursor.getString(1));
                guEst.setEmail(cursor.getString(2));
                guEst.setStarted(cursor.getLong(3));
                guEst.setFinished(cursor.getLong(4));

                //guEsts=[obj,objs,]
                guEsts.add(guEst);
            }while  (cursor.moveToNext());
        }
        return guEsts;
    }
    //delete the guest from the database
    public void deleteGuEst(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});

        //close the database connection
        db.close();
    }

    //get the single guest
    public GuEst getSingleGuEst(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.query(TABLE_NAME,new String[]{ID,NAME,EMAIL,STARTED,FINISHED},ID+ "= ?",new String[]{String.valueOf(id)},null,null,null);

        GuEst guEst;

        if(cursor != null){
            cursor.moveToFirst();
            guEst =new GuEst(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)

            );
            return guEst;

        }
        return null;
    }

    //update the guest
    public int updateSingleGuEst(GuEst guEst){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues =new ContentValues();//to struchure the data

        contentValues.put(NAME,guEst.getName());
        contentValues.put(EMAIL,guEst.getEmail());
        contentValues.put(STARTED,guEst.getStarted());
        contentValues.put(FINISHED,guEst.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",new String[]{String.valueOf(guEst.getId())});
        db.close();
        return status;


    }

}
