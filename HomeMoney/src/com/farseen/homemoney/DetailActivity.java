package com.farseen.homemoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity {
	OnClickListener btnHandeler;
	Button btnSave;
	Button btnCancal;
	Button btnShowList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		setListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	private void setListener() {
		btnHandeler = new OnClickListener() {
			public void onClick(View view) {
				int id = view.getId();
				switch (id) {
				case R.id.btn_journal:
					navJournal();
				case R.id.btn_entry:
					insertJournal();
				}
			}

		};
		btnShowList = (Button) this.findViewById(R.id.btn_journal);
		btnShowList.setOnClickListener(btnHandeler);

	}

	private void navJournal() {
		Intent intent = new Intent(DetailActivity.this, JournalActivity.class);
		this.startActivity(intent);

	}

	private void insertJournal() {
		EditText date = (EditText)this.findViewById(R.id.edit_date);
		EditText amount = (EditText)this.findViewById(R.id.edit_exp);
		EditText member = (EditText)this.findViewById(R.id.edit_member);

	}

}
