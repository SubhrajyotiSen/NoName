package me.subhrajyoti.myday.adapters;

import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.MyDayApp;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
import me.subhrajyoti.myday.utils.Utils;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private final List<ProjectModel> projectModelList;

    public ProjectsAdapter(List<ProjectModel> projectModels) {
        this.projectModelList = projectModels;
    }


    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        ProjectsViewHolder projectsViewHolder;
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.projects_layout, viewGroup, false);
        projectsViewHolder = new ProjectsAdapter.ProjectsViewHolder(v);

        return projectsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder projectsViewHolder, int position) {
        ProjectModel projectModel = projectModelList.get(position);

        int tasksCompleted = projectModel.getTasksCompleted();
        int tasksTotal = projectModel.getTasksTotal();
        projectsViewHolder.projectName.setText(projectModel.getProjectName());

        String progressText = tasksCompleted + "/" + tasksTotal;
        SpannableString spannableString = new SpannableString(progressText);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 0, progressText.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.2f), progressText.indexOf("/"), progressText.indexOf("/") + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        projectsViewHolder.projectProgress.setText(spannableString);
        projectsViewHolder.deadlineDays.setText(String.valueOf(Utils.daysLeftTillDeadline(projectModel.getDeadline())).concat(" days left"));
        projectsViewHolder.projectProgressBar.setProgress((int)((tasksCompleted * 1.0)/tasksTotal * 100));
        if (tasksTotal == tasksCompleted)
            projectsViewHolder.projectProgressBar.getProgressDrawable().setColorFilter(ResourcesCompat.getColor(MyDayApp.getContext().getResources(), R.color.colorGreenCyan, null), PorterDuff.Mode.SRC_IN);
        else
            projectsViewHolder.projectProgressBar.getProgressDrawable().setColorFilter(ResourcesCompat.getColor(MyDayApp.getContext().getResources(), R.color.colorRedOrange, null), PorterDuff.Mode.SRC_IN);


    }

    @Override
    public int getItemCount() {
        return projectModelList.size();
    }


    class ProjectsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.project_name_textView)
        TextView projectName;
        @BindView(R.id.project_progress)
        TextView projectProgress;
        @BindView(R.id.project_progressBar)
        ProgressBar projectProgressBar;
        @BindView(R.id.deadline_days)
        TextView deadlineDays;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void addAll(List<Object> projectModels) {
        if (this.projectModelList.isEmpty()) {
            for (Object object : projectModels)
                this.projectModelList.add((ProjectModel) object);
            notifyDataSetChanged();
        }
    }

}
