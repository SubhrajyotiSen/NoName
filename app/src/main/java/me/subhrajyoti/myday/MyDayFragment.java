package me.subhrajyoti.myday;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.subhrajyoti.myday.adapters.MultiTypeAdapter;
import me.subhrajyoti.myday.data.remote.ApiService;
import me.subhrajyoti.myday.utils.Utils;

public class MyDayFragment extends Fragment {

    private ApiService apiService;
    private MultiTypeAdapter multiTypeAdapter;
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;

    public MyDayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_my_day, container, false);
        ButterKnife.bind(this, rootView);

        apiService = Utils.getApiService();

        multiTypeAdapter = new MultiTypeAdapter(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(multiTypeAdapter);
        recyclerView.setHasFixedSize(true);

        fetchData();


        return rootView;
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
