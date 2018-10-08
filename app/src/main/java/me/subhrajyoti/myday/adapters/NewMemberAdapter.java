package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.NewMemberModel;

public class NewMemberAdapter extends RecyclerView.Adapter<NewMemberAdapter.NewMemberViewHolder> {

    public List<NewMemberModel> newMemberModels;


    public NewMemberAdapter() {
        this.newMemberModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewMemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.new_member_layout, viewGroup, false);
        return new NewMemberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMemberViewHolder newMemberViewHolder, int i) {
        NewMemberModel newMemberModel = newMemberModels.get(i);

       Picasso.get().load(newMemberModel.getImageURL()).fit().centerCrop().into(newMemberViewHolder.newMemberImageView);
        newMemberViewHolder.newMemberNameTextView.setText(newMemberModel.getName());
        newMemberViewHolder.newMemberRoleTextView.setText(newMemberModel.getRole());

    }

    @Override
    public int getItemCount() {
        return newMemberModels.size();
    }

    public void addAll(List<Object> newMemberModels) {
        if (this.newMemberModels.isEmpty()) {
         //   for (Object object: newMemberModels)
                this.newMemberModels.add((NewMemberModel) newMemberModels.get(0));
            notifyDataSetChanged();
        }
    }

    class NewMemberViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.new_member_imageView)
        ImageView newMemberImageView;
        @BindView(R.id.new_member_name_textView)
        TextView newMemberNameTextView;
        @BindView(R.id.new_member_role_textView)
        TextView newMemberRoleTextView;

        public NewMemberViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
