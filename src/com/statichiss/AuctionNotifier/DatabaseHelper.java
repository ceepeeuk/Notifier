package com.statichiss.AuctionNotifier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {

    private SQLiteDatabase db;
    private static final String DB_NAME = "db";
    private static final int VERSION = 1;
    private static final String DB_PATH = "/data/data/";

    private static final String scheduleQueryString = "create table schedule (_id integer primary key, duration numeric, description text);";
    private static final String listingDefinitionQueryString = "create table listing_definition (_id integer primary key, name text, schedule integer,  " +
            "foreign key (schedule) references schedule (_id));";
    private static final String typeQueryString = "create table auction_type (_id integer primary key, description text);";
    private static final String listingsQueryString = "create table listings (_id integer primary key, listing_definition integer, auction_type integer, price numeric, datetime_found numeric, auction_end_date numeric, already_notified numeric " +
            "foreign key (listing_definition) references listing_definition(_id), foreign key (auction_type) references auction_type(_id));";


    public DatabaseHelper() {
        this.db = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
}
