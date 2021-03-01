package com.newthinktank.kinokuniya;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewNews extends ActionBarActivity {

	TextView ansNu;
	String userData;
	Button b;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_news);
		
		//Initialization
		//ansNu = (TextView)findViewById(R.id.ansNu);

		//////////////////////////////////////////////////////
		//Bundle userBundle = getIntent().getExtras();
		
		//if (userBundle !=null){
		//userData = userBundle.getString("usernameKey");
		
		//ansNu.setText(userData);
		//}
		
		////////////////////////////////////////////////////////////
		
		b = (Button) findViewById(R.id.backButton);
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ViewNews.this, MoreOptions.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
