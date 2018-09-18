package me.subhrajyoti.myday;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import me.subhrajyoti.myday.data.BirthdayModel;
import me.subhrajyoti.myday.data.ProjectModel;

public class Utils {

    public static String birthdayTimeToDisplay(String day) {
        /*TODO: write logic to check if birthday is today or upcoming
            for now return Upcoming
         */
        return "Upcoming";
    }

    public static ArrayList<BirthdayModel> parseBirthdays(String json) throws JSONException {

        ArrayList<BirthdayModel> birthdayModelArrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            birthdayModelArrayList.add(new BirthdayModel(jsonObject));
        }

        return birthdayModelArrayList;

    }

    public static ArrayList<ProjectModel> parseProjects(String json) throws JSONException {

        ArrayList<ProjectModel> projectModelArrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            projectModelArrayList.add(new ProjectModel(jsonObject));
        }

        return projectModelArrayList;

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

    public static int daysLeftTillDeadline(String deadline) {
        // TODO: calculate number of days till deadline. For now return 15
        return 15;
    }
}
