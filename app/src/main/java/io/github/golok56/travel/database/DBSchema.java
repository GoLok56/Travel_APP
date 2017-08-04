package io.github.golok56.travel.database;

import android.provider.BaseColumns;

public class DBSchema {

    private DBSchema(){}

    public static class TableUser implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String EMAIL_COLUMN = "email";
        public static final String USERNAME_COLUMN = "username";
        public static final String NAME_COLUMN = "name";
        public static final String PASSWORD_COLUMN = "password";
    }

    public static class TableFlightBook implements BaseColumns {
        public static final String TABLE_NAME = "flight_book";
        public static final String USERID_COLUMN = "user_id";
        public static final String INFO_COLUMN = "info";
    }

}
