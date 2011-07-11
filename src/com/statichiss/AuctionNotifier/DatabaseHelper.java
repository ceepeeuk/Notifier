package com.statichiss.AuctionNotifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getName();
    private static final String DB_NAME = "db";
    private static final int VERSION = 1;

    private static final String ID = "_id";

    private static final String SCHEDULE_TABLE = "schedule";
    private static final String SCHEDULE_DURATION = "duration";
    public static final String SCHEDULE_DESCRIPTION = "description";

    private static final String SCHEDULE_QUERY_STRING = "create table schedule (_id integer primary key, duration numeric, description text);";

    public static final String LISTING_DEFINITION_TABLE = "listing_definition";
    private static final String LISTING_DEFINITION_NAME = "name";
    private static final String LISTING_DEFINITION_SCHEDULE = "schedule";

    private static final String LISTING_DEFINITION_QUERY_STRING = "create table listing_definition (_id integer primary key, name text, schedule integer, foreign key (schedule) references schedule (_id));";

    private static final String TYPE_QUERY_STRING = "create table auction_type (_id integer primary key, description text);";
    private static final String LISTINGS_QUERY_STRING = "create table listings (_id integer primary key, listing_definition integer, auction_type integer, price numeric, datetime_found numeric, auction_end_date numeric, already_notified numeric, " +
            "foreign key (listing_definition) references listing_definition(_id), foreign key (auction_type) references auction_type(_id));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCHEDULE_QUERY_STRING);
        db.execSQL(LISTING_DEFINITION_QUERY_STRING);
        db.execSQL(TYPE_QUERY_STRING);
        db.execSQL(LISTINGS_QUERY_STRING);

        insertScheduleData(db);
        insertAuctionTypeData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public Cursor getDurations() {
        return getReadableDatabase().query(SCHEDULE_TABLE, new String[]{ID, SCHEDULE_DURATION, SCHEDULE_DESCRIPTION}, null, null, null, null, null);
    }

    public void addNewSearch(String searchTerm, long scheduleId) {
        ContentValues cv = new ContentValues();
        cv.put(LISTING_DEFINITION_NAME, searchTerm);
        cv.put(LISTING_DEFINITION_SCHEDULE, scheduleId);
        getWritableDatabase().insert(LISTING_DEFINITION_TABLE, LISTING_DEFINITION_NAME, cv);
    }

    private void insertScheduleData(SQLiteDatabase db) {
        long fifteenMinutes = 900000;

        ContentValues cv = new ContentValues();
        cv.put("duration", fifteenMinutes / 15);
        cv.put("description", "1 minute");
        db.insert("schedule", "duration", cv);

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

    private void insertAuctionTypeData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("description", "Auction");
        db.insert("auction_type", "description", cv);

        cv = new ContentValues();
        cv.put("description", "Buy It Now");
        db.insert("auction_type", "description", cv);
    }


}
