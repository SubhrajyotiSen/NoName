package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.ChannelModel;

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

    public void addAll(List<Object> channelModels) {
        if (this.channelModels.isEmpty()) {
            for (Object object: channelModels)
                this.channelModels.add((ChannelModel) object);
            notifyDataSetChanged();
        }
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
