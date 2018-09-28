package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.EmployeeUpdateModel;

public class EmployeeUpdateAdapter extends RecyclerView.Adapter<EmployeeUpdateAdapter.EmployeeUpdateViewHolder> {

    private List<EmployeeUpdateModel> employeeUpdateModels;

    public EmployeeUpdateAdapter() {
        employeeUpdateModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public EmployeeUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.employee_update_layout, viewGroup, false);
        return new EmployeeUpdateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeUpdateViewHolder employeeUpdateViewHolder, int i) {

        EmployeeUpdateModel employeeUpdateModel = employeeUpdateModels.get(i);

        Picasso.get().load(employeeUpdateModel.getImageURL()).into(employeeUpdateViewHolder.employeeImageView);
        employeeUpdateViewHolder.employeeNameTextView.setText(employeeUpdateModel.getName());
        employeeUpdateViewHolder.employeeRoleTextView.setText(employeeUpdateModel.getRole());
        employeeUpdateViewHolder.employeeUpdateTimeTextView.setText(employeeUpdateModel.getTime());
        employeeUpdateViewHolder.employeeUpdateTextView.setText(employeeUpdateModel.getText());
        employeeUpdateViewHolder.employeeUpdateLikeCountTextView.setText(String.valueOf(employeeUpdateModel.getLikes()));
        employeeUpdateViewHolder.employeeUpdateCommentCountTextView.setText(String.valueOf(employeeUpdateModel.getComments()));

    }

    @Override
    public int getItemCount() {
        return employeeUpdateModels.size();
    }

    public void addAll(List<Object> employeeUpdateModels) {
        if (this.employeeUpdateModels.isEmpty()) {
            for(Object object: employeeUpdateModels)
                this.employeeUpdateModels.add((EmployeeUpdateModel) object);
            notifyDataSetChanged();
        }
    }

    class EmployeeUpdateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.employee_imageView)
        ImageView employeeImageView;
        @BindView(R.id.employee_name_textView)
        TextView employeeNameTextView;
        @BindView(R.id.employee_role_textView)
        TextView employeeRoleTextView;
        @BindView(R.id.employee_update_time_textView)
        TextView employeeUpdateTimeTextView;
        @BindView(R.id.employee_update_textView)
        TextView employeeUpdateTextView;
        @BindView(R.id.employee_update_like_count_textView)
        TextView employeeUpdateLikeCountTextView;
        @BindView(R.id.employee_update_comment_count_textView)
        TextView employeeUpdateCommentCountTextView;
        @BindView(R.id.employee_update_share_button)
        Button employeeUpdateShareButton;
        public EmployeeUpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
