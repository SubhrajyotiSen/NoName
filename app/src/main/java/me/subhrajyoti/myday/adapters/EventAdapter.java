package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.EventModel;
import me.subhrajyoti.myday.utils.ColorGenerator;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<EventModel> eventModels;

    public EventAdapter(List<EventModel> eventModels) {
        this.eventModels = eventModels;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.event_layout, viewGroup, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        EventModel eventModel = eventModels.get(i);

        eventViewHolder.dateTextView.setText(eventModel.getDate());
        eventViewHolder.eventNameTextView.setText(eventModel.getName());
        eventViewHolder.venueTextView.setText(eventModel.getVenue());

        eventViewHolder.eventCardView.setCardBackgroundColor(ColorGenerator.getColor(4));
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public void addAll(List<Object> eventModels) {
        if (this.eventModels.isEmpty()) {
            for(Object object: eventModels)
                this.eventModels.add((EventModel) object);
            notifyDataSetChanged();
        }
    }

    class EventViewHolder  extends RecyclerView.ViewHolder {

        @BindView(R.id.date_textView)
        TextView dateTextView;
        @BindView(R.id.event_name_textView)
        TextView eventNameTextView;
        @BindView(R.id.venue_textView)
        TextView venueTextView;
        @BindView(R.id.event_cardView)
        CardView eventCardView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
