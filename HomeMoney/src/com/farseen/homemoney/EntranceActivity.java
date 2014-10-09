package com.farseen.homemoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.farseen.homemoney.data.HomeMoneyDbAdapter;

public class EntranceActivity extends Activity {
	private OnClickListener btnListener;
	private Button btnEntr;
	private Button btnList;
	private Button btnMember;
	private HomeMoneyDbAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initListener();
		setListener();
		adapter = new HomeMoneyDbAdapter(this);
		adapter.open();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrance, menu);
		return true;
	}

	private void initListener() {
		btnListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = v.getId();
				Intent intent;
				Class navAct = null;
				if (id == R.id.btn_entry){
					navAct = DetailActivity.class;
				}
				if (id == R.id.btn_list){
					navAct = JournalActivity.class; 
				}
				if (id == R.id.btn_journal) {
					//todo
					navAct = JournalActivity.class;
				}
// start navigation
				intent = new Intent(EntranceActivity.this, navAct);
				startActivity(intent);
			}

		};

	}

	private void setListener() {
		btnEntr = (Button) this.findViewById(R.id.btn_entry);
		btnList = (Button) this.findViewById(R.id.btn_list);
		btnMember = (Button) this.findViewById(R.id.btn_journal);
		btnEntr.setOnClickListener(btnListener);
		btnList.setOnClickListener(btnListener);
		btnMember.setOnClickListener(btnListener);

	}

}
