package com.bdby.jarvis;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ModifyActivity extends Activity
{
	private void handle_actionBar(String title)
    {
		// TODO Auto-generated method stub
    	 ColorDrawable colorDrawable = new ColorDrawable();
		ActionBar acb = getActionBar();
		//colorDrawable.setColor(0xff006B00);
       // acb.setBackgroundDrawable(colorDrawable);
        //enable home button
        acb.setHomeButtonEnabled(true);
        //change title of action bar
        acb.setTitle(title);
		//acb.setBackgroundDrawable(d);
	}
  

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read_my_sms, menu);
        return true;
    }

	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    
	    
	   
	    case R.id.action_Devloper:
		      Toast.makeText(this, "Developer selected", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    case R.id.action_Rate:
		      Toast.makeText(this, "Rate selected", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    default:
	      break;
	    }

	    return true;
	  } 
}
