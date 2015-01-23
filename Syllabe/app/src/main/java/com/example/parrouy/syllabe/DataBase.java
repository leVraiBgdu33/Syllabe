package com.example.parrouy.syllabe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase {

    public static final String KEY_ROWID = "id";
    public static final String KEY_WORD = "mot";
    public static final String KEY_S1 = "syllabe1";
    public static final String KEY_S2 = "syllabe2";

    private static final String TAG = "Syllabe";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE_1 = "mot2syllabes";
    private static final String DATABASE_TABLE_2 = "mot3syllabes";
    private static final String DATABASE_TABLE_3 = "mot4syllabes";

    private static final String CREATE_DATABASE_TABLE_1 = "CREATE TABLE "
            + DATABASE_TABLE_1 + "(" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_WORD
            + " TEXT NOT NULL," + KEY_S1 + " TEXT NOT NULL," + KEY_S2 + " TEXT NOT NULL" + ")";

    private static final int DATABASE_VERSION = 6;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE_TABLE_1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS mot2syllabes;");
            //db.execSQL("DROP TABLE IF EXISTS relations;");
            onCreate(db);
        }
    }

    public DataBase(Context ctx) {
        this.mCtx = ctx;
    }

    public DataBase open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long insertWordEasy(String mot, String s1, String s2) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_WORD, mot);
        initialValues.put(KEY_S1, s1);
        initialValues.put(KEY_S2, s2);
        return mDb.insert(DATABASE_TABLE_1, null, initialValues);
    }

    public boolean deleteWordEasy(long rowId) {

        return mDb.delete(DATABASE_TABLE_1, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor fetchAllEasyWords() {

        return mDb.query(DATABASE_TABLE_1, new String[] {KEY_ROWID, KEY_WORD, KEY_S1, KEY_S2}, null, null, null, null, null);
    }

    /*public boolean updateNote(long rowId, String name) {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/

    public Cursor getSyllabeEasyWord(String word){
        return mDb.query(true, DATABASE_TABLE_1, new String[] {KEY_WORD,KEY_S1,
                        KEY_S2}, KEY_WORD + "=?", new String[] { word }, null,
                null, null, null, null);
    }
}
