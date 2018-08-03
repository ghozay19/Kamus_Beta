package com.ghozay19.kamus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ghozay19.kamus.Model.BhsEngModel;
import com.ghozay19.kamus.Model.EngBhsModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ghozay19.kamus.Database.DatabaseContract.BahasaEnglishColumns.DETAIL_BAHASA;
import static com.ghozay19.kamus.Database.DatabaseContract.BahasaEnglishColumns.WORD_BAHASA;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.DETAIL_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.EnglishBahasaColumns.WORD_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.TABLE_BAHASA_ENGLISH;
import static com.ghozay19.kamus.Database.DatabaseContract.TABLE_ENGLISH_BAHASA;

public class BhsEngHelper {

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public BhsEngHelper(Context context) {
        this.context = context;
    }


    public BhsEngHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void closeBhs() {
        databaseHelper.close();
    }

    /*
    Dapetin data berdasarkan word
     */
    public ArrayList<BhsEngModel> getDataByWord(String word) {
        Cursor cursor = database.query(TABLE_BAHASA_ENGLISH, null, WORD_BAHASA + " LIKE ?", new String[]{word + "%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<BhsEngModel> arrayList = new ArrayList<>();
        BhsEngModel bhsEngModel;
        if (cursor.getCount() > 0) {
            do {
                bhsEngModel = new BhsEngModel();
                bhsEngModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bhsEngModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD_BAHASA)));
                bhsEngModel.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_BAHASA)));

                arrayList.add(bhsEngModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    /*
    Buat dapetin semua data
     */
    public ArrayList<BhsEngModel> getAllData() {
        Cursor cursor = database.query(TABLE_ENGLISH_BAHASA, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<BhsEngModel> arrayList = new ArrayList<>();
        BhsEngModel bhsEngModel;
        if (cursor.getCount() > 0) {
            do {
                bhsEngModel = new BhsEngModel();
                bhsEngModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bhsEngModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD_BAHASA)));
                bhsEngModel.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_BAHASA)));

                arrayList.add(bhsEngModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public void beginTransactionBhs() {
        database.beginTransaction();
    }

    public void setTransactionSuccessBhs() {
        database.setTransactionSuccessful();
    }

    public void endTransactionBhs() {
        database.endTransaction();
    }

    public void insertTransactionBhs(BhsEngModel bhsEngModel) {
        String sql = "INSERT INTO " + TABLE_BAHASA_ENGLISH + "(" + WORD_BAHASA +
                "," + DETAIL_BAHASA + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, bhsEngModel.getWord());
        stmt.bindString(2, bhsEngModel.getDetail());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(BhsEngModel bhsEngModel) {
        ContentValues args = new ContentValues();
        args.put(WORD_BAHASA, bhsEngModel.getWord());
        args.put(DETAIL_BAHASA, bhsEngModel.getDetail());
        return database.update(TABLE_BAHASA_ENGLISH, args, _ID + "= '" + bhsEngModel.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_ENGLISH_BAHASA, _ID + "= '" + id + "'", null);
    }


}
