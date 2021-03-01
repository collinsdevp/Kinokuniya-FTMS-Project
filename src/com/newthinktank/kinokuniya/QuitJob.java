package com.newthinktank.kinokuniya;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class QuitJob extends ActionBarActivity {

	Button s,mn;
	TextView r;
	String userData,quitjob;
	RadioGroup selectionRadio;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quit_job);
		

		//////////////////////////////////////////////////////
		Bundle userBundle = getIntent().getExtras();
		
		if (userBundle !=null){
		userData = userBundle.getString("usernameKey");
		}
	
		//////////////////////////////////////////////////////
		
		
		r = (TextView) findViewById(R.id.reasonEdit);
		s = (Button) findViewById(R.id.more);
		mn = (Button) findViewById(R.id.menu);
		selectionRadio = (RadioGroup)findViewById(R.id.radioGroup1);
		
		selectionRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int selected = selectionRadio.getCheckedRadioButtonId();
				RadioButton b = (RadioButton) findViewById(selected);
				
				if(b.getText().toString().contains("Yes"))
				{
					quitjob="1";
				}else
				{
					quitjob="0";
					
				}
				
			}
		}); //end radio group	
		
		//
		final AlertDialog alertDialog = new AlertDialog.Builder(QuitJob.this).create();
		 
        // Setting Dialog Title
        alertDialog.setTitle(":: Detail ::");
 
        // Setting Dialog Message
        alertDialog.setMessage("Your request has been Sent. Thank You.");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
        });
		
        
		 s.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					GetData();
					alertDialog.show();
				}
			});
		
		 
		 mn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(QuitJob.this, MoreOptions.class);
					startActivity(intent);
				}
			});
		
	}
	
	
	 public void GetData() {
		 InputStream is = null;
     try{
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/android_quitjob.php");
         ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);  
		 nameValuePairs.add(new BasicNameValuePair("empid",userData.toString().trim())); 
		 nameValuePairs.add(new BasicNameValuePair("quitjob",quitjob.toString().trim())); 
		 nameValuePairs.add(new BasicNameValuePair("reason",r.getText().toString().trim())); 
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
         HttpResponse response = httpclient.execute(httppost);
         HttpEntity entity = response.getEntity();
         is = entity.getContent();
     }catch(Exception e){

     }
  
 
   
 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quit_job, menu);
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
