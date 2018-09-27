package me.subhrajyoti.myday;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class HubblerTextView extends AppCompatTextView {

    public HubblerTextView(Context context) {
        super(context);
        init(context);
    }

    public HubblerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        setTypeface(HubblerFonts.getSingletonInstance(context).getRegularFont() , Typeface.NORMAL);
    }

}
