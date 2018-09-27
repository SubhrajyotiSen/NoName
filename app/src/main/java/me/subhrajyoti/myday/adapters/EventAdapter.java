package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.ColorGenerator;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.EventModel;
import me.subhrajyoti.myday.data.pojo.MyData;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<EventModel> eventModels;

    public EventAdapter() {
        this.eventModels = new ArrayList<>();
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

    public void addAll(List<EventModel> eventModels) {
        this.eventModels.addAll(eventModels);
        notifyDataSetChanged();
    }

    public void addAll(MyData myData) {
        Gson gson = new Gson();
        List<EventModel> eventModels = new ArrayList<>();
        JsonArray jsonArray = myData.getData();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i).getAsJsonObject();
            eventModels.add(gson.fromJson(jsonElement, EventModel.class));
        }
        addAll(eventModels);

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
