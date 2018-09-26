package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ChannelModel;
import me.subhrajyoti.myday.data.pojo.MyData;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    private List<ChannelModel> channelModels;

    public ChannelAdapter() {
        this.channelModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.channel_layout, viewGroup, false);
        return new ChannelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder channelViewHolder, int i) {
        channelViewHolder.channelNameTextView.setText(channelModels.get(i).getChannelName());
        channelViewHolder.memberCountTextView.setText(String.valueOf(channelModels.get(i).getNumberOfMembers()).concat(" Members"));

    }

    @Override
    public int getItemCount() {
        return channelModels.size();
    }

    public void addAll(List<ChannelModel> channelModels) {
        this.channelModels.addAll(channelModels);
        notifyDataSetChanged();
    }

    public void addAll(MyData myData) {
        Gson gson = new Gson();
        List<ChannelModel> channelModelList = new ArrayList<>();
        JsonArray jsonArray = myData.getData();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i).getAsJsonObject();
            channelModelList.add(gson.fromJson(jsonElement, ChannelModel.class));
        }
        addAll(channelModelList);
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.channelNameTextView)
        TextView channelNameTextView;
        @BindView(R.id.memberCountTextView)
        TextView memberCountTextView;
        @BindView(R.id.joinChannelButton)
        Button joinChannelButton;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
