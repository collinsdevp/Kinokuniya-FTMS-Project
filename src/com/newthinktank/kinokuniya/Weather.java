package com.newthinktank.kinokuniya;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Weather extends ActionBarActivity {

	int bundleData;
	TextView data, descriptionTextView, temperatureTextView,pressureTextView, humidityTextView;
	String formato,rdescription,result;
	double rtemperature, rpressure, rhumidity;
	ImageView img;
	Button b;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
	//	data = (TextView) findViewById(R.id.results);
		
		Bundle userBundle = getIntent().getExtras();
		if (userBundle !=null){
			bundleData = userBundle.getInt("pagekeyWeather");
			//data.setText(Integer.toString(bundleData));
		}
		
		img = (ImageView) findViewById(R.id.imageView3);
		
		b = (Button) findViewById(R.id.yourChart);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Weather.this, ChooseCity.class);
				startActivity(intent);
			}
		});
		
		switch(bundleData)
		{
		case 1:
			getKualaLumpur();
			descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
			temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
			pressureTextView=(TextView) findViewById(R.id.pressureTextView);
			humidityTextView= (TextView) findViewById(R.id.humidityTextView);
			descriptionTextView.setText(rdescription);
			temperatureTextView.setText(formato+" F ");
			pressureTextView.setText(rpressure+" ");
			humidityTextView.setText(rhumidity+" ");
			
			img.setImageResource(R.drawable.my);
			break;
		case 2:
			getAustinTexas();
			descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
			temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
			pressureTextView=(TextView) findViewById(R.id.pressureTextView);
			humidityTextView= (TextView) findViewById(R.id.humidityTextView);
			descriptionTextView.setText(rdescription);
			temperatureTextView.setText(formato+" F ");
			pressureTextView.setText(rpressure+" ");
			humidityTextView.setText(rhumidity+" ");
			img.setImageResource(R.drawable.us);
			break;
		case 3:
			getLondonUK();
			descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
			temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
			pressureTextView=(TextView) findViewById(R.id.pressureTextView);
			humidityTextView= (TextView) findViewById(R.id.humidityTextView);
			descriptionTextView.setText(rdescription);
			temperatureTextView.setText(formato+" F ");
			pressureTextView.setText(rpressure+" ");
			humidityTextView.setText(rhumidity+" ");
			img.setImageResource(R.drawable.uk);
			break;
		case 4:
			getJapanK();
			descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
			temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
			pressureTextView=(TextView) findViewById(R.id.pressureTextView);
			humidityTextView= (TextView) findViewById(R.id.humidityTextView);
			descriptionTextView.setText(rdescription);
			temperatureTextView.setText(formato+" F ");
			pressureTextView.setText(rpressure+" ");
			humidityTextView.setText(rhumidity+" ");
			img.setImageResource(R.drawable.jp);
			break;
		case 5:
			getSouthAfrica();
			descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
			temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
			pressureTextView=(TextView) findViewById(R.id.pressureTextView);
			humidityTextView= (TextView) findViewById(R.id.humidityTextView);
			descriptionTextView.setText(rdescription);
			temperatureTextView.setText(formato+" F ");
			pressureTextView.setText(rpressure+" ");
			humidityTextView.setText(rhumidity+" ");
			img.setImageResource(R.drawable.sa);
			break;
		
		}
		
	}
	
	
	private void getSouthAfrica() {
		// TODO Auto-generated method stub
		
		 InputStream is = null;
		    try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139");
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


	private void getJapanK() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 InputStream is = null;
				    try{
				        HttpClient httpclient = new DefaultHttpClient();
				        HttpPost httppost = new HttpPost("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139");
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



	private void getLondonUK() {
		// TODO Auto-generated method stub
		 InputStream is = null;
		    try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://api.openweathermap.org/data/2.5/weather?q=London,uk");
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


	private void getAustinTexas() {
		// TODO Auto-generated method stub
		
		 InputStream is = null;
		    try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://api.openweathermap.org/data/2.5/weather?q=austin,tx");
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

	
	

	private void getKualaLumpur()
	{
		 InputStream is = null;
		    try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://api.openweathermap.org/data/2.5/weather?q=kl,my"); // data from aopenweather api
		        HttpResponse response = httpclient.execute(httppost); // execute request
		        HttpEntity entity = response.getEntity(); // get response 
		        is = entity.getContent();
		    }catch(Exception e){

		    }
		 
		    //convert response to string
		    try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8); // return results
		        StringBuilder sb = new StringBuilder();
		        sb.append(reader.readLine());
		        String line="0";
		      
		        while ((line = reader.readLine()) != null) { // loop results
		            sb.append(line);
		        }
		        is.close();
		         result = sb.toString();
		    }catch(Exception e){

		    }
		    displayViewProfileJson();     // display results method
		
	}

	
	
	  
    private void displayViewProfileJson() {
	
    	//paring data
	    
	             try {
	            	 JSONObject jsonResult = new JSONObject(result);
		            rdescription= jsonResult.getJSONArray("weather").getJSONObject(0).getString("description");
		            rtemperature= jsonResult.getJSONObject("main").getDouble("temp");
		            rtemperature = ConvertTemperatureToFarenheit(rtemperature);
		             formato = String.format("%.2f",rtemperature);
		            rpressure = jsonResult.getJSONObject("main").getDouble("pressure");
		            rhumidity = jsonResult.getJSONObject("main").getDouble("humidity");
		            
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    
    	
		
	}
	
    private double ConvertTemperatureToFarenheit(double temperature) {
    	return (temperature - 273)* (9/5) + 32;

    	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
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
