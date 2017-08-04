package io.github.golok56.travel.database;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "travel.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         final String createUserTable = "CREATE TABLE " + DBSchema.TableUser.TABLE_NAME + " (" +
            DBSchema.TableUser._ID + " INTEGER PRIMARY KEY, " +
            DBSchema.TableUser.EMAIL_COLUMN + " TEXT UNIQUE, " +
            DBSchema.TableUser.USERNAME_COLUMN + " TEXT UNIQUE, " +
            DBSchema.TableUser.NAME_COLUMN + " TEXT, " +
            DBSchema.TableUser.PASSWORD_COLUMN + " TEXT);";
         db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
