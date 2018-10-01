package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.MyDayApp;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.DashboardModel;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private List<DashboardModel> dashboardModels;

    public DashboardAdapter(List<DashboardModel> dashboardModels) {
        this.dashboardModels = dashboardModels;
    }


    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.dashboard_layout, viewGroup, false);
        return new DashboardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder viewHolder, int i) {
        DashboardModel dashboardModel = dashboardModels.get(i);

        viewHolder.amountTextView.setText(dashboardModel.getAmount());
        viewHolder.descriptionTextView.setText(dashboardModel.getDescription());
        viewHolder.whenTextView.setText(dashboardModel.getWhen());
        viewHolder.whenTextView.setText(dashboardModel.getWhen());

        String status = dashboardModel.getStatus();
        String changeText = String.valueOf(dashboardModel.getChange());
        if (status.equals("up")) {
            viewHolder.upDownImageView.setImageResource(R.drawable.ic_up_arrow);
            viewHolder.changeTextView.setTextColor(ResourcesCompat.getColor(MyDayApp.getContext().getResources(), R.color.colorLightGreen, null));
            changeText = "+".concat(changeText).concat("%");
        } else {
            viewHolder.upDownImageView.setImageResource(R.drawable.ic_down_arrow);
            viewHolder.changeTextView.setTextColor(ResourcesCompat.getColor(MyDayApp.getContext().getResources(), R.color.colorRed, null));
            changeText = "-".concat(changeText).concat("%");
        }

        viewHolder.changeTextView.setText(changeText);

    }

    @Override
    public int getItemCount() {
        return dashboardModels.size();
    }

    public void addAll(List<Object> dashboardModels) {
        if (this.dashboardModels.isEmpty()) {
            for(Object object: dashboardModels)
                this.dashboardModels.add((DashboardModel) object);
            notifyDataSetChanged();
        }
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.amount_textview)
        TextView amountTextView;
        @BindView(R.id.desc_textview)
        TextView descriptionTextView;
        @BindView(R.id.when_textview)
        TextView whenTextView;
        @BindView(R.id.updown_imageview)
        ImageView upDownImageView;
        @BindView(R.id.change_textview)
        TextView changeTextView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
