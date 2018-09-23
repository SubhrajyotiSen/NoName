package me.subhrajyoti.myday;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.subhrajyoti.myday.adapters.BirthdayAdapter;
import me.subhrajyoti.myday.adapters.ProjectsAdapter;
import me.subhrajyoti.myday.data.remote.ApiService;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.birthdaysRecyclerView)
    RecyclerView birthdaysRecyclerView;
    @BindView(R.id.projectsRecyclerView)
    RecyclerView projectRecyclerView;


    private BirthdayAdapter birthdayAdapter;
    private ProjectsAdapter projectsAdapter;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiService = Utils.getApiService();

        birthdayAdapter = new BirthdayAdapter(position -> Toast.makeText(MainActivity.this, "Wishing Happy Birthday", Toast.LENGTH_SHORT).show());

        birthdaysRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        birthdaysRecyclerView.setAdapter(birthdayAdapter);

        projectsAdapter = new ProjectsAdapter(position -> Toast.makeText(MainActivity.this, "Project tapped", Toast.LENGTH_SHORT).show());

        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        projectRecyclerView.setAdapter(projectsAdapter);

        fetchBirthdays();
        fetchProjects();

    }

    @SuppressLint("CheckResult")
    private void fetchProjects() {
            apiService.loadProjects()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(projectModels -> projectsAdapter.addAll(projectModels));
    }

    @SuppressLint("CheckResult")
    private void fetchBirthdays() {
        apiService.loadBirthdays()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(birthdayModels -> birthdayAdapter.addAll(birthdayModels));
    }
}
