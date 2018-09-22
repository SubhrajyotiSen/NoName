package me.subhrajyoti.myday.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.Utils;
import me.subhrajyoti.myday.data.pojo.ProjectModel;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private final List<ProjectModel> projectModelList;
    private final ClickListener clickListener;

    public ProjectsAdapter(ClickListener clickListener) {
        this.projectModelList = new ArrayList<>();
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ProjectsAdapter.ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        ProjectsViewHolder projectsViewHolder;
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.projects_layout, viewGroup, false);
        projectsViewHolder = new ProjectsAdapter.ProjectsViewHolder(v);

        return projectsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.ProjectsViewHolder projectsViewHolder, int position) {
        ProjectModel projectModel = projectModelList.get(position);

        int tasksCompleted = projectModel.getTasksCompleted();
        int tasksTotal = projectModel.getTasksTotal();
        projectsViewHolder.projectName.setText("Project: ".concat(projectModel.getProjectName()));
        projectsViewHolder.projectProgress.setText(tasksCompleted + "/" + tasksTotal);
        projectsViewHolder.deadlineDays.setText(String.valueOf(Utils.daysLeftTillDeadline(projectModel.getDeadline())).concat(" days left"));
        projectsViewHolder.projectProgressBar.setProgress((int)((tasksCompleted * 1.0)/tasksTotal * 100));
        if (tasksTotal == tasksCompleted)
            projectsViewHolder.projectProgressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        else
            projectsViewHolder.projectProgressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


    }

    @Override
    public int getItemCount() {
        return projectModelList.size();
    }


    class ProjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.projectName)
        TextView projectName;
        @BindView(R.id.projectProgress)
        TextView projectProgress;
        @BindView(R.id.projectProgressBar)
        ProgressBar projectProgressBar;
        @BindView(R.id.deadlineDays)
        TextView deadlineDays;
        private WeakReference<ClickListener> listenerRef;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            listenerRef = new WeakReference<>(clickListener);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    public void addAll(List<ProjectModel> projectModels) {
        this.projectModelList.addAll(projectModels);
        notifyDataSetChanged();
    }
}
