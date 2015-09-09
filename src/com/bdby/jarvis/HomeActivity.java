package com.bdby.jarvis;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
 
public class HomeActivity extends Activity implements OnClickListener {
    
	ToggleButton tb;
	TextView tvTimer;
    
	LinearLayout ll1,ll2;
	int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intial();
    }
     
    private void intial() {
		// TODO Auto-generated method stub
    	tvTimer=(TextView)findViewById(R.id.tv_Home_TimePanic);
        
		tb=(ToggleButton)findViewById(R.id.tb_Home_Panic);
		ll1=(LinearLayout)findViewById(R.id.LL_Home_ClockSettings);
		ll2=(LinearLayout)findViewById(R.id.LL_Home_ProgressBarTV);
		tb.setOnClickListener(this);
	}

	public void openClockActivity(View v)
	{
		Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
		startActivity( LaunchIntent );
	}
    public void showApps(View v){
        Intent i = new Intent(this, AppsListActivity.class);
        startActivity(i);
    }
    
    public void openDialerApp(View v){
    	Intent intent = new Intent(Intent.ACTION_DIAL);
    	//intent.setData(Uri.parse("tel:0123456789"));
    	startActivity(intent);
    }
    
    public void openVoiceAssitantActivity(View v){
    	Intent ia=new Intent(HomeActivity.this,VoiceAssitant.class);
		startActivity(ia);
    }

	@Override
	public void onClick(View id) {
		// TODO Auto-generated method stub
	 switch(id.getId())
	 {
	 case R.id.tb_Home_Panic:
		 //Toast.makeText(this,"Hi panic button is touched !",Toast.LENGTH_SHORT).show();
		 if( tb.isChecked() )
		 {
			 Toast.makeText(this,"TB enabled!",Toast.LENGTH_SHORT).show();
			 ll1.setVisibility(View.GONE);
			 ll2.setVisibility(View.VISIBLE);
			
			 try
			 {
					final ProgressBar	pb=(ProgressBar)findViewById(R.id.progressBar1);
					pb.setMax(30);
					pb.setProgress(0);
			 i=1; final int  j=30;
			 CountDownTimer mCountDownTimer;
			 pb.setProgress(i);
			    mCountDownTimer=new CountDownTimer(30000,1000) {

			         @Override
			         public void onTick(long millisUntilFinished) {
			             Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
			           
			             tvTimer.setText(""+(j-i)+"");
			             i++;
			             pb.setProgress(i);

			         }

			         @Override
			         public void onFinish() {
			         //Do what you want 
			             i++;
			             tvTimer.setText("Alert Message Sent!");
			             ll2.setVisibility(View.GONE);
						 ll1.setVisibility(View.VISIBLE);
			            // pb.setProgress(0);
			         }
			     };
			     mCountDownTimer.start();
			     
			 }
			 catch(Exception e)
			 {
				 Toast.makeText(this,"Problem with timer & Progress Bar.\n"+e.toString(), Toast.LENGTH_SHORT);
			 }
		 }
		 else
		 {
			 Toast.makeText(this,"TB disabled!",Toast.LENGTH_SHORT).show();
			 ll2.setVisibility(View.GONE);
			 ll1.setVisibility(View.VISIBLE);
		 }
	 }
	}
}