package com.m_windle.xyz_conference.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.m_windle.xyz_conference.database.ConferenceContract.PresenterTable;

import java.sql.SQLClientInfoException;

/**
 * Created by mr_moshi on 11/27/2015.
 */
public class ConferenceDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Conference.db";
    public static ConferenceDbHelper sInstance;

    private static String[] allColumns = {
            PresenterTable._ID,
            PresenterTable.COLUMN_NAME_FNAME,
            PresenterTable.COLUMN_NAME_LNAME,
            PresenterTable.COLUMN_NAME_EMAIL,
            PresenterTable.COLUMN_NAME_BIO,
            PresenterTable.COLUMN_NAME_AFFILIATION
    };


    public ConferenceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ConferenceDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ConferenceDbHelper(context.getApplicationContext());
        }

        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PresenterTable.SQL_CREATE_ENTRIES);
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PresenterTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    // DATA INSERTION
    private void initialize(SQLiteDatabase db) {
        insertPresenter("Gregory", "House", "Princeton-Plainsboro", "ghouse@pp.com", "Cantankerous enigma, the proverbial bitter pill who also happens to be a highly intuitive medical genius.", db);
        insertPresenter("Patch", "Adams", "Gesundheit! Institute", "padams@laughter.net", "Organizes a group of volunteers from around the world to travel to various countries where they dress as clowns in an effort to bring humor to orphans, patients, and other people.", db);
        insertPresenter("Marie", "Curie", "University of Paris", "m.curie@rads.fr", "Polish and naturalized-French physicist and chemist who conducted pioneering research on radioactivity.", db);
    }

    public long insertPresenter(String fname, String lname, String affiliation, String email, String bio, SQLiteDatabase db){
        long id;

        ContentValues cv = new ContentValues();
        cv.put(PresenterTable.COLUMN_NAME_FNAME, fname);
        cv.put(PresenterTable.COLUMN_NAME_LNAME, lname);
        cv.put(PresenterTable.COLUMN_NAME_AFFILIATION, affiliation);
        cv.put(PresenterTable.COLUMN_NAME_EMAIL, email);
        cv.put(PresenterTable.COLUMN_NAME_BIO, bio);

        id = db.insert(PresenterTable.TABLE_NAME, null, cv);
        System.out.println("Item " + id + " added successfully");
        return id;
    }

    // DATA RETRIEVAL
    // Presenter Table
    public Cursor getAllPresenters(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(PresenterTable.TABLE_NAME, allColumns, null, null, null, null, null);
    }

    public Cursor getPresenterById(long id){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(PresenterTable.TABLE_NAME, allColumns, "_ID=?", new String[]{""+id}, null, null, null);
    }
}