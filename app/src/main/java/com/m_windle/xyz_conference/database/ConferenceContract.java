package com.m_windle.xyz_conference.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by mr_moshi on 11/13/2015.
 */
public final class ConferenceContract {
    public ConferenceContract() { }

    // PRESENTERS TABLE
    public static abstract class PresenterTable implements BaseColumns {
        public static final String TABLE_NAME = "presenters";
        public static final String COLUMN_NAME_PRESENTER_ID = "_id";
        public static final String COLUMN_NAME_FNAME = "fname";
        public static final String COLUMN_NAME_LNAME = "lname";
        public static final String COLUMN_NAME_AFFILIATION = "affiliation";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_BIO = "bio";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + PresenterTable.TABLE_NAME + " (" +
                        PresenterTable.COLUMN_NAME_PRESENTER_ID + " INTEGER PRIMARY KEY," +
                        PresenterTable.COLUMN_NAME_FNAME + TEXT_TYPE + COMMA_SEP +
                        PresenterTable.COLUMN_NAME_LNAME + TEXT_TYPE + COMMA_SEP +
                        PresenterTable.COLUMN_NAME_AFFILIATION + TEXT_TYPE + COMMA_SEP +
                        PresenterTable.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                        PresenterTable.COLUMN_NAME_BIO + TEXT_TYPE +
                " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PresenterTable.TABLE_NAME;
    }
}
