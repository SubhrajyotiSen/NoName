package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.TeamUpdateModel;

public class TeamUpdateAdapter extends RecyclerView.Adapter<TeamUpdateAdapter.TeamUpdateViewHolder> {

    private List<TeamUpdateModel> teamUpdateModels;

    public TeamUpdateAdapter(List<TeamUpdateModel> teamUpdateModels) {
        this.teamUpdateModels = teamUpdateModels;
    }

    @NonNull
    @Override
    public TeamUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.team_update_layout, viewGroup, false);
        return  new TeamUpdateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamUpdateViewHolder teamUpdateViewHolder, int i) {
        TeamUpdateModel teamUpdateModel = teamUpdateModels.get(i);

        teamUpdateViewHolder.updateTextView.setText(teamUpdateModel.getUpdate());
        teamUpdateViewHolder.topicTextView.setText(teamUpdateModel.getTopic());
        teamUpdateViewHolder.whenTextView.setText(teamUpdateModel.getWhen());

    }

    @Override
    public int getItemCount() {
        return teamUpdateModels.size();
    }

    public void addAll(List<Object> teamUpdateModels) {
        if (this.teamUpdateModels.isEmpty()) {
            for (Object object : teamUpdateModels)
                this.teamUpdateModels.add((TeamUpdateModel) object);
            notifyDataSetChanged();
        }
    }


    class TeamUpdateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.update_textview)
        TextView updateTextView;
        @BindView(R.id.topic_textview)
        TextView topicTextView;
        @BindView(R.id.when_textview)
        TextView whenTextView;

        public TeamUpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
