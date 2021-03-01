package com.newthinktank.kinokuniya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Intro extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.intro);
		
		Thread timer = new Thread()
		{
			public void run()
			{
				
				try
				{
					sleep(5000);
					
					
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
					
				}
				finally
				{
					Intent intent = new Intent("com.newthinktank.kinokuniya.MAINACTIVITY");
					startActivity(intent);
				}
				
			}
			
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		finish();
	}

	
	
}
