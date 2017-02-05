package com.restaurant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity {
    
	Button go_to_menu,go_to_order_list,info;
	String user_name;
	@Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		TextView tx = (TextView)findViewById(R.id.easyDine);
		TextView user = (TextView) findViewById(R.id.username);

		Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/abc.ttf");

		tx.setTypeface(custom_font);
		final DBHandler dbHandler=new DBHandler(getApplicationContext());
		ModelClass modelClass=new ModelClass();
		final String name=modelClass.getName();
		final String email=modelClass.getEmail();
		final String phon=modelClass.getPhone();




		user.setTypeface(custom_font);
        
        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");


        
       // initialise form widget
        go_to_menu=(Button)findViewById(R.id.Go_To_Menu);
        
        go_to_order_list=(Button)findViewById(R.id.Go_To_Order_List);

        info=(Button)findViewById(R.id.Info);

        
        ModelClass.createlist();
        info.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(RestaurantActivity.this,InfoScreen.class);
				RestaurantActivity.this.startActivity(i);
			}
		});
        go_to_menu.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(RestaurantActivity.this,MenuScreen.class);
				RestaurantActivity.this.startActivity(i);
			}
		});

        go_to_order_list.setOnClickListener( new OnClickListener() {
			
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(RestaurantActivity.this,OrderList.class);
				RestaurantActivity.this.startActivity(i);
			}
		});

		user.setText("Welcome " + user_name);
    }
}