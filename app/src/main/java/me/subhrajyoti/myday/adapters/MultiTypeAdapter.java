package me.subhrajyoti.myday.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.Utils;
import me.subhrajyoti.myday.data.pojo.MyData;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.BaseViewHolder>{

    private List<MyData> myDataList = new ArrayList<>();
    private final int PROJECT = 0, BIRTHDAY = 1, QUICKVIEW = 2;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        BaseViewHolder customViewHolder;

        View v;
        if (i == QUICKVIEW)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.quickview_layout, viewGroup, false);
        else
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.generic_recyclerview_row, viewGroup, false);

        switch (i) {
            case PROJECT:
                customViewHolder = new ProjectsViewHolder(v);
                break;
            case BIRTHDAY:
                customViewHolder = new BirthdayViewHolder(v);
                break;
            default:
                customViewHolder = new QuickViewHolder(v);
        }

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
        viewHolder.update(myDataList.get(i));
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public void addAll(List<MyData> myDataList) {
        this.myDataList = myDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        switch (myDataList.get(position).getType()) {
            case "projects":
                return PROJECT;
            case "birthdays":
                return BIRTHDAY;
            default:
                return QUICKVIEW;
        }
    }

    class ProjectsViewHolder extends BaseViewHolder {

        ProjectsAdapter projectsAdapter;
        @BindView(R.id.generic_recyclerview)
        RecyclerView projectRecyclerview;
        @BindView(R.id.header_textView)
        TextView headerTextView;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            projectsAdapter = new ProjectsAdapter();
            projectRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            projectRecyclerview.setAdapter(projectsAdapter);

        }

        @Override
        public void update(MyData myData) {
            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            projectsAdapter.addAll(myData);
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {


        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void update(MyData myData);
    }

    class BirthdayViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView birthdayRecyclerview;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        BirthdayAdapter birthdayAdapter;

        public BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            birthdayAdapter = new BirthdayAdapter();
            birthdayRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            birthdayRecyclerview.setAdapter(birthdayAdapter);
        }

        @Override
        public void update(MyData myData) {
            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            birthdayAdapter.addAll(myData);
        }
    }

    class QuickViewHolder extends BaseViewHolder {

        @BindView(R.id.date_textview)
        TextView dateTextView;
        @BindView(R.id.day_textview)
        TextView dayTextView;
        @BindView(R.id.add_button)
        ImageView addButton;
        @BindView(R.id.quickview_card_recyclerview)
        RecyclerView quickviewCardRecyclerview;
        QuickViewCardAdapter quickViewCardAdapter;

        public QuickViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dateTextView.setText("24 September");
            dayTextView.setText("Today");
            quickViewCardAdapter = new QuickViewCardAdapter();
            quickviewCardRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            quickviewCardRecyclerview.setAdapter(quickViewCardAdapter);

        }

        @Override
        public void update(MyData myData) {
            quickViewCardAdapter.addAll(myData);
        }
    }

}
