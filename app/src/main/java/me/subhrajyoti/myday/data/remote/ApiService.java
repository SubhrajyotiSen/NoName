package me.subhrajyoti.myday.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.subhrajyoti.myday.data.pojo.MyData;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/v2/5bb1f3302e00005d319272d6")
    Single<List<MyData>> loadData();

}
