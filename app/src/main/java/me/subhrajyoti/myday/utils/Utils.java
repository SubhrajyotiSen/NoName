package me.subhrajyoti.myday.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import me.subhrajyoti.myday.MyDate;
import me.subhrajyoti.myday.MyDayApp;
import me.subhrajyoti.myday.data.remote.ApiService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        assert deadline != null;
        long diff = deadline.getTime() - today.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static ApiService getApiService() {

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .cache(new Cache(MyDayApp.getContext().getCacheDir(), 5 * 1024 * 1024)) // 10 MB
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (isNetworkAvailable()) {
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                    }
                    return chain.proceed(request);
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService.class);
    }

    public static String capitalizeFirstCharacter(String text) {
        return text.substring(0,1).toUpperCase().concat(text.substring(1));
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyDayApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
