package com.newthinktank.kinokuniya;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MoreOptions extends ActionBarActivity {

	String classes[] = {"Home","View Profile","Edit Profile","View Salary","View News Update","Email H.R.","Weather Report","Time Zone","Quit Job"};
	ListView listview ; // array for navigation labels 
	@Override
	protected void onCreate(Bundle savedInstanceState) { // all logic  enters this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more__options);
		listview = (ListView) findViewById(R.id.listView1); // initialize of the ListView
		listview.setAdapter(new ArrayAdapter<String>(MoreOptions.this,R.layout.list_item,classes)); // display the ListView
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {				// list event

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {   						
				
				switch(position)
				{
				case 0:
					Intent intent0 = new Intent(MoreOptions.this, Home.class); // redirect to  this page
					startActivity(intent0);
					break;
				case 1:
					
					int pageIndex = 1;
					Bundle bundle = new Bundle();
					bundle.putInt("pagekey", pageIndex);
					Intent intent1 = new Intent(MoreOptions.this, Login.class);  // redirect to this page
					intent1.putExtras(bundle);	
					startActivity(intent1);
	
					break;
				case 2:
					int pageIndex2 = 2;
					Bundle bundle2 = new Bundle();
					bundle2.putInt("pagekey", pageIndex2);
					Intent intent2 = new Intent(MoreOptions.this, Login.class);  // redirect to this page
					intent2.putExtras(bundle2);	
					startActivity(intent2);
					
					break;
					
				case 3:
					
					int pageIndex3 = 3;
					Bundle bundle3 = new Bundle();
					bundle3.putInt("pagekey", pageIndex3);
					Intent intent3 = new Intent(MoreOptions.this, Login.class);
					intent3.putExtras(bundle3);	
					startActivity(intent3);
					
					break;
				case 4:
					int pageIndex4 = 4;
					Bundle bundle4 = new Bundle();
					bundle4.putInt("pagekey", pageIndex4);
					Intent intent4 = new Intent(MoreOptions.this, Login.class);
					intent4.putExtras(bundle4);	
					startActivity(intent4);
					
					break;
				case 5:
					int pageIndex5 = 5;
					Bundle bundle5 = new Bundle();
					bundle5.putInt("pagekey", pageIndex5);
					Intent intent5 = new Intent(MoreOptions.this, Login.class);
					intent5.putExtras(bundle5);	
					startActivity(intent5);
					
					break;
				case 6:
					//int pageIndex6 = 6;
					//Bundle bundle6 = new Bundle();
					//bundle6.putInt("pagekey", pageIndex6);
					Intent intent6 = new Intent(MoreOptions.this, ChooseCity.class);
					//intent6.putExtras(bundle6);	
					startActivity(intent6);
					break;
				case 7:
					Intent intent7 = new Intent(MoreOptions.this, TimeZ.class);
					startActivity(intent7);
					
					
					break;
				case 8:
					int pageIndex8 = 8;
					Bundle bundle8 = new Bundle();
					bundle8.putInt("pagekey", pageIndex8);
					Intent intent8 = new Intent(MoreOptions.this, Login.class);
					intent8.putExtras(bundle8);	
					startActivity(intent8);
					
					
					break;
				default:
					
				}
				
				
			}
		});
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.more__options, menu);
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
