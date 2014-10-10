package com.farseen.homemoney.data;

import java.util.Calendar;
import java.util.Date;

import com.farseen.homemoney.HomeMoneyConst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HomeMoneyDbAdapter {
	/*
	 * amount; date; member; comment; type;
	 */
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_DATE = "date";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_MEMBER = "member";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_TYPE = "type";

	private static final String TAG = "HomeMoneyDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE = "create table journal(_id integer primary key autoincrement, "
			+ "amount real not null, date text not null, member text not null, comment text not null, type text not null);";

	private static final String DATABASE_NAME = "HOME_MONEY_DB";
	private static final String DATABASE_TABLE = "journal";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS journal");
			onCreate(db);
		}
	}

	public HomeMoneyDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public HomeMoneyDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		Log.v(HomeMoneyConst.TAG, mDb.toString());
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long insertJournal(Journal j) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_AMOUNT, j.getAmount());
		initialValues.put(KEY_MEMBER, j.getMember());
		initialValues.put(KEY_DATE, j.getDateString());
		initialValues.put(KEY_COMMENT, j.getComment());
		initialValues.put(KEY_TYPE, j.getType());
		Calendar calendar = Calendar.getInstance();
		String created = calendar.get(Calendar.YEAR) + ""
				+ calendar.get(Calendar.MONTH) + ""
				+ calendar.get(Calendar.DAY_OF_MONTH) + ""
				+ calendar.get(Calendar.HOUR_OF_DAY) + ""
				+ calendar.get(Calendar.MINUTE) + "";

		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteJournal(Journal j) {
		long rowId = j.getId();
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public Cursor getAllJournals() {

		return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_AMOUNT,
				KEY_DATE, KEY_MEMBER, KEY_COMMENT, KEY_TYPE }, null, null, null, null, null);
	}

	public Cursor getJournal(long rowId) throws SQLException {

		Cursor mCursor =
		mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_AMOUNT,
				KEY_MEMBER, KEY_DATE, KEY_COMMENT, KEY_TYPE }, KEY_ROWID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public boolean updateJournal( Journal j) {
		ContentValues args = new ContentValues();
		args.put(KEY_AMOUNT, j.getAmount());
		args.put(KEY_MEMBER, j.getMember());
		args.put(KEY_DATE, j.getDateString());
		args.put(KEY_MEMBER, j.getMember());
		args.put(KEY_COMMENT, j.getComment());
		args.put(KEY_TYPE, j.getType());
		Calendar calendar = Calendar.getInstance();
		String created = calendar.get(Calendar.YEAR) + " "
				+ calendar.get(Calendar.MONTH) + " "
				+ calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + " "
				+ calendar.get(Calendar.MINUTE) + " ";
		
		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + j.getId(), null) > 0;
	}

}
