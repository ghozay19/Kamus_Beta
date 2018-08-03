package com.ghozay19.kamus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ghozay19.kamus.Model.EngBhsModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.DETAIL_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.WORD_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.TABLE_ENGLISH_BAHASA;

public class EngBhsHelper {

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public EngBhsHelper(Context context) {
        this.context = context;
    }


    public EngBhsHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void closeEng() {
        databaseHelper.close();
    }

    /*
    Dapetin data berdasarkan word
     */
    public ArrayList<EngBhsModel> getDataByWord(String word) {
        Cursor cursor = database.query(TABLE_ENGLISH_BAHASA, null, WORD_ENGLISH + " LIKE ?", new String[]{word + "%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<EngBhsModel> arrayList = new ArrayList<>();
        EngBhsModel engBhsModel;
        if (cursor.getCount() > 0) {
            do {
                engBhsModel = new EngBhsModel();
                engBhsModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                engBhsModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD_ENGLISH)));
                engBhsModel.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_ENGLISH)));

                arrayList.add(engBhsModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    /*
    Buat dapetin semua data
     */
    public ArrayList<EngBhsModel> getAllData() {
        Cursor cursor = database.query(TABLE_ENGLISH_BAHASA, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<EngBhsModel> arrayList = new ArrayList<>();
        EngBhsModel engBhsModel;
        if (cursor.getCount() > 0) {
            do {
                engBhsModel = new EngBhsModel();
                engBhsModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                engBhsModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD_ENGLISH)));
                engBhsModel.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_ENGLISH)));

                arrayList.add(engBhsModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public void beginTransactionEng() {
        database.beginTransaction();
    }

    public void setTransactionSuccessEng() {
        database.setTransactionSuccessful();
    }

    public void endTransactionEng() {
        database.endTransaction();
    }


    public void insertTransactionEng(EngBhsModel engBhsModel) {
        String sql = "INSERT INTO " + TABLE_ENGLISH_BAHASA + "(" + WORD_ENGLISH +
                "," + DETAIL_ENGLISH + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, engBhsModel.getWord());
        stmt.bindString(2, engBhsModel.getDetail());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(EngBhsModel engBhsModel) {
        ContentValues args = new ContentValues();
        args.put(WORD_ENGLISH, engBhsModel.getWord());
        args.put(DETAIL_ENGLISH, engBhsModel.getDetail());
        return database.update(TABLE_ENGLISH_BAHASA, args, _ID + "= '" + engBhsModel.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_ENGLISH_BAHASA, _ID + "= '" + id + "'", null);
    }


}
