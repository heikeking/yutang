package com.yzh.android.view;
import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AddTangkouView_2 extends FrameLayout {
	private TextView name;
	private NiceSpinner_view niceSpinner_view;

	public AddTangkouView_2(Context context) {
		super(context);
		init();
	}

	public AddTangkouView_2(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	public String getEditText(){
		return niceSpinner_view.getText().toString();
	}
	public NiceSpinner_view getNiceSpinner_view(){
		return niceSpinner_view;
	}
	public void setNameText(CharSequence text) {
		name.setText(text);
	}
	public int getSelectedIndex(){
		return niceSpinner_view.getSelectedIndex();
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_addtangkou_2, this);
		name = (TextView) findViewById(R.id.addtangkou_name);
		niceSpinner_view = (NiceSpinner_view) findViewById(R.id.addtangkou_spinner2);
	}

}