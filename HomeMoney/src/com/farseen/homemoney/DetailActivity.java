package com.farseen.homemoney;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.farseen.homemoney.data.HomeMoneyDbAdapter;
import com.farseen.homemoney.data.Journal;

public class DetailActivity extends Activity {
	OnClickListener btnHandeler;
	Button btnSave;
	Button btnCancal;
	Button btnShowList;
	HomeMoneyDbAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		setListener();
		dbAdapter = new HomeMoneyDbAdapter(this);
		dbAdapter.open();
		
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
		btnSave = (Button)this.findViewById(R.id.btn_entry);
		btnSave.setOnClickListener(btnHandeler);

	}

	private void navJournal() {
		Intent intent = new Intent(DetailActivity.this, JournalActivity.class);
		this.startActivity(intent);

	}

	private void insertJournal() {
		EditText date_s = (EditText)this.findViewById(R.id.edit_date);
		EditText amount_s = (EditText)this.findViewById(R.id.edit_exp);
		EditText member_s = (EditText)this.findViewById(R.id.edit_member);
		EditText comment_s = (EditText)this.findViewById(R.id.edit_commet);
		float amount = Float.parseFloat(amount_s.getText().toString());
		Date date = new Date(date_s.getText().toString());
		String member = member_s.getText().toString();
		String comment = comment_s.getText().toString();
		Journal newJournal = new Journal(0, amount, date, member , comment, null);
		dbAdapter.insertJournal(newJournal);
		

	}

}
