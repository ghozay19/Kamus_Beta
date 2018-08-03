package com.ghozay19.kamus.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.ghozay19.kamus.Database.DatabaseContract.BahasaEnglishColumns.DETAIL_BAHASA;
import static com.ghozay19.kamus.Database.DatabaseContract.BahasaEnglishColumns.WORD_BAHASA;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.DETAIL_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.WORD_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.TABLE_BAHASA_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.TABLE_ENGLISH_BAHASA;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH_BAHASA = "create table " + TABLE_ENGLISH_BAHASA +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD_ENGLISH + " text not null, " +
            DETAIL_ENGLISH + " text not null);";

    public static String CREATE_TABLE_BAHASA_ENGLISH = "create table " + TABLE_BAHASA_ENGLISH +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD_BAHASA + " text not null, " +
            DETAIL_BAHASA + " text not null);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH_BAHASA);
        db.execSQL(CREATE_TABLE_BAHASA_ENGLISH);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH_BAHASA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAHASA_ENGLISH);
        onCreate(db);

    }

}
