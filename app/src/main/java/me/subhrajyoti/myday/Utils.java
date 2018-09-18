package me.subhrajyoti.myday;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

import me.subhrajyoti.myday.data.BirthdayModel;

public class Utils {

    public static String birthdayTimeToDisplay(String day) throws ParseException {
        /*TODO: write logic to check if birthday is today or upcoming
            for now return Upcoming
         */
        return "Upcoming";
    }

    public static ArrayList<BirthdayModel> parse(String json) throws JSONException {

        ArrayList<BirthdayModel> birthdayModelArrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            birthdayModelArrayList.add(new BirthdayModel(jsonObject));
        }

        return birthdayModelArrayList;

    }


    public static String loadJSONFromAsset(String JSONFileName, Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open(JSONFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
