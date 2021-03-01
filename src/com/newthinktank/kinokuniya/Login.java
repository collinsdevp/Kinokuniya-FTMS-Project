package com.newthinktank.kinokuniya;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

// import all the redirected packages here --- very important

import com.newthinktank.kinokuniya.Login;
import com.newthinktank.kinokuniya.View_Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends ActionBarActivity {
	
	// Declaration
	 EditText et,pass;
	 TextView tv;
	 HttpPost httppost;
	 StringBuffer buffer;
	 HttpResponse response;
	 HttpClient httpclient;
	 List<NameValuePair> nameValuePairs,retNameValuePairs;
	 ProgressDialog dialog = null;
	 Button loginButton;
	 StringBuilder sb = null;
	 String result = null;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		//Initialization
		et = (EditText)findViewById(R.id.uname);
        pass= (EditText)findViewById(R.id.pword);
        
		loginButton = (Button)findViewById(R.id.loginPageLoginButton);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog = ProgressDialog.show(Login.this, "","Validating user...", true);
			    new Thread(new Runnable() {

			    public void run(){
			    	
			    	retMethod();
					login();           
			     
			    }
			    
			    }).start();  
	
			}
		});
		
	} // end of onCreate method

	
	
	 private void retMethod() {
		 
			InputStream is = null;
			
			// http post
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/android_retrieval.php");
				retNameValuePairs = new ArrayList<NameValuePair>(2);

				   //for posting android side and php side variables should be similar 
				retNameValuePairs.add(new BasicNameValuePair("uname",et.getText().toString().trim()));  
				retNameValuePairs.add(new BasicNameValuePair("pword",pass.getText().toString().trim())); 
				   
				httppost.setEntity(new UrlEncodedFormEntity(retNameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection" + e.toString());
			}
			// convert response to string
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				sb = new StringBuilder();
				sb.append(reader.readLine() + "\n");
				String line = "0";
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
				//////////////////////////
				 
						
						
						///////////////////////////////////
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}  
		
	}
	 
	 void login(){
		  try{   
			  //http://cloud.byethost16.com/
		   httpclient=new DefaultHttpClient();
		   httppost= new HttpPost("http://cloud.byethost16.com/php_scripts/androidphp.php");  // http url for accessing data online
		   nameValuePairs = new ArrayList<NameValuePair>(2);

		   //for posting android side and php side variables should be similar 
		   nameValuePairs.add(new BasicNameValuePair("uname",et.getText().toString().trim()));  //for posting android 
		   nameValuePairs.add(new BasicNameValuePair("pword",pass.getText().toString().trim())); //for posting android 
		   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		   
		   
		   response=httpclient.execute(httppost); 		//Execute HTTP Post Request
		   
		   ResponseHandler<String> responseHandler = new BasicResponseHandler(); // getting the response for php online
		   final String response = httpclient.execute(httppost, responseHandler);
		   System.out.println("Response : " + response); 
		   
		   runOnUiThread(new Runnable() {
		       public void run() {
		      
		     dialog.dismiss();
		       }
		   });
		   
		   if(response.equalsIgnoreCase("User Found")){ // verify if response is good
		    runOnUiThread(new Runnable() {
		        public void run() {
		         Toast.makeText(Login.this,"Login Success", Toast.LENGTH_SHORT).show(); // // display login successful, only if
		        }
		    });
		    
		    int pageData=0;
			Bundle userBundle = getIntent().getExtras();
			
			if (userBundle !=null){
				pageData = userBundle.getInt("pagekey"); // pass data to need page
			}
		    switch(pageData)
		    {
		    
		    case 1:
		    	 	String tUsername = result;
					Bundle bundle = new Bundle();
					bundle.putString("usernameKey", tUsername);
					Intent intent = new Intent(Login.this, View_Profile.class);
					intent.putExtras(bundle);	
					startActivity(intent);
		    	break;
		    case 2:
		    	String tUsername2 = result;
				Bundle bundle2 = new Bundle();
				bundle2.putString("usernameKey", tUsername2);
				Intent intent2 = new Intent(Login.this, EditProfile.class);
				intent2.putExtras(bundle2);	
				startActivity(intent2);
		    	break;
		    case 3:
		    	String tUsername3 = result;
				Bundle bundle3 = new Bundle();
				bundle3.putString("usernameKey", tUsername3);
				Intent intent3 = new Intent(Login.this, ViewSalary.class);
				intent3.putExtras(bundle3);	
				startActivity(intent3);
		    	
		    	break;
		    case 4:
		    	String tUsername4 = result;
				Bundle bundle4 = new Bundle();
				bundle4.putString("usernameKey", tUsername4);
				Intent intent4 = new Intent(Login.this, ViewNews.class);
				intent4.putExtras(bundle4);	
				startActivity(intent4);
		    	
		    	
		    	break;
		    	
		    case 5:
		    	String tUsername5 = result;
				Bundle bundle5 = new Bundle();
				bundle5.putString("usernameKey", tUsername5);
				Intent intent5 = new Intent(Login.this, EmailHR.class);
				intent5.putExtras(bundle5);	
				startActivity(intent5);
		    	
		    	break;
		    	
		    	
		    case 8:
		    	String tUsername8 = result;
				Bundle bundle8 = new Bundle();
				bundle8.putString("usernameKey", tUsername8);
				Intent intent8 = new Intent(Login.this, QuitJob.class);
				intent8.putExtras(bundle8);	
				startActivity(intent8);
		    	
		    	break;
		    	
		    	
		    	default:
		    		String tUsernamed = result;
		  			Bundle bundled = new Bundle();
		  			bundled.putString("usernameKey", tUsernamed);
		  			Intent intentd = new Intent(Login.this, View_Profile.class);
		  			intentd.putExtras(bundled);	
		  			startActivity(intentd);
		    		
		    }

		  
		   }else{
		    showAlert();    
		   }
		   
		  }catch(Exception e){
		   dialog.dismiss();
		   System.out.println("Exception : " + e.getMessage());
		  }
		 } // login() method ends here
	 
	 
	 
	 public void showAlert(){
		  Login.this.runOnUiThread(new Runnable() {
		      public void run() {
		       AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
		       builder.setTitle("Login Error.");
		       builder.setMessage("User not Found.")  
		              .setCancelable(false)
		              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                  public void onClick(DialogInterface dialog, int id) {
		                  }
		              });              
		       AlertDialog alert = builder.create();
		       alert.show();       
		      }
		  });
		 } // end of showalert() method
	 
	 
	 
	 
	 ///////////////////////////////////////////////////////////////////////////////////////
	 // not relevant  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	} // end of not relevant
	
  /////////////////////////////////////////////////////////////////////////////////////	
	
	
} // class ends here 

