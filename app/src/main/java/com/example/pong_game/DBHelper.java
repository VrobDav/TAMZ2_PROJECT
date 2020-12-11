package com.example.pong_game;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "DBTAMZ2_Project.db";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_SCORE = "score";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items " + "(id INTEGER PRIMARY KEY, name TEXT, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean insertItem(String name, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("score", score);
        long insertedId = db.insert("items", null, contentValues);
        if (insertedId == -1) return false;
        return true;
    }


    public ArrayList<String> getItemList()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items order by items.score desc limit 20;", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name = res.getString(res.getColumnIndex(ITEM_COLUMN_NAME));
            int score = res.getInt(res.getColumnIndex(ITEM_COLUMN_SCORE));
            arrayList.add( name + ": " + score );
            res.moveToNext();
        }

        return arrayList;
    }

    public int removeAll()
    {
        int nRecordDeleted = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db_del = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from items", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            int id = res.getInt(0);
            db_del.delete("items", "id="+id, null);
            nRecordDeleted++;
            res.moveToNext();
        }
        return nRecordDeleted;
    }
}
