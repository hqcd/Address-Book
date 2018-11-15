package com.example.quinten.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressBookDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "addressbook.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "STREET";
    public static final String COL_6 = "CITY";
    public static final String COL_7 = "STATE";
    public static final String COL_8 = "ZIP";

    public AddressBookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, EMAIL TEXT, STREET TEXT, CITY TEXT, STATE TEXT, ZIP TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String phone, String email, String street, String city, String state, String zip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, street);
        contentValues.put(COL_6, city);
        contentValues.put(COL_7, state);
        contentValues.put(COL_8, zip);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}
