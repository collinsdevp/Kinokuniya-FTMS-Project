package com.newthinktank.kinokuniya;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Home extends ActionBarActivity {

	Button b;
	
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) { // all logic enters inside this method 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
              
        // started here
        Button btn_go=(Button)findViewById(R.id.more); // initialize navigation button
        b = (Button) findViewById(R.id.searchButton);  // initialize search navigation button
        
        btn_go.setOnClickListener(new View.OnClickListener() {  // onclick event 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home.this, MoreOptions.class);
				startActivity(intent);
			}
		});

        
 Button btn_go2=(Button)findViewById(R.id.menu);
        
        btn_go2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home.this, Login.class); // login object to link to login page
				startActivity(intent); // display login activity
			}
		});

    	//
    	final AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create(); // alert box for any error
    	 
        // Setting Dialog Title
        alertDialog.setTitle(":: Detail ::");

        // Setting Dialog Message
        alertDialog.setMessage("Sever Time-Out.");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.sever_error);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
        });
        
        
        b.setOnClickListener(new View.OnClickListener() {
  			@Override
  			public void onClick(View v) {
				alertDialog.show();
  			}
  		});

   

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
