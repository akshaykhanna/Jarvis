package com.bdby.jarvis;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class VoiceAssitant extends Activity
{
    String early[]={"Good Morning Sir, \nJarvis is here at your service !",
    		"Guten Morgen Sir, \nHow may I help you !",
    		"Have no fear, Jarvis is here! \nSir tell me what you want ?",
    		"Greetings Sir , \nHow may I help !"};
    String noon[]={"Hello Sir, How may I help you !",
    		"Greetings Sir , How may I help !",
    		"Sun is sunny and Jarvis is funny, Tell me how can I help !"};
    String late[]={"Jarvis is at your service 24 O 7 ! \nAsk me what you want.",
    		"Hello Sir, How may I help you !", 
    "Tipi Tipi top ask me what you want!",
     "Sir still awake, \nAsk me something, I am for your sake.",
     
     "Sun is sunny and Jarvis is funny,\nTell me how can I help !"};
    
    
    TextToSpeech tts;
    String text="Text to speech Working";
    TextView tvJ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_assitant_lay);
		intial();
		tvJ.setText(text);
		
		tts=new TextToSpeech(this, 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			             tts.setLanguage(Locale.US);
			            }				
			         }
			      });
		 //tts.speak("Hello Hi Love you ", TextToSpeech.QUEUE_FLUSH, null);
		 CountDownTimer mCountDownTimer;
		  mCountDownTimer=new CountDownTimer(2000,1000) {

		         @Override
		         public void onTick(long millisUntilFinished) {
		             Log.v("Log_tag", "Tick of Progress"+ millisUntilFinished);
		           
		          
		         }

		         @Override
		         public void onFinish() {
		         //Do what you want 
		        	 speakUp(null);
		          
		         }
		     };
		     mCountDownTimer.start();

	}
	private void intial() {
		// TODO Auto-generated method stub
		tvJ=(TextView)findViewById(R.id.tv_VA_JarvisText);
		
	}

	public void speakUp(View v)
	{
		text=selectTextAsPerTime();
		 tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	private String selectTextAsPerTime()
			{
				String t;
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
				//tvJ.setText("Hour:"+today.hour);
				int hour=today.hour;
				
				Random rnd=new Random();
				if(hour>=3 && hour <=10)
				{
					
					t=early[rnd.nextInt(4)];
					
				}
				else
				{
					
					t=late[rnd.nextInt(5)];
				}
				tvJ.setText(""+t);
				return t;
				
			}
		
	
	
	void OpenVoiceRecognition(View v)
	{
		
	}
	
	public void onPause(){
	      if(tts !=null){
	         tts.stop();
	         tts.shutdown();
	      }
	      super.onPause();
	   }
  
}
