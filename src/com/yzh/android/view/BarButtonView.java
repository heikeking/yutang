package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xubin on 15/6/28.
 */
public class BarButtonView extends FrameLayout {
    private ImageView rightImage;
    private TextView rightText;
    private ImageView arrow;
    private EditTextWithDeleteButton editTextWithDeleteButton;
    private EditText edit;
    private ToggleButton tbutton;
    private boolean canclick = true;

    public BarButtonView(Context context) {
        super(context);
        init();
    }

    public BarButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarButtonView);
        canclick = ta.getBoolean(R.styleable.BarButtonView_canclick, true);
        setLeft(ta.getText(R.styleable.BarButtonView_leftText), ta.getDrawable(R.styleable.BarButtonView_leftImage));
        setRightDrawable(ta.getDrawable(R.styleable.BarButtonView_rightImage));
        setRightTempl(ta.getText(R.styleable.BarButtonView_rightText));
        ta.recycle();
    }

    public void hideClick() {
        arrow.setVisibility(GONE);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_baredit, this);
        rightImage = (ImageView) findViewById(R.id.hintimg);
        rightText = (TextView) findViewById(R.id.hint);
        arrow = (ImageView) findViewById(R.id.arrow);
        edit = (EditText) findViewById(R.id.edit);
        tbutton = (ToggleButton) findViewById(R.id.tbutton);
       // edit = editTextWithDeleteButton.editText;
    }

    public void setRightDrawable(Drawable src) {
        if (src == null) {
            rightImage.setVisibility(GONE);
        } else {
            rightImage.setVisibility(VISIBLE);
            rightImage.setImageDrawable(src);
        }
    }

    public void setRightImage(Bitmap bmp) {
        rightImage.setImageBitmap(bmp);
    }

    public ImageView getRightImage() {
        return rightImage;
    }

    public void setLeft(CharSequence text) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(text);
        textView.setVisibility(VISIBLE);
    }

    public void setLeft(CharSequence text, Drawable background) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(text);
        textView.setVisibility(VISIBLE);

        ImageView image = (ImageView) findViewById(R.id.image);
        if (background == null) {
            image.setVisibility(GONE);
        } else {
            image.setVisibility(VISIBLE);
            image.setBackgroundDrawable(background);
        }
    }

    public void setLeftStyle(SpannableStringBuilder builder, Drawable background) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(builder);
        textView.setVisibility(VISIBLE);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxEms(15);
        ImageView image = (ImageView) findViewById(R.id.image);
        if (background == null) {
            image.setVisibility(GONE);
        } else {
            image.setVisibility(VISIBLE);
            image.setBackgroundDrawable(background);
        }
    }
    
    public void setRightText(String text) {
        if (edit.getVisibility() == VISIBLE) {
            edit.setHint(text);
            edit.selectAll();
        } else {
            rightText.setText(text);
        }
    }

    public EditText getRightEdit() {
        return edit;
    }

    public void setRightTempl(CharSequence templ) {
        if (templ != null) {
            String txt = templ.toString();
            if (txt.startsWith("toggle:")) {
                tbutton.setVisibility(VISIBLE);
                edit.setVisibility(GONE);
                rightText.setVisibility(GONE);
                arrow.setVisibility(GONE);
            } else if (txt.startsWith("redit:") || txt.startsWith("edit:") || txt.startsWith("mobile:") || txt.startsWith("rmobile:")) {
                if (txt.startsWith("rmobile:")) {
                    edit.setInputType(InputType.TYPE_CLASS_PHONE);
                    edit.setHint(txt.substring(8));
                    edit.setGravity(Gravity.RIGHT);
                } else if (txt.startsWith("mobile:")) {
                    edit.setInputType(InputType.TYPE_CLASS_PHONE);
                    edit.setHint(txt.substring(7));
                } else if (txt.startsWith("edit:")) {
                    edit.setHint(txt.substring(5));
                } else if (txt.startsWith("redit:")) {
                    edit.setHint(txt.substring(6));
                    edit.setGravity(Gravity.RIGHT);
                }
                edit.setSingleLine();
                edit.setVisibility(VISIBLE);
                rightText.setVisibility(GONE);
                arrow.setVisibility(GONE);
                findViewById(R.id.space).setVisibility(GONE);
            } else {
                if (txt.startsWith("red:")) {
                    rightText.setText(txt.substring(4));
                    rightText.setTextColor(getResources().getColor(R.color.main_color));
                } else {
                    rightText.setText(txt);
                }
                rightText.setVisibility(VISIBLE);
            }
        } else {
            rightText.setVisibility(GONE);
        }
        if (!canclick) {
            arrow.setVisibility(GONE);
        }
    }

    public String getRightText() {
        if (edit.getVisibility() == VISIBLE) {
            return edit.getText().toString();
        } else {
            return rightText.getText().toString();
        }
    }

	public void addRightFilter(TextWatcher watcher) {
		if (edit != null)
			edit.addTextChangedListener(watcher);
	}

	public void goneArrow() {
		arrow.setVisibility(GONE);
	}
	public void goneText() {
		rightText.setVisibility(GONE);
	}
}
