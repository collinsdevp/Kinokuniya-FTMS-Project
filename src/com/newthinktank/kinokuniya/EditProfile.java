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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends ActionBarActivity {
	String result;
	TextView empid,ut;
	String userData,fnamer, lnamer, emailr, dobr,imager;
	EditText fnameEdit,lnameEdit,emailEdit,dobEdit;
	List<NameValuePair> nameValuePairs;
	ImageView imageView ;
	StringBuilder sb;
	Button b,b2;
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		
		//Initialization
				empid = (TextView)findViewById(R.id.empid);
				fnameEdit = (EditText)findViewById(R.id.fnameEdit);
				lnameEdit = (EditText)findViewById(R.id.lnameEdit);
				emailEdit = (EditText)findViewById(R.id.emailEdit);
				dobEdit = (EditText)findViewById(R.id.dobEdit);
				imageView = (ImageView) findViewById(R.id.iv);
				b = (Button) findViewById(R.id.backButton);	
				b2 = (Button) findViewById(R.id.updateButton);	
				
				
				Bundle userBundle = getIntent().getExtras();
				if (userBundle !=null){
				userData = userBundle.getString("usernameKey");
				empid.setText(userData);
				}
				
				 GetData();
				 
				fnameEdit.setText(fnamer);
				lnameEdit.setText(lnamer);
				emailEdit.setText(emailr);
				dobEdit.setText(dobr);
				
				new LoadImage().execute(imager);
				
				b.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(EditProfile.this, MoreOptions.class);
							startActivity(intent);
						}
					});
				
		final AlertDialog alertDialog = new AlertDialog.Builder(EditProfile.this).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(":: Detail Updated ::");
 
        // Setting Dialog Message
        alertDialog.setMessage("Updated");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
        });
 
        
				
				b2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sendData();
						alertDialog.show();
					
					}
				});
				
				
			
	}

	
public void sendData()
{
	
	 InputStream is = null;
     try{
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/edit_profile_saving_android.php");
         nameValuePairs = new ArrayList<NameValuePair>(5);  
		 nameValuePairs.add(new BasicNameValuePair("empid",userData.toString().trim()));  
		 nameValuePairs.add(new BasicNameValuePair("fname",fnameEdit.getText().toString().trim())); 
		 nameValuePairs.add(new BasicNameValuePair("lname",lnameEdit.getText().toString().trim())); 
		 nameValuePairs.add(new BasicNameValuePair("email",emailEdit.getText().toString().trim())); 
		 nameValuePairs.add(new BasicNameValuePair("dob",dobEdit.getText().toString().trim())); 
		 
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
         HttpResponse response = httpclient.execute(httppost);
         HttpEntity entity = response.getEntity();
         is = entity.getContent();
     }
     catch(Exception e){}

}
public void GetData() {
		 InputStream is = null;
     try{
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/edit_profile_android.php");
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
		            emailr=json_data.getString("email");
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
		getMenuInflater().inflate(R.menu.edit_profile, menu);
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
	}}