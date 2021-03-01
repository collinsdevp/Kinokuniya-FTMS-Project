package com.newthinktank.kinokuniya;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseCity extends ActionBarActivity {
	
Spinner s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_city);
		s = (Spinner) findViewById(R.id.chooseCitySpinner);
		
		
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				switch (position){
				case 1:
					int pageIndex = 1;
					Bundle bundle = new Bundle();
					bundle.putInt("pagekeyWeather", pageIndex);
					Intent intent1 = new Intent(ChooseCity.this, Weather.class);
					intent1.putExtras(bundle);	
					startActivity(intent1);
					break;
				case 2:
					int pageIndex2 = 2;
					Bundle bundle2 = new Bundle();
					bundle2.putInt("pagekeyWeather", pageIndex2);
					Intent intent2 = new Intent(ChooseCity.this, Weather.class);
					intent2.putExtras(bundle2);	
					startActivity(intent2);
					break;
				case 3:
					int pageIndex3 = 3;
					Bundle bundle3 = new Bundle();
					bundle3.putInt("pagekeyWeather", pageIndex3);
					Intent intent3 = new Intent(ChooseCity.this, Weather.class);
					intent3.putExtras(bundle3);	
					startActivity(intent3);
					break;
				case 4:
					int pageIndex4 = 4;
					Bundle bundle4 = new Bundle();
					bundle4.putInt("pagekeyWeather", pageIndex4);
					Intent intent4 = new Intent(ChooseCity.this, Weather.class);
					intent4.putExtras(bundle4);	
					startActivity(intent4);
					
					break;
				case 5:
					int pageIndex5 = 5;
					Bundle bundle5 = new Bundle();
					bundle5.putInt("pagekeyWeather", pageIndex5);
					Intent intent5 = new Intent(ChooseCity.this, Weather.class);
					intent5.putExtras(bundle5);	
					startActivity(intent5);
					
					break;
				default:
					
				}
				
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_city, menu);
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
