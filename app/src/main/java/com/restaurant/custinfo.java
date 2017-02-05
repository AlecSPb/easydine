package com.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class custinfo extends Activity {
	EditText cust_name,cust_email,cust_phone;
	Button save;
	ModelClass mClass;
	DBHandler cust_Helper;
	public static String c_name,c_mail, c_phone ;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.custinfo);
        try{
        	cust_Helper = new DBHandler(custinfo.this);
        	cust_Helper.getWritableDatabase();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
       //load form widget
        mClass = new ModelClass();
        save=(Button)findViewById(R.id.Save);
        cust_name = (EditText)findViewById(R.id.cust_name);
        cust_email =(EditText)findViewById(R.id.cust_email);
        cust_phone =(EditText)findViewById(R.id.cust__number);
        
        
        
       save.setOnClickListener(new OnClickListener() {
		
    	   @Override
			public void onClick(View v) {
    		 
    		 c_name = cust_name.getText().toString();
    		 c_mail = cust_email.getText().toString();
    		 c_phone = cust_phone.getText().toString();

			     		   if(c_name.equals("")||c_mail.equals("")||c_phone.equals("")){
    			  Toast.makeText(custinfo.this, "All fields are Mandatory", Toast.LENGTH_LONG).show(); 
    		   }
    		   else{
    			   Intent i =new Intent(custinfo.this,RestaurantActivity.class);
    			   Bundle bundle = new Bundle();
    			   bundle.putString("cust_name", c_name);
    			   i.putExtras(bundle);


    			   mClass.setName(c_name);
    			   mClass.setEmail(c_mail);
    			   mClass.setPhone(c_phone);

    	       
    			   cust_Helper.addCustomer(mClass);

							   startActivity(i);

							   finish();
    			   
    		   }
			}
    	   	
       });
     
      
	}
	public void onResume(){
		super.onResume();
		cust_Helper = new DBHandler(custinfo.this);
		cust_Helper.getWritableDatabase();
	}
	public void onStop(){
		super.onStop();
		cust_Helper.close();
	
	}
}
