package com.yzh.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FitImageView extends ImageView {
	private Drawable mBitmap;
	private int mBitmapWidth;
	private int mBitmapHeight;

	private boolean mReady;
	private boolean mSetupPending;

	public FitImageView(Context context) {
		super(context);
		init();
	}

	public FitImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FitImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mReady = true;
		Log.i("1234", "init");
		if (mSetupPending) {
			Log.i("1234", "init start");
			setup();
			mSetupPending = false;
		}
	}

	private void reSize() {
		int width = getResources().getDisplayMetrics().widthPixels;
		int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.8);
		int imgH = mBitmapHeight;
		int imgW = mBitmapWidth;
		int lastH = imgH;
		int lastW = imgW;
		double radio = 1.0;
		radio = (width * 1.0) / imgW;
		if (imgH * radio > height) {
			radio = (height * 1.0) / imgH;
		}
		lastH = (int) (radio * imgH);
		lastW = (int) (radio * imgW);
		LayoutParams lp = this.getLayoutParams();
		Log.i("1234", "\nlastW:"+lastW);
		Log.i("1234", "lp.width:"+lp.width);
		lp.width = lastW;
		lp.height = lastH;
		this.setLayoutParams(lp);
	}
	
	/*@Override
	public void set(Bitmap bm) {
		super.setImageBitmap(bm);
		mBitmap = bm;
		setup();
		reSize();
	}*/
	@Override
	public void setImageDrawable(Drawable drawable) {
		super.setImageDrawable(drawable);
		mBitmap = drawable;
		setup();
		reSize();
	}
	private void setup() {
		if (!mReady) {
			mSetupPending = true;
			return;
		}

		if (mBitmap == null) {
			return;
		}

		mBitmapHeight = mBitmap.getIntrinsicHeight();
		mBitmapWidth = mBitmap.getIntrinsicWidth();

		invalidate();
	}
}
