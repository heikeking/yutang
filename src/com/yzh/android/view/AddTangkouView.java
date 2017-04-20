package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AddTangkouView extends FrameLayout {
	private TextView name;
	private EditText edit;

	public AddTangkouView(Context context) {
		super(context);
		init();
	}

	public AddTangkouView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	public String getEditText(){
		return edit.getText().toString();
	}
	public void setNameText(CharSequence text) {
		name.setText(text);
	}
	public void setHint(CharSequence text){
		edit.setHint(text);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_addtangkou_1, this);
		name = (TextView) findViewById(R.id.addtangkou_name);
		edit = (EditText) findViewById(R.id.addtangkou_edit);
	}

}
