package me.subhrajyoti.myday.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by sreejithk on 10/11/16.
 */

public class SemiBoldTextView extends android.support.v7.widget.AppCompatTextView {

    View autoHideView;
    public SemiBoldTextView(Context context) {
        super(context);
        init(context);
    }
    public SemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(text.toString().trim().length() > 0) {
            setVisibility(View.VISIBLE);
            if(autoHideView!=null)
                autoHideView.setVisibility(VISIBLE);
        }
        else {
            setVisibility(View.GONE);
            if(autoHideView!=null)
                autoHideView.setVisibility(GONE);
        }
    }

    public void init(Context context) {


        setTypeface(HubblerFonts.getSingletonInstance(context).getSemiboldFont() ,Typeface.NORMAL);

    }

    public void setAutoHideView(View v)
    {
        autoHideView = v;
    }
}
