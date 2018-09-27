package me.subhrajyoti.myday;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.subhrajyoti.myday.adapters.MultiTypeAdapter;
import me.subhrajyoti.myday.data.remote.ApiService;
import me.subhrajyoti.myday.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private MultiTypeAdapter multiTypeAdapter;
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiService = Utils.getApiService();

        multiTypeAdapter = new MultiTypeAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(multiTypeAdapter);
        recyclerView.setHasFixedSize(true);

        fetchData();

    }

    @SuppressLint("CheckResult")
    private void fetchData() {
        apiService.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myDataList ->
                    multiTypeAdapter.addAll(myDataList));
    }

}
