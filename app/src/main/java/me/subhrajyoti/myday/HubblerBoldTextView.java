package me.subhrajyoti.myday;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class HubblerBoldTextView extends android.support.v7.widget.AppCompatTextView {

    View autoHideView;
    public HubblerBoldTextView(Context context) {
        super(context);
        init(context);
    }
    public HubblerBoldTextView(Context context, AttributeSet attrs) {
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


        setTypeface(HubblerFonts.getSingletonInstance(context).getBoldFont() , Typeface.NORMAL);

    }

    public void setAutoHideView(View v)
    {
        autoHideView = v;
    }
}
