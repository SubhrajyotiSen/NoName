package me.subhrajyoti.myday.adapters;

import android.support.annotation.NonNull;
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
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.MyData;
import me.subhrajyoti.myday.data.pojo.PollModel;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewVolder> {

    private List<PollModel> pollModels;

    public PollAdapter() {
        this.pollModels = new ArrayList<>();
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

    public void addAll(List<PollModel> pollModels) {
        this.pollModels.addAll(pollModels);
        notifyDataSetChanged();
    }

    public void addAll(MyData myData) {
        Gson gson = new Gson();
        List<PollModel> pollModels = new ArrayList<>();
        JsonArray jsonArray = myData.getData();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i).getAsJsonObject();
            pollModels.add(gson.fromJson(jsonElement, PollModel.class));
        }
        addAll(pollModels);

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
