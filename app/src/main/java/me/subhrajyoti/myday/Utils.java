package me.subhrajyoti.myday;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import me.subhrajyoti.myday.data.BirthdayModel;
import me.subhrajyoti.myday.data.ProjectModel;

public class Utils {

    public static String birthdayTimeToDisplay(String day) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        MyDate today = MyDate.Companion.makeMyDate(dateFormat.format(new Date()));
        MyDate birthday = MyDate.Companion.makeMyDate(day);
        if (MyDate.Companion.compare(today, birthday))
            return "Upcoming";
        else
            return "Today";
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

    public static int daysLeftTillDeadline(String day) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date deadline = null;
        try {
            deadline = dateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        long diff = deadline.getTime() - today.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
