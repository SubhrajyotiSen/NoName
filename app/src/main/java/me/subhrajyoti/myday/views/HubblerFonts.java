package me.subhrajyoti.myday.views;

import android.content.Context;
import android.graphics.Typeface;

/*
 * Created by deepakjoshi on 13/04/18.
 */

public class HubblerFonts {

    private static HubblerFonts fonts;

    private Typeface proxima_light,
            proxima_thin,
            proxima_bold,
            proxima_regular,
            proxima_semibold;

    private HubblerFonts(Context context){

        initFonts(context);
    }

    public static HubblerFonts getSingletonInstance(Context ctx){

        synchronized (HubblerFonts.class) {
            if (fonts == null)
                fonts = new HubblerFonts(ctx);
        }

        return fonts;
    }


    public Typeface getLightFont() {
        return proxima_light;
    }

    public Typeface getThinFont() {
        return proxima_thin;
    }

    public Typeface getBoldFont() {
        return proxima_bold;
    }

    public Typeface getRegularFont() {
        return proxima_regular;
    }

    public Typeface getSemiboldFont() {
        return proxima_semibold;
    }

    private void initFonts(Context context){

        proxima_light = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Light.otf");
        proxima_thin = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Thin.otf");
        proxima_bold = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Bold.otf");
        proxima_regular = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Regular.otf");
        proxima_semibold = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Semibold.otf");
    }
}

