package me.subhrajyoti.myday.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.subhrajyoti.myday.data.pojo.MyData;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/v2/5bab879a310000380065439b")
    Single<List<MyData>> loadData();


}
