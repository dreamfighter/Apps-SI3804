package com.example.zeger.apps_si3004.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zeger.apps_si3004.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeger on 01/04/17.
 */

public class SqliteMyHelper extends SQLiteOpenHelper{
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "MAHASISWA" + " (" +
                    "ID" + " INTEGER PRIMARY KEY," +
                    "NIM" + " TEXT," +
                    "NAMA" + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS MAHASISWA" ;

    public SqliteMyHelper(Context context) {
        super(context, "si3804.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long insert(String nim,String nama){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("NIM", nim);
        values.put("NAMA", nama);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("MAHASISWA", null, values);

        db.close();

        return newRowId;
    }

    public List<Mahasiswa> selectMahasiswa(){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "ID",
                "NIM",
                "NAMA"
        };

// Filter results WHERE "TITLE" = 'My Title'
        String selection = "NIM" + " = ?";

        String[] selectionArgs = { "113060038" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                "ID" + " DESC";

        Cursor cursor = db.query(
                "MAHASISWA",                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List<Mahasiswa> listMhs = new ArrayList<Mahasiswa>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));

            String itemNim = cursor.getString(
                    cursor.getColumnIndexOrThrow("NIM"));

            String itemNama = cursor.getString(
                    cursor.getColumnIndexOrThrow("NAMA"));

            Mahasiswa mhs = new Mahasiswa();
            mhs.setId(itemId);
            mhs.setNim(itemNim);
            mhs.setNama(itemNama);

            listMhs.add(mhs);
        }
        cursor.close();
        return  listMhs;
    }
}
