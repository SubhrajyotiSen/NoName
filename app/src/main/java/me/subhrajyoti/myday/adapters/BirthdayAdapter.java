package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.Utils;
import me.subhrajyoti.myday.data.BirthdayModel;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.BirthdayViewHolder> {

    private final List<BirthdayModel> birthdayModelList;
    private final ClickListener clickListener;

    public BirthdayAdapter(List<BirthdayModel> birthdayModelList, ClickListener clickListener) {
        this.birthdayModelList = birthdayModelList;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public BirthdayAdapter.BirthdayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
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
        birthdayViewHolder.birthdayWhen.setText(Utils.birthdayTimeToDisplay(birthdayModel.getBirthday()));

        Picasso.get().load(birthdayModelList.get(position).getImageURL()).into(birthdayViewHolder.birthdayPersonImage);
        // TODO: use something like Shimmer as a placeholder while loading image

    }

    @Override
    public int getItemCount() {
        return birthdayModelList.size();
    }

    class BirthdayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.birthday_person_image)
        ImageView birthdayPersonImage;
        @BindView(R.id.birthday_person_name)
        TextView name;
        @BindView(R.id.birthday_person_role)
        TextView role;
        @BindView(R.id.birthday_when)
        TextView birthdayWhen;
        @BindView(R.id.wish_birthday_button)
        TextView birthdayWishButton;
        private WeakReference<ClickListener> listenerRef;

        public BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            listenerRef = new WeakReference<>(clickListener);
            birthdayWishButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    public interface ClickListener {
        void onPositionClicked(int position);
    }

}
