package com.newthinktank.kinokuniya;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.newthinktank.kinokuniya.View_Profile;


public class View_Profile extends ActionBarActivity {
	
	// Declaration
	String userData;
	RadioGroup selectionRadio;
	RadioButton ft,pt;
	TextView empid,fname,lname,email,dob,empdate,daysworked,ans8,emptype;
	public String result, empidr,fnamer,lnamer,emailr,dobr,empdater,daysworkedr,emptyper;
	String imager;
	List<NameValuePair> nameValuePairs;
	ImageView imageView ;
	StringBuilder sb;
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__profile);
		
		//Initialization
		empid = (TextView)findViewById(R.id.empid_);
		fname = (TextView)findViewById(R.id.fname_);
		lname = (TextView)findViewById(R.id.descriptionTextView);
		email = (TextView)findViewById(R.id.email);
		dob = (TextView)findViewById(R.id.dob__);
		empdate = (TextView)findViewById(R.id.empdate);
		daysworked = (TextView)findViewById(R.id.daysworked);
		ans8 = (TextView)findViewById(R.id.values_);
		ft = (RadioButton)findViewById(R.id.fulltimeradio);
		pt = (RadioButton)findViewById(R.id.parttimeradio);
		imageView  = (ImageView) findViewById(R.id.iv);
		b  = (Button) findViewById(R.id.backButton);
		
		
		Bundle userBundle = getIntent().getExtras();
		if (userBundle !=null){
		userData = userBundle.getString("usernameKey");
		empid.setText(userData);
		}
		
		GetData();
		
		fname.setText(fnamer);
		lname.setText(lnamer);
		email.setText(emailr);
		dob.setText(dobr);
		empdate.setText(empdater);
		daysworked.setText(daysworkedr);
		
		if(emptyper.contains("1"))
		{
		
			ft.setChecked(true);
			pt.setChecked(false);
		
		}
		else
		{
			pt.setChecked(true);
			ft.setChecked(false);
		}
		
		 new LoadImage().execute(imager);
	
		selectionRadio = (RadioGroup)findViewById(R.id.empStatusradioGroup);
		selectionRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int selected = selectionRadio.getCheckedRadioButtonId();
				RadioButton b = (RadioButton) findViewById(selected);
				//ans8.setText(b.getText().toString());
			}
		}); //end radio group		

		
		 b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(View_Profile.this, MoreOptions.class);
					startActivity(intent);
				}
			});
		
	}	// end of onCreate Method

	
	 public void GetData() {
		 InputStream is = null;
     try{
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/view_profile_android.php");
         nameValuePairs = new ArrayList<NameValuePair>(1);  
		 nameValuePairs.add(new BasicNameValuePair("empid",userData.toString().trim())); 
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
         HttpResponse response = httpclient.execute(httppost);
         HttpEntity entity = response.getEntity();
         is = entity.getContent();
     }catch(Exception e){

     }
  
     //convert response to string
     try{
         BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
         sb = new StringBuilder();
         sb.append(reader.readLine());
         String line="0";
       
         while ((line = reader.readLine()) != null) {
             sb.append(line);
         }
         is.close();
         result=sb.toString();
     }catch(Exception e){

     }

     displayViewProfileJson();    
 }	
	 public void displayViewProfileJson()
	 {
		//paring data
		    
	     JSONArray jArray = null;
		try {
			jArray = new JSONArray(result); // retrieve result variables from server and return as array
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     JSONObject json_data=null;
	     for(int i=0;i<jArray.length();i++) // loop jArray object to retrieve data result
	     {
	             try {
					json_data = jArray.getJSONObject(i);
		            fnamer=json_data.getString("fname"); // display fname and etc
		            lnamer=json_data.getString("lname");
		            emailr=json_data.getString("email");
		            dobr=json_data.getString("dob");
		            empdater=json_data.getString("emp_date");
		            daysworkedr=json_data.getString("daysworkedmonthly");
		            emptyper=json_data.getString("typeemployment");
		            imager=json_data.getString("picture");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
		 
	 }
	 
	 private class LoadImage extends AsyncTask<String, String, Bitmap> {
	        @Override
	            protected void onPreExecute() {
	                super.onPreExecute();

	        }
	           protected Bitmap doInBackground(String... args) {
	             Bitmap bitmap = null;
				try {
	                   bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
	            } catch (Exception e) {
	                  e.printStackTrace();
	            }
	          return bitmap;
	           }
	           protected void onPostExecute(Bitmap image) {
	             if(image != null){
	            	 imageView.setImageBitmap(image);
	             }else{
	 
	             }
	           }
	       }
	       
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__profile, menu);
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
