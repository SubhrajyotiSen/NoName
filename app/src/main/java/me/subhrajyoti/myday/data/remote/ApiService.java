package me.subhrajyoti.myday.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.subhrajyoti.myday.data.pojo.MyData;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/v2/5bac90c93100005e006547c7")
    Single<List<MyData>> loadData();


}
