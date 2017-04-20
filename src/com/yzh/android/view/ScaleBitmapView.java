package com.yzh.android.view;

import com.itteam.yutang.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable; 

public class ScaleBitmapView extends ImageView {

	private Bitmap mBitmap = null;  
	private Matrix mMatrix = new Matrix(); 
    private int BitmapWidth = 0;  
    private int BitmapHeight = 0; 
    float Scale = 1.0f;
	@SuppressLint("NewApi")
	public ScaleBitmapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public ScaleBitmapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public ScaleBitmapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SmartImageView);
		//setRightDrawable(ta.getDrawable(R.styleable.SmartImageView_imgname));
		Scale =ta.getFloat(R.styleable.SmartImageView_scalesize, Scale);
		ratio =ta.getFloat(R.styleable.SmartImageView_ratio, ratio);
		setRatio(ratio);
		mBitmap = ((BitmapDrawable)ta.getDrawable(R.styleable.SmartImageView_imgname)).getBitmap(); 
		if (mBitmap!=null) {
			 BitmapWidth = mBitmap.getWidth();  
		     BitmapHeight = mBitmap.getHeight();  
		     onScale();
		}
	}

	public ScaleBitmapView(Context context) {
		super(context);
	}

	// 然后在SmartImageView中，添加一个float类型的成员变量ratio作为图片的比例值，并且给它暴露一个setter方法，以便于设置图片比例。

	/** 图片宽和高的比例 */
	private float ratio = 1f;

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	public void onScale() {  
        mMatrix.reset();  
        mMatrix.postScale(Scale, Scale);  
        Log.i("Cache", "mBitmap2:"+mBitmap);
        Bitmap mBitmap2 = Bitmap.createBitmap(mBitmap, 0, 0, BitmapWidth,  
                BitmapHeight, mMatrix, true);  
        this.setImage(mBitmap2);  
        mBitmap2 = null;  
    }  
	
	public void setDrawable(Drawable drawable){
		if (drawable!=null) {
			mBitmap = ((BitmapDrawable)drawable).getBitmap(); 
			BitmapWidth = mBitmap.getWidth();  
		    BitmapHeight = mBitmap.getHeight(); 
		    onScale();
		}
		
	}
	 public void setImage(Bitmap bitmap) {  
		 this.setImageBitmap(bitmap);    
	 }
	// 然后我们来重写onMeausre方法，如下：

	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// 父容器传过来的宽度方向上的模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		// 父容器传过来的高度方向上的模式
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// 父容器传过来的宽度的值
		int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
		// 父容器传过来的高度的值
		int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingLeft() - getPaddingRight();

		if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
			// 判断条件为，宽度模式为Exactly，也就是填充父窗体或者是指定宽度；
			// 且高度模式不是Exaclty，代表设置的既不是fill_parent也不是具体的值，于是需要具体测量
			// 且图片的宽高比已经赋值完毕，不再是0.0f
			// 表示宽度确定，要测量高度
			height = (int) (width / ratio + 0.5f);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		} else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
			// 判断条件跟上面的相反，宽度方向和高度方向的条件互换
			// 表示高度确定，要测量宽度
			width = (int) (height * ratio + 0.5f);

			widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}*/
}

