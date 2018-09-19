package me.subhrajyoti.myday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.adapters.BirthdayAdapter;
import me.subhrajyoti.myday.adapters.ClickListener;
import me.subhrajyoti.myday.adapters.ProjectsAdapter;
import me.subhrajyoti.myday.data.BirthdayModel;
import me.subhrajyoti.myday.data.ProjectModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.birthdaysRecyclerView)
    RecyclerView birthdaysRecyclerView;
    @BindView(R.id.projectsRecyclerView)
    RecyclerView projectRecyclerView;


    private BirthdayAdapter birthdayAdapter;
    private ProjectsAdapter projectsAdapter;
    private List<BirthdayModel> birthdayModelList;
    private List<ProjectModel> projectModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        try {
            birthdayModelList = Utils.parseBirthdays(Utils.loadJSONFromAsset("bdays.json", this));
            projectModelList = Utils.parseProjects(Utils.loadJSONFromAsset("projects.json", this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        birthdayAdapter = new BirthdayAdapter(birthdayModelList, new BirthdayAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Toast.makeText(MainActivity.this, "Wishing Happy Birthday", Toast.LENGTH_SHORT).show();
            }
        });

        birthdaysRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        birthdaysRecyclerView.setAdapter(birthdayAdapter);

        projectsAdapter = new ProjectsAdapter(projectModelList, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Toast.makeText(MainActivity.this, "Project tapped", Toast.LENGTH_SHORT).show();
            }
        });

        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        projectRecyclerView.setAdapter(projectsAdapter);

    }
}
