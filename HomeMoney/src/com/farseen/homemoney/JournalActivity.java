package com.farseen.homemoney;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.farseen.homemoney.data.HomeMoneyDbAdapter;

public class JournalActivity extends Activity {
	ListView listView;
	private HomeMoneyDbAdapter adapter;
	private Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journal_actvity);
		// init list view:
		listView = (ListView) this.findViewById(R.id.journalList);
		adapter = new HomeMoneyDbAdapter(this);
		adapter.open();
		renderListView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.journal_actvity, menu);
		return true;
	}

	public void renderListView() {
		mCursor = adapter.getAllJournals();
		
		Log.i(HomeMoneyConst.TAG, "Total Record: "+ mCursor.getCount());
		startManagingCursor(mCursor);
		String[] from = new String[] { HomeMoneyDbAdapter.KEY_DATE,
				HomeMoneyDbAdapter.KEY_AMOUNT, HomeMoneyDbAdapter.KEY_COMMENT };
		int[] to = new int[] { R.id.col_date, R.id.col_amount, R.id.col_comment };
		SimpleCursorAdapter jAdapter = new SimpleCursorAdapter(this,
				R.layout.journal_row, mCursor, from, to);
		listView.setAdapter(jAdapter);
	}

}
