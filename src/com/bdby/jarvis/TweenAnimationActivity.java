package com.bdby.jarvis;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenAnimationActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween_animation_lay);
		
		//get the sun View
		ImageView sun = (ImageView) findViewById(R.id.sun);
		
		//get the sunrise animation
		Animation sunRise = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
		
		//apply the animation to the View
		sun.startAnimation(sunRise);
		
	}
  
}
