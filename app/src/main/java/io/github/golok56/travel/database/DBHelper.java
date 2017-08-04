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

        final String createFlightTable = "CREATE TABLE " + DBSchema.TableFlightBook.TABLE_NAME + " (" +
            DBSchema.TableFlightBook._ID + " INTEGER PRIMARY KEY, " +
            DBSchema.TableFlightBook.USERID_COLUMN + " INTEGER, " +
            DBSchema.TableFlightBook.INFO_COLUMN + " TEXT);";

        final String createNotifTable = "CREATE TABLE " + DBSchema.TableNotifBook.TABLE_NAME + " (" +
            DBSchema.TableFlightBook._ID + " INTEGER PRIMARY KEY, " +
            DBSchema.TableFlightBook.INFO_COLUMN + " TEXT);";

         db.execSQL(createUserTable);
         db.execSQL(createFlightTable);
         db.execSQL(createNotifTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
