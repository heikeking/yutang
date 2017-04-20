package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BtnSwitchView  extends FrameLayout {
	public TextView name;
	public RadioGroup fragment_settings_rg;
	public RadioButton rb_shoudong,rb_zidong;

	public BtnSwitchView(Context context) {
		super(context);
		init();
	}

	public BtnSwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		/*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();*/
	}
	
	public void setName(CharSequence txt){
		name.setText(txt);
	}
	public void setSwitchStatus(boolean isrb_shoudong,boolean isrb_zidong){
		rb_shoudong.setChecked(isrb_shoudong);
		rb_zidong.setChecked(isrb_zidong);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_setengines_btnswitch, this);
		name = (TextView) findViewById(R.id.name);
		fragment_settings_rg = (RadioGroup) findViewById(R.id.fragment_settings_rg);
		rb_shoudong =(RadioButton) findViewById(R.id.rb_shoudong);
		rb_zidong =(RadioButton) findViewById(R.id.rb_zidong);
	}

}

