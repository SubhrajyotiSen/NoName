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
import me.subhrajyoti.myday.data.BirthdayModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.birthdaysRecyclerView)
    RecyclerView birthdaysRecyclerView;

    private BirthdayAdapter birthdayAdapter;
    private List<BirthdayModel> birthdayModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        try {
            birthdayModelList = Utils.parse(Utils.loadJSONFromAsset("bdays.json", this));
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



    }
}
