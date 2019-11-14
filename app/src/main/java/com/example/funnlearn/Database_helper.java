package com.example.funnlearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static java.sql.Types.INTEGER;

public class Database_helper extends SQLiteOpenHelper {
    int C=1;

    public static final String DATABASE_NAME = "word_meaning.db";
    public static final String TABLE_NAME = "WM";
    public static final String ID = "ID";
    public static final String word_col = "word";
    public static final String meaning_col = "meaning";

    public Database_helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY , WORD TEXT, MEANING TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean insertData1(String WORD){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("word", WORD);
        content.put("ID", C);
        //content.put(meaning_col, MEANING);
        long result = database.insert(TABLE_NAME, null, content);
        if(result ==-1) {
            return false;
        }
        else{
            return true;
            }
    }

    public boolean insertData2(String MEANING){
        SQLiteDatabase database = this.getWritableDatabase();
        //ContentValues content = new ContentValues();
        //content.put(meaning_col, MEANING);
        String cmd = "update " + TABLE_NAME + " SET MEANING = " + MEANING +" where ID = " + C;
        database.execSQL(cmd);
        C++;
        /*long result = database.insert(TABLE_NAME, null, content);
        if(result ==-1) {
            return false;
        }
        else{
            return true;
        }*/
        return true;
    }

}
