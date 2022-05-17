package com.umg.trains;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "train_battles";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "battle_history";
    private static final String ID_COL = "id";
    private static final String HP_DIFF_COL = "hp_difference";
    private static final String WHO_WIN = "winner";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HP_DIFF_COL + " TEXT,"
                + WHO_WIN + " TEXT)";
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewBattle(String hpDifference, String winner) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HP_DIFF_COL, hpDifference);
        values.put(WHO_WIN, winner);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
