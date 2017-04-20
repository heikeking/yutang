package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SetEnginesView  extends FrameLayout {
	public EditTextWithDeleteButton edit;
	public TextView name;
	public TextView name_duliang;

	public SetEnginesView(Context context) {
		super(context);
		init();
	}

	public SetEnginesView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		/*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();*/
	}
	
	public String getEditTextString(){
		return edit.getText();
	}
	public EditText getEditText(){
		return edit.getEditText();
	}
	public void setName(CharSequence txt){
		name.setText(txt);
	}
	public void setName_duliang(CharSequence txt){
		name_duliang.setText(txt);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_setengins, this);
		edit = (EditTextWithDeleteButton) findViewById(R.id.editTextWithDeleteButton);
		name = (TextView) findViewById(R.id.name);
		name_duliang = (TextView) findViewById(R.id.name_duliang);
	}

}
