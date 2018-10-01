package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.PollModel;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewVolder> {

    private List<PollModel> pollModels;

    public PollAdapter(List<PollModel> pollModels) {
        this.pollModels = pollModels;
    }

    @NonNull
    @Override
    public PollViewVolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.poll_layout, viewGroup, false);
        return new PollViewVolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PollViewVolder pollViewVolder, int i) {

        PollModel pollModel = pollModels.get(i);

        pollViewVolder.questionTextView.setText(pollModel.getQuestion());
        pollViewVolder.deadlineTextView.setText(pollModel.getDeadline());

    }

    @Override
    public int getItemCount() {
        return pollModels.size();
    }

    public void addAll(List<Object> pollModels) {
        if (this.pollModels.isEmpty()) {
            for (Object object : pollModels)
                this.pollModels.add((PollModel) object);
            notifyDataSetChanged();
        }
    }

    class PollViewVolder extends RecyclerView.ViewHolder {

        @BindView(R.id.deadline_textView)
        TextView deadlineTextView;
        @BindView(R.id.question_textView)
        TextView questionTextView;
        @BindView(R.id.answer_button)
        TextView answerButton;

        public PollViewVolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
