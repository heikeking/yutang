package com.yzh.android.util;

import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Context;
import android.view.View;

public class AnimationUtils {
	
	public static AnimationUtils animationAdapter;
	private Context c;
	private AnimationUtils(Context c){
		this.c=c;
	}
	public static AnimationUtils getInstance(Context c){
		if (animationAdapter==null) {
			synchronized (AnimationUtils.class) {
				if (animationAdapter == null) {
					animationAdapter = new AnimationUtils(c);
				}
			}
		}
		return animationAdapter;
	}
	
	public void canvasView(View v,String propertyName,int continueTime){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, propertyName, 1f, 0f, 1f);
		animator.setDuration(continueTime);  
		animator.start();
	}
}
