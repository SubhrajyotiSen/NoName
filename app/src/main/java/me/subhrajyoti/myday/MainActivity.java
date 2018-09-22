package me.subhrajyoti.myday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.subhrajyoti.myday.adapters.BirthdayAdapter;
import me.subhrajyoti.myday.adapters.ClickListener;
import me.subhrajyoti.myday.adapters.ProjectsAdapter;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
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

        birthdayAdapter = new BirthdayAdapter( new BirthdayAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Toast.makeText(MainActivity.this, "Wishing Happy Birthday", Toast.LENGTH_SHORT).show();
            }
        });

        birthdaysRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        birthdaysRecyclerView.setAdapter(birthdayAdapter);

        projectsAdapter = new ProjectsAdapter( new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Toast.makeText(MainActivity.this, "Project tapped", Toast.LENGTH_SHORT).show();
            }
        });

        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        projectRecyclerView.setAdapter(projectsAdapter);

        fetchBirthdays();
        fetchProjects();

    }

    private void fetchProjects() {
            apiService.loadProjects()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<ProjectModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<ProjectModel> projects) {
                            projectsAdapter.addAll(projects);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("Retrofit", e.getLocalizedMessage());
                        }
                    });
    }

    private void fetchBirthdays() {
        apiService.loadBirthdays()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BirthdayModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BirthdayModel> birthdays) {
                        birthdayAdapter.addAll(birthdays);
                        Log.d("Retrofit", String.valueOf(birthdays.size()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Retrofit", e.getLocalizedMessage());
                    }
                });
    }
}
