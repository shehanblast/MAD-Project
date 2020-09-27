package com.example.dbdemo;
//Dulan
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "Task";
    private static final String TABLE_NAME = "Task";

    //Coulmn Name
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+ " TEXT,"
                +DESCRIPTION+ " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                ");";
        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF  EXISTS "+ TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }
    //Add task
    public void addTask(Task task){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,task.getTitle());
        contentValues.put(DESCRIPTION,task.getDescription());
        contentValues.put(STARTED,task.getStarted());
        contentValues.put(FINISHED,task.getFinished());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        sqLiteDatabase.close();
    }

    //Count task
    public int countTask(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all task
    public List<Task> getAllTask(){

        List<Task> tasks = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Task task = new Task();

                task.setId(cursor.getInt(0));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setStarted(cursor.getLong(3));
                task.setFinished(cursor.getLong(4));

                tasks.add(task);
            }while (cursor.moveToNext());
        }
        return  tasks;
    }

    //delete task
    public void deleteTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //get single task
    public Task getSingaleTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED,FINISHED},ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        Task task;
        if(cursor != null){
            cursor.moveToFirst();
            task = new Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return task;
        }
        return null;
    }

    //Update task
    public int UpdateTask(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,task.getTitle());
        contentValues.put(DESCRIPTION,task.getDescription());
        contentValues.put(STARTED,task.getStarted());
        contentValues.put(FINISHED,task.getFinished());

        int status =db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(task.getId())});

        db.close();
        return status;
    }
}
