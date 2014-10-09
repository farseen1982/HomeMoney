package com.farseen.homemoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.farseen.homemoney.data.HomeMoneyDbAdapter;

public class JournalActivity extends Activity {
	ListView listView;
	private HomeMoneyDbAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journal_actvity);
		adapter = new HomeMoneyDbAdapter(this);
		adapter.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.journal_actvity, menu);
		return true;
	}

}
