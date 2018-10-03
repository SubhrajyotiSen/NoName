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
import me.subhrajyoti.myday.data.pojo.BirthdayModel;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.BirthdayViewHolder> {

    private final List<BirthdayModel> birthdayModelList;

    public BirthdayAdapter() {
        this.birthdayModelList = new ArrayList<>();

    }

    @NonNull
    @Override
    public BirthdayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        BirthdayAdapter.BirthdayViewHolder birthdayViewHolder;
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.birthday_layout, viewGroup, false);
        birthdayViewHolder = new BirthdayViewHolder(v);

        return birthdayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BirthdayViewHolder birthdayViewHolder, int position) {
        BirthdayModel birthdayModel = birthdayModelList.get(position);

        birthdayViewHolder.name.setText(birthdayModel.getName());
        birthdayViewHolder.role.setText(birthdayModel.getRole());

        Picasso.get().load(birthdayModel.getImageURL()).fit().centerCrop().into(birthdayViewHolder.birthdayPersonImage);
        // TODO: use something like Shimmer as a placeholder while loading image

    }

    @Override
    public int getItemCount() {
        return birthdayModelList.size();
    }

    class BirthdayViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.birthday_person_image)
        ImageView birthdayPersonImage;
        @BindView(R.id.birthday_person_name)
        TextView name;
        @BindView(R.id.birthday_person_role)
        TextView role;

        public BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void addAll(List<Object> birthdayModels) {
        if (this.birthdayModelList.isEmpty()) {
            for(Object object : birthdayModels)
                this.birthdayModelList.add((BirthdayModel) object);
            notifyDataSetChanged();
        }
    }

}
