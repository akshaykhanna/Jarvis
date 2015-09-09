package com.bdby.jarvis;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Db_ui extends Activity implements OnClickListener
{
   EditText etN,etE;
   TextView tvD;
   Button bI,bV;
   DBAdapter obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_ui);
		obj=new DBAdapter(this);
		intial();
		
	}
	private void intial() {
		// TODO Auto-generated method stub
		
		bI=(Button)findViewById(R.id.bInsert);
		bV=(Button)findViewById(R.id.bView);
		etN=(EditText)findViewById(R.id.etEmail);
		etE=(EditText)findViewById(R.id.etName);
		bI.setOnClickListener(this);
		bV.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bInsert:
			obj.open();
			try
			{
				long id=obj.insertContact(etN.getText().toString(), etE.getText().toString(),"0");
			
			if(id!=-1)
			{
				Toast.makeText(getBaseContext(), "Value succesfull inserted to DB", Toast.LENGTH_LONG).show();
			}
			else
				Toast.makeText(getBaseContext(), "Unable to insert to DB", Toast.LENGTH_LONG).show();
			}
			catch(Exception E)
			{
				Toast.makeText(getBaseContext(), "Unable to insert to DB", Toast.LENGTH_LONG).show();
			}
			obj.close();
			break;
		case R.id.bView:
			obj.open();
			Cursor c = obj.getAllContacts();
			if (c.moveToFirst())
			{
			do {
			DisplayContact(c);
			} while (c.moveToNext());
			}
			obj.close();
			break;
			
		}
	}
	
	public void DisplayContact(Cursor c)
	{
	
	Toast.makeText(getBaseContext(),"id: " + c.getString(0) + "\n" +
	"App Name: " + c.getString(1) + "\n" +
	"Package Name: " + c.getString(2)+ "\n" +
	"Counter: " + c.getString(3),
	Toast.LENGTH_LONG).show();
	}

}
