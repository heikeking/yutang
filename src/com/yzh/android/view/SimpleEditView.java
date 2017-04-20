package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SimpleEditView extends FrameLayout {
	private ImageView rightImage;
	private EditTextWithDeleteButton editTextWithDeleteButton;
	private boolean canclick = true;

	public SimpleEditView(Context context) {
		super(context);
		init();
	}

	public SimpleEditView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.accountView);
		canclick = ta.getBoolean(R.styleable.accountView_canclick_account, true);
		setRightDrawable(ta.getDrawable(R.styleable.accountView_Image));
		setHint(ta.getText(R.styleable.accountView_editText));
		ta.recycle();
	}

	public void setHint(CharSequence text) {
		editTextWithDeleteButton.editText.setHint(text);
	}

	public void setRightDrawable(Drawable drawable) {
		rightImage.setImageDrawable(drawable);
	}
	public void setInfo(String text,Drawable drawable){
		editTextWithDeleteButton.editText.setHint(text);
		rightImage.setImageDrawable(drawable);
	}
	public String getText(){
		String str=editTextWithDeleteButton.getText();
		return str;
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_account, this);
		rightImage = (ImageView) findViewById(R.id.fitImageView);
		editTextWithDeleteButton = (EditTextWithDeleteButton) findViewById(R.id.editTextWithDeleteButton);
	}

}
