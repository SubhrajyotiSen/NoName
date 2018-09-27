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
import me.subhrajyoti.myday.utils.ColorGenerator;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.QuickViewModel;

public class QuickViewCardAdapter extends RecyclerView.Adapter<QuickViewCardAdapter.QuickViewHolder> {

    private List<QuickViewModel> quickViewModels;

    public QuickViewCardAdapter() {
        this.quickViewModels = new ArrayList<>();

    }
    @NonNull
    @Override
    public QuickViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        QuickViewHolder quickViewHolder;
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.quickview_card_layout, viewGroup, false);
        quickViewHolder = new QuickViewCardAdapter.QuickViewHolder(v);

        return quickViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuickViewHolder viewHolder, int i) {
        viewHolder.card_header.setText(quickViewModels.get(i).getCardHeader());
        viewHolder.card_count.setText(quickViewModels.get(i).getSubText());

        viewHolder.quickcard_cardView.setCardBackgroundColor(ColorGenerator.getColor(i));

    }

    @Override
    public int getItemCount() {
        return quickViewModels.size();
    }

    public void addAll(List<Object> quickViewModels) {
        if (this.quickViewModels.isEmpty()) {
            for (Object object : quickViewModels)
                this.quickViewModels.add((QuickViewModel) object);
            notifyDataSetChanged();
        }
    }

    public class QuickViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_header)
        TextView card_header;
        @BindView(R.id.card_count)
        TextView card_count;
        @BindView(R.id.quickview_card)
        CardView quickcard_cardView;


        public QuickViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
