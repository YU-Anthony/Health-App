package com.example.healthapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create activity table to store tracked sessions/activities by the user
        db.execSQL("CREATE TABLE contactpersons (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "lastname VARCHAR(128) NOT NULL," +
                "firstname VARCHAR(128) NOT NULL," +
                "phonenumber VARCHAR(128) NOT NULL," +
                "email VARCHAR(128) NOT NULL);");

        db.execSQL("CREATE TABLE sittingpostures (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "issittingright INTEGER NOT NULL," +
                "healthscore INTEGER NOT NULL," +
                "slowrising INTEGER NOT NULL," +
                "issitting INTEGER NOT NULL," +
                "islrbalanced INTEGER NOT NULL," +
                "isapbalanced INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactpersons");
        db.execSQL("DROP TABLE IF EXISTS sittingpostures");
        onCreate(db);
    }

}

