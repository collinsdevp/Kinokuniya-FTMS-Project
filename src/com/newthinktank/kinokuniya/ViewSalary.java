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
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ViewSalary extends ActionBarActivity {

	TextView empid;
	String userData;
	RadioButton ft,pt;
	TextView fname,lname,dob,daysworked,salary,emptype;
	public String result, empidr,fnamer,lnamer,salaryr,dobr,daysworkedr,emptyper;
	String imager;
	List<NameValuePair> nameValuePairs;
	ImageView imageView ;
	StringBuilder sb;
	Button b,b2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_salary);
		
		//Initialization
		empid = (TextView)findViewById(R.id.empid_); 	//Initialization empid
		fname = (TextView)findViewById(R.id.fname_); 	//Initialization fname
		lname = (TextView)findViewById(R.id.descriptionTextView); //Initialization description
		dob = (TextView)findViewById(R.id.dob);
		salary = (TextView)findViewById(R.id.salaryPay);
		daysworked = (TextView)findViewById(R.id.daysworked);
		ft = (RadioButton)findViewById(R.id.fradio); //Initialization employment type
		pt = (RadioButton)findViewById(R.id.radio1);
		imageView  = (ImageView) findViewById(R.id.iv);
		b  = (Button) findViewById(R.id.menu_);
		b2  = (Button) findViewById(R.id.yourChart);		 //Initialization display graph button
				
		Bundle userBundle = getIntent().getExtras();
		if (userBundle !=null){
		userData = userBundle.getString("usernameKey");
		empid.setText(userData);
		}
		//////////////////
		//////////////////
		GetData();    					// get data for server method, get user data from internet
		fname.setText(fnamer); 			// set fname
		lname.setText(lnamer);          //  set lname
		dob.setText(dobr);				// set dob
		salary.setText(salaryr);		// set SALARY
		daysworked.setText(daysworkedr); 	// set amount of days workes
		
		if(emptyper.contains("1")) 
		{
		
			ft.setChecked(true);		 // verify if employee is full time or part time type
			pt.setChecked(false);		
		}
		
		else
		{
			pt.setChecked(true);
			ft.setChecked(false);
		}
		
		
		 new LoadImage().execute(imager);
		 
		 b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(ViewSalary.this, MoreOptions.class);
					startActivity(intent);
				}
			});
		 
		 b2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub					
					String data = userData;
					Bundle bundle = new Bundle();
					bundle.putString("pagekey", data);
					Intent intent = new Intent(ViewSalary.this, YourGraph.class);
					intent.putExtras(bundle);	
					startActivity(intent);
				}
			});
	}
		
public void GetData() {
			 InputStream is = null;
	     try{
	         HttpClient httpclient = new DefaultHttpClient();
	         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/view_salary_android.php");
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
				jArray = new JSONArray(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     JSONObject json_data=null;
		     for(int i=0;i<jArray.length();i++)
		     {
		             try {
						json_data = jArray.getJSONObject(i);
			            fnamer=json_data.getString("fname");
			            lnamer=json_data.getString("lname");
			            dobr=json_data.getString("dob");
			            salaryr=json_data.getString("salary");
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
		getMenuInflater().inflate(R.menu.view_salary, menu);
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
