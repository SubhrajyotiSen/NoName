package me.subhrajyoti.myday.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
import retrofit2.http.GET;

public interface ApiService {


    @GET("/v2/5ba63c4a3200006500963d5a")
    Single<List<BirthdayModel>> loadBirthdays();

    @GET("/v2/5ba63c713200006300963d5b")
    Single<List<ProjectModel>> loadProjects();

}
