package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class RizhiFragment extends BaseFragmentV4 implements View.OnClickListener{
	ImageView imageView1,imageView2,imageView3,imageView4;
	@Override
	public int bindLayout() {
		return R.layout.fragment_rizhi;
	}

	@Override
	public void initView(View view) {
		imageView1 = ViewFindUtils.find(view, R.id.imageView1);
		imageView2= ViewFindUtils.find(view, R.id.imageView2);
		imageView3 = ViewFindUtils.find(view, R.id.imageView3);
		imageView4 = ViewFindUtils.find(view, R.id.imageView4);
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		imageView3.setOnClickListener(this);
		imageView4.setOnClickListener(this);
	}

	@Override
	public void doBusiness(Context mContext) {
	}

	@Override
	protected void lazyLoad() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView1:
			getOperation().forward(BreedinglogActivity.class);
			break;
		case R.id.imageView2:
		//	getOperation().forward(AquacultureActivity.class);
			Intent intent= new Intent(getContext(),AquacultureActivity.class);
            intent.putExtra("title","水产病害报表");
            startActivity(intent);
			break;
		case R.id.imageView3:
			Intent intent2= new Intent(getContext(),AquacultureActivity.class);
            intent2.putExtra("title","动态咨询");
            startActivity(intent2);
			break;
		case R.id.imageView4:
			Intent intent3= new Intent(getContext(),AquacultureActivity.class);
            intent3.putExtra("title","视频资料");
            startActivity(intent3);
			break;
		default:
			break;
		}
	}
}
