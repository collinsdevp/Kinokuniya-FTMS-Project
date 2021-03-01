package com.newthinktank.kinokuniya;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YourGraph extends ActionBarActivity {
	LinearLayout la;
	String  returnData,userData;
	String result ;
	String un, deux,trois,quatre,cinq,six,sept,huit,neuf,dix,onze,douze;
	TextView values_;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your_graph);
		 la = (LinearLayout)findViewById(R.id.lchart);
	     values_= (TextView)findViewById(R.id.values_);
	     
		 //////////////////////
		 Bundle userBundle = getIntent().getExtras();
			if (userBundle !=null){
			userData = userBundle.getString("pagekey");
			// Console.WriteLine(userData);
			}
		 
			GetData();
		 ////////////////////
		 
		 
	        int color[] = {1,1,1,1,1,1,1,1,1,1,1,1};
	        int Height[]={Integer.parseInt(un),Integer.parseInt(deux),Integer.parseInt(trois),
	        		Integer.parseInt(quatre),Integer.parseInt(cinq),Integer.parseInt(six),
	        		Integer.parseInt(sept),Integer.parseInt(huit),Integer.parseInt(neuf),Integer.parseInt(dix)
	        		,Integer.parseInt(onze),Integer.parseInt(douze)};
	        
	        for(int j=0; j<color.length; j++)
	        {
	        	
	        	drawChart(1,color[j],Height[j]);
	        }
	        
	        
	        GetData2();
	        displayViewProfileJson2();
	        values_.setText(un+","+deux+","+trois+","+quatre+","+cinq+","+six+","+sept+
	        		","+huit+","+neuf+","+dix+","+onze+","+douze);
	    }
	

	 private void displayViewProfileJson2() {
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
		            un=json_data.getString("un");
		            deux=json_data.getString("deux");
		            trois=json_data.getString("trois");
		            quatre=json_data.getString("quatre");
		            cinq=json_data.getString("cinq");
		            six=json_data.getString("six");
		            sept=json_data.getString("sept");
		            huit=json_data.getString("huit");
		            neuf=json_data.getString("neuf");
		            dix=json_data.getString("dix");
		            onze=json_data.getString("onze");
		            douze=json_data.getString("douze");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
	}


	private void GetData2() {
		 InputStream is = null;
	     try{
	         HttpClient httpclient = new DefaultHttpClient();
	         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/graph_value.php");
	         ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);  
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
	         StringBuilder sb = new StringBuilder();
	         sb.append(reader.readLine());
	         String line="0";
	       
	         while ((line = reader.readLine()) != null) {
	             sb.append(line);
	         }
	         is.close();
	          result = sb.toString();
	     }catch(Exception e){

	     }

	     displayViewProfileJson2();    
		
	}

	
	

	public void GetData() {
		 InputStream is = null;
     try{
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost("http://cloud.byethost16.com/php_scripts/graph_data.php");
         ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);  
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
         StringBuilder sb = new StringBuilder();
         sb.append(reader.readLine());
         String line="0";
       
         while ((line = reader.readLine()) != null) {
             sb.append(line);
         }
         is.close();
          result = sb.toString();
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
		            un=json_data.getString("un");
		            deux=json_data.getString("deux");
		            trois=json_data.getString("trois");
		            quatre=json_data.getString("quatre");
		            cinq=json_data.getString("cinq");
		            six=json_data.getString("six");
		            sept=json_data.getString("sept");
		            huit=json_data.getString("huit");
		            neuf=json_data.getString("neuf");
		            dix=json_data.getString("dix");
		            onze=json_data.getString("onze");
		            douze=json_data.getString("douze");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
		 
	 }
	
	
	
	 private void drawChart(int count, int color, int height) {
			// TODO Auto-generated method stub
			
	    	System.out.println(count+color+height);
	    	
	    	if(color == 1)
	    	{
	    		
	    		color=Color.BLUE;
	    	}
	    	
	    	if(color == 2)
	    	{
	    		
	    		color=Color.YELLOW;
	    	}
	    	
	    	if(color == 3)
	    	{
	    		
	    		color=Color.GREEN;
	    	}
	    	
	    	if(color == 4)
	    	{
	    		
	    		color=Color.MAGENTA;
	    	}
	    	
	    	if(color == 5)
	    	{
	    		
	    		color=Color.RED;
	    	}
	    	
	    	for(int k=1; k<=count; k++)
	    	{
	    		View view = new View(this);
	    		view.setBackgroundColor(color);
	    		view.setLayoutParams(new LinearLayout.LayoutParams(30,height));
	    		
	    		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)view.getLayoutParams();
	    		params.setMargins(3, 0, 0, 0);
	    		view.setLayoutParams(params);
	    		
	    		la.addView(view);
	    	}
	}
	    
	    
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.your_graph, menu);
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
