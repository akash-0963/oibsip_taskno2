package com.example.todoapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Model.UserInfoModel;

import java.util.ArrayList;
import java.util.List;

public class UserDataHandler extends SQLiteOpenHelper {
    private SQLiteDatabase udb;

    private static  final String DATABASE_NAME = "USER_DATABASE";
    private static  final String TABLE_NAME = "USER_TABLE";
    private static  final String COL_1 = "ID";
    private static  final String COL_2 = "USERID";
    private static  final String COL_3 = "PASS";

    public UserDataHandler(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERID TEXT , PASS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertUser(UserInfoModel User){
        udb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , User.getUserId());
        values.put(COL_3 , User.getPass());
        udb.insert(TABLE_NAME , null , values);
        udb.close();
    }

    public int getUserPass(String UserId) {
        udb = this.getReadableDatabase();
        Cursor cursor = udb.query(TABLE_NAME,
                new String[]{COL_1, COL_2, COL_3},
                COL_2 + "=?", new String[]{String.valueOf(UserId)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        return Integer.parseInt(cursor.getString(2));
    }

    /*public String getUserData() {
        return userId;
    }*/

}
