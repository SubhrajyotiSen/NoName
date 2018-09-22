package me.subhrajyoti.myday;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import me.subhrajyoti.myday.data.remote.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
