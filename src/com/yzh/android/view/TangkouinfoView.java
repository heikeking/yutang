package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TangkouinfoView  extends FrameLayout {
	public TextView tangkouinfo_txt1;
	public TextView tangkouinfo_txt2;

	public TangkouinfoView(Context context) {
		super(context);
		init();
	}

	public TangkouinfoView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	public void setTangkouinfo_txt1(CharSequence text) {
		tangkouinfo_txt1.setText(text);
	}
	public void setTangkouinfo_txt2(CharSequence text) {
		tangkouinfo_txt2.setText(text);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_tangkouinfo, this);
		tangkouinfo_txt1 = (TextView) findViewById(R.id.tangkouinfo_txt1);
		tangkouinfo_txt2 = (TextView) findViewById(R.id.tangkouinfo_txt2);
	}
}
