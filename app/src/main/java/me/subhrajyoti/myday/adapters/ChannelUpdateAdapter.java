package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.ColorGenerator;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.ChannelUpdateModel;

public class ChannelUpdateAdapter extends RecyclerView.Adapter<ChannelUpdateAdapter.ChannelUpdateViewHolder> {

    private List<ChannelUpdateModel> channelUpdateModels;

    public ChannelUpdateAdapter() {
        this.channelUpdateModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChannelUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.channel_updates_layout, viewGroup, false);
        return new ChannelUpdateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelUpdateViewHolder channelUpdateViewHolder, int i) {

        ChannelUpdateModel channelUpdateModel = channelUpdateModels.get(i);

        channelUpdateViewHolder.channlNameTextView.setText(channelUpdateModel.getChannelName());
        channelUpdateViewHolder.channelUpdateTextView.setText(channelUpdateModel.getUpdate());
        channelUpdateViewHolder.channelLikeCountTextView.setText(String.valueOf(channelUpdateModel.getLikes()));
        channelUpdateViewHolder.channelCommentCountTextView.setText(String.valueOf(channelUpdateModel.getComments()));

        channelUpdateViewHolder.channeUpdatesCardView.setCardBackgroundColor(ColorGenerator.getColor(17));
    }

    @Override
    public int getItemCount() {
        return channelUpdateModels.size();
    }

    public void addAll(List<Object> channelUpdateModels) {
        if (this.channelUpdateModels.isEmpty()) {
            for(Object object: channelUpdateModels)
                this.channelUpdateModels.add((ChannelUpdateModel) object);
            notifyDataSetChanged();
        }
    }

    class ChannelUpdateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.channel_name_textView)
        TextView channlNameTextView;
        @BindView(R.id.channel_update_textView)
        TextView channelUpdateTextView;
        @BindView(R.id.channel_like_count_textView)
        TextView channelLikeCountTextView;
        @BindView(R.id.channel_comment_count_textView)
        TextView channelCommentCountTextView;
        @BindView(R.id.channel_updates_cardView)
        CardView channeUpdatesCardView;

        public ChannelUpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
