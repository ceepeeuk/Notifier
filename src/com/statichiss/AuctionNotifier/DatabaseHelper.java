package com.statichiss.AuctionNotifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DB_NAME = "db";
    private static final int VERSION = 1;

    private static final String scheduleQueryString = "create table schedule (_id integer primary key, duration numeric, description text);";
    private static final String listingDefinitionQueryString = "create table listing_definition (_id integer primary key, name text, schedule integer,  " +
            "foreign key (schedule) references schedule (_id));";
    private static final String typeQueryString = "create table auction_type (_id integer primary key, description text);";
    private static final String listingsQueryString = "create table listings (_id integer primary key, listing_definition integer, auction_type integer, price numeric, datetime_found numeric, auction_end_date numeric, already_notified numeric " +
            "foreign key (listing_definition) references listing_definition(_id), foreign key (auction_type) references auction_type(_id));";

    private long fifteenMinutes = 900000;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(scheduleQueryString);
        db.execSQL(listingDefinitionQueryString);
        db.execSQL(typeQueryString);
        db.execSQL(listingsQueryString);

        insertScheduleData();
        insertAuctionTypeData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    private void insertScheduleData(){
        ContentValues cv = new ContentValues();
        cv.put("duration", fifteenMinutes);
        cv.put("description", "15 minutes");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 2);
        cv.put("description", "30 minutes");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 4);
        cv.put("description", "1 hour");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 4 * 2);
        cv.put("description", "2 hours");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 4 * 3);
        cv.put("description", "3 hours");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 4 * 6);
        cv.put("description", "6 hours");
        db.insert("schedule", "duration", cv);

        cv = new ContentValues();
        cv.put("duration", fifteenMinutes * 4 * 12);
        cv.put("description", "12 hours");
        db.insert("schedule", "duration", cv);
    }

    private void insertAuctionTypeData(){
        ContentValues cv = new ContentValues();
        cv.put("description", "Auction");
        db.insert("auction_type", "description", cv);

        cv = new ContentValues();
        cv.put("description", "Buy It Now");
        db.insert("auction_type", "description", cv);
    }
}
