package me.subhrajyoti.myday.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.subhrajyoti.myday.data.pojo.MyData;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/v2/5bb73d072e00004d0068398c")
    Single<List<MyData>> loadData();

}
