package com.soshians_co.aab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yousef on 5/10/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BOB.db";

    // tables name
    private static final String TABLE_BOB = "bob";
    private static final String TABLE_SETTING = "setting";

    // BOB Table Columns names (user account)
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_SWITCH = "switch";






    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_BOB_TABLE = "CREATE TABLE " + TABLE_BOB + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_LOGIN + " TEXT," + KEY_USERNAME + " TEXT" + ")";
        db.execSQL(CREATE_BOB_TABLE);

        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_SWITCH + " TEXT" + ")";
        db.execSQL(CREATE_SETTING_TABLE);




    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(db);
    }

    //add user account
    void addBOB(BABDbInterface BABDbInterface) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, BABDbInterface.getLogin());
        values.put(KEY_USERNAME, BABDbInterface.getUsername());

        // Inserting Row
        db.insert(TABLE_BOB, null, values);
        db.close(); // Closing database connection
    }

    void addSetting(BABDbInterface BABDbInterface) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SWITCH, BABDbInterface.getSwitch());
        // Inserting Row
        db.insert(TABLE_SETTING, null, values);
        db.close(); // Closing database connection
    }




    // Getting All BOB user
    public List<BABDbInterface> getAllBOB() {
        List<BABDbInterface> Condenser = new ArrayList<BABDbInterface>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOB ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BABDbInterface BABDbInterface = new BABDbInterface();
                BABDbInterface.setLogin(cursor.getString(1));
                BABDbInterface.setUsername(cursor.getString(2));

                // Adding contact to list
                Condenser.add(BABDbInterface);
            } while (cursor.moveToNext());
        }

        // return contact list
        return Condenser;
    }



    public List<BABDbInterface> getAllSetting() {
        List<BABDbInterface> Condenser = new ArrayList<BABDbInterface>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SETTING ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BABDbInterface BABDbInterface = new BABDbInterface();
                BABDbInterface.setSwitch(cursor.getString(1));
                                // Adding contact to list
                Condenser.add(BABDbInterface);
            } while (cursor.moveToNext());
        }

        // return contact list
        return Condenser;
    }



    // Updating single contact
    public int updateBOB(BABDbInterface BABDbInterface) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, BABDbInterface.getLogin());
        values.put(KEY_USERNAME, BABDbInterface.getUsername());


        // updating row
        return db.update(TABLE_BOB, values, KEY_USERNAME + " = ?",
                new String[] { BABDbInterface.getUsername()});
    }



    public int updateSetting(BABDbInterface BABDbInterface) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SWITCH, BABDbInterface.getSwitch());
        // updating row
        return db.update(TABLE_BOB, values, KEY_SWITCH + " = ?",
                new String[] { BABDbInterface.getSwitch()});
    }

    // Deleting single contact
    public void deleteBOB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOB, null, null);
        db.close();
    }

    public void deleteSetting() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETTING, null, null);
        db.close();
    }


    // Getting contacts Count
    public int getBOBCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }



    public String BOBCount(){
        String count = "SELECT COUNT(*) FROM " + TABLE_BOB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(count, null);
        cur.moveToFirst();
        if (cur.getInt(0) == 0) {
            return "false";

        }

        else {
            return "true";
        }
    }

    public String SettingCount(){
        String count = "SELECT COUNT(*) FROM " + TABLE_SETTING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(count, null);
        cur.moveToFirst();
        if (cur.getInt(0) == 0) {
            return "false";

        }

        else {
            return "true";
        }
    }

}
