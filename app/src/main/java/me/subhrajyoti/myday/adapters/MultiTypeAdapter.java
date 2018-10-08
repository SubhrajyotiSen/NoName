package me.subhrajyoti.myday.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ChannelModel;
import me.subhrajyoti.myday.data.pojo.ChannelUpdateModel;
import me.subhrajyoti.myday.data.pojo.ClaimModel;
import me.subhrajyoti.myday.data.pojo.DashboardModel;
import me.subhrajyoti.myday.data.pojo.EmployeeUpdateModel;
import me.subhrajyoti.myday.data.pojo.EventModel;
import me.subhrajyoti.myday.data.pojo.MyData;
import me.subhrajyoti.myday.data.pojo.NewMemberModel;
import me.subhrajyoti.myday.data.pojo.PollModel;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
import me.subhrajyoti.myday.data.pojo.QuickViewModel;
import me.subhrajyoti.myday.data.pojo.TaskModel;
import me.subhrajyoti.myday.data.pojo.TeamUpdateModel;
import me.subhrajyoti.myday.data.pojo.ThoughtModel;
import me.subhrajyoti.myday.utils.Utils;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.BaseViewHolder>{

    private List<MyData> dataArrayList = new ArrayList<>();

    private final int PROJECT = 0, BIRTHDAY = 1, QUICKVIEW = 2, CHANNEL = 3, DASHBOARD = 4, TEAM_UPDATE = 5,
            POLL = 6, EVENT = 7, CHANNEL_UPDATE = 8, NEW_MEMBER = 9, EMPLOYEE_UPDATE = 10, TASKS = 11, CLAIMS = 12,
            HEADER = 13, MORE = 14, THOUGHT = 15;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        BaseViewHolder customViewHolder;

        View v;
        if (i == QUICKVIEW)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.quickview_layout, viewGroup, false);
        else if (i == BIRTHDAY)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.birthday_layout, viewGroup, false);
        else if (i == TASKS)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.task_layout, viewGroup, false);
        else if (i == CLAIMS)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.claims_layout, viewGroup, false);
        else if (i == HEADER)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.header_layout, viewGroup, false);
        else if (i == MORE)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.display_more_layout, viewGroup, false);
        else if (i == THOUGHT)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.thought_layout, viewGroup, false);
        else
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.generic_recyclerview_row, viewGroup, false);

        switch (i) {
            case PROJECT:
                customViewHolder = new ProjectsViewHolder(v);
                break;
            case BIRTHDAY:
                customViewHolder = new BirthdayViewHolder(v);
                break;
            case CHANNEL:
                customViewHolder = new ChannelsAdapter(v);
                break;
            case DASHBOARD:
                customViewHolder = new DashboardsAdapter(v);
                break;
            case TEAM_UPDATE:
                customViewHolder = new TeamUpdatesViewHolder(v);
                break;
            case POLL:
                customViewHolder = new PollsViewHolder(v);
                break;
            case EVENT:
                customViewHolder = new EventsViewHolder(v);
                break;
            case CHANNEL_UPDATE:
                customViewHolder = new ChannelUpdatesViewHolder(v);
                break;
            case NEW_MEMBER:
                customViewHolder = new NewMembersViewHolder(v);
                break;
            case EMPLOYEE_UPDATE:
                customViewHolder = new EmployeeUpdatesViewHolder(v);
                break;
            case TASKS:
                customViewHolder = new TasksViewHolder(v);
                break;
            case CLAIMS:
                customViewHolder = new ClaimsViewHolder(v);
                break;
            case HEADER:
                customViewHolder = new HeaderViewHolder(v);
                break;
            case MORE:
                customViewHolder = new DisplayMoreViewHolder(v);
                break;
            case THOUGHT:
                customViewHolder = new ThoughtViewHolder(v);
                break;
            default:
                customViewHolder = new QuickViewHolder(v);
        }

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.update(position);
    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public void addAll(List<MyData> myDataList) {
        Gson gson = new Gson();
        int length, min;
        for (MyData myData : myDataList) {
            switch (myData.getType()) {
                case "projects":
                    myData.makeArrayListFromJsonArray(ProjectModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "birthdays":
                    dataArrayList.add(new MyData("header", myData.getType()));
                    length  = myData.getData().size();
                    min = (length > 5)? 5: length;

                    for (int i = 0; i < min; i++) {
                        JsonElement jsonElement = myData.getData().get(i);
                        dataArrayList.add(new MyData(myData.getType(), new ArrayList<>(Collections.singletonList(gson.fromJson(jsonElement, BirthdayModel.class)))));
                    }
                    if (myData.getData().size() > 5) {
                        List<Object> list = new ArrayList<>();
                        for (int i = 5; i < length; i++) {
                            JsonElement jsonElement = myData.getData().get(i);
                            list.add(gson.fromJson(jsonElement, ClaimModel.class));
                        }
                        dataArrayList.add((new MyData("more", String.valueOf(myData.getData().size() - 5),list)));
                    }
                    break;
                case "channels":
                    myData.makeArrayListFromJsonArray(ChannelModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "dashboard":
                    myData.makeArrayListFromJsonArray(DashboardModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "team updates":
                    myData.makeArrayListFromJsonArray(TeamUpdateModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "polls":
                    myData.makeArrayListFromJsonArray(PollModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "events":
                    myData.makeArrayListFromJsonArray(EventModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "channel update":
                    myData.makeArrayListFromJsonArray(ChannelUpdateModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "new members":
                    myData.makeArrayListFromJsonArray(NewMemberModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "employee updates":
                    myData.makeArrayListFromJsonArray(EmployeeUpdateModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
                    break;
                case "tasks":
                    dataArrayList.add(new MyData("header", myData.getType()));
                    length  = myData.getData().size();
                    min = (length > 5)? 5: length;

                    for (int i = 0; i < min; i++) {
                        JsonElement jsonElement = myData.getData().get(i);
                        dataArrayList.add(new MyData(myData.getType(), new ArrayList<>(Collections.singletonList(gson.fromJson(jsonElement, TaskModel.class)))));
                    }
                    if (myData.getData().size() > 5) {
                        List<Object> list = new ArrayList<>();
                        for (int i = 5; i < length; i++) {
                            JsonElement jsonElement = myData.getData().get(i);
                            list.add(gson.fromJson(jsonElement, ClaimModel.class));
                        }
                        dataArrayList.add((new MyData("more", String.valueOf(myData.getData().size() - 5),list)));
                    }
                    break;
                case "claims":
                    dataArrayList.add(new MyData("header", myData.getType()));
                    length  = myData.getData().size();
                    min = (length > 5)? 5: length;

                    for (int i = 0; i < min; i++) {
                        JsonElement jsonElement = myData.getData().get(i);
                        dataArrayList.add(new MyData(myData.getType(), new ArrayList<>(Collections.singletonList(gson.fromJson(jsonElement, ClaimModel.class)))));
                    }
                    if (myData.getData().size() > 5) {
                        List<Object> list = new ArrayList<>();
                        for (int i = 5; i < length; i++) {
                            JsonElement jsonElement = myData.getData().get(i);
                            list.add(gson.fromJson(jsonElement, ClaimModel.class));
                        }
                        dataArrayList.add((new MyData("more", String.valueOf(myData.getData().size() - 5),list)));
                    }
                    break;
                case "thought":
                    Object object = gson.fromJson(myData.getData().get(0), ThoughtModel.class);
                    Log.d("TAG", ((ThoughtModel) object).getThought());
                    dataArrayList.add(new MyData(myData.getType(), new ArrayList<>(Collections.singleton(object))));
                    break;
                default:
                    myData.makeArrayListFromJsonArray(QuickViewModel.class);
                    dataArrayList.add(new MyData(myData.getType(), myData.getDataList()));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataArrayList.get(position).getType()) {
            case "projects":
                return PROJECT;
            case "birthdays":
                return BIRTHDAY;
            case "channels":
                return CHANNEL;
            case "dashboard":
                return DASHBOARD;
            case "team updates":
                return TEAM_UPDATE;
            case "polls":
                return POLL;
            case "events":
                return EVENT;
            case "channel update":
                return CHANNEL_UPDATE;
            case "new members":
                return NEW_MEMBER;
            case "employee updates":
                return EMPLOYEE_UPDATE;
            case "tasks":
                return TASKS;
            case "claims":
                return CLAIMS;
            case "header":
                return HEADER;
            case "more":
                return MORE;
            case "thought":
                return THOUGHT;
            default:
                return QUICKVIEW;
        }
    }

    class ProjectsViewHolder extends BaseViewHolder {

        ProjectsAdapter projectsAdapter;
        @BindView(R.id.generic_recyclerview)
        RecyclerView projectRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;


        ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            projectsAdapter = new ProjectsAdapter();
            projectRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            projectRecyclerView.setAdapter(projectsAdapter);

        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            projectsAdapter.addAll(myData.getDataList());
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void update(int position);
    }

    class BirthdayViewHolder extends BaseViewHolder {

        @BindView(R.id.birthday_person_image)
        ImageView birthdayPersonImage;
        @BindView(R.id.birthday_person_name)
        TextView name;
        @BindView(R.id.birthday_person_role)
        TextView role;
        @BindView(R.id.wish_text_view)
        EditText wishEditText;

        BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            BirthdayModel birthdayModel = (BirthdayModel) myData.getDataList().get(0);

            name.setText(birthdayModel.getName());
            role.setText(birthdayModel.getRole());
            wishEditText.setHint("Happy Birthday ".concat(birthdayModel.getName().substring(0, birthdayModel.getName().indexOf(' '))).concat("!"));
            Picasso.get().load(birthdayModel.getImageURL()).fit().centerCrop().into(birthdayPersonImage);

            Log.d("TAG", "birthtday");
        }
    }

    class QuickViewHolder extends BaseViewHolder {

        @BindView(R.id.date_textview)
        TextView dateTextView;
        @BindView(R.id.day_textview)
        TextView dayTextView;
        @BindView(R.id.add_button)
        ImageView addButton;
        @BindView(R.id.quickview_card_recyclerview)
        RecyclerView quickViewCardRecyclerView;
        QuickViewCardAdapter quickViewCardAdapter;

        QuickViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dateTextView.setText("24 September");
            dayTextView.setText("Today");
            quickViewCardAdapter = new QuickViewCardAdapter();
            quickViewCardRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            quickViewCardRecyclerView.setAdapter(quickViewCardAdapter);

        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            quickViewCardAdapter.addAll(myData.getDataList());
        }
    }

    class ChannelsAdapter extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView channelRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        ChannelAdapter channelAdapter;

        ChannelsAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            channelAdapter = new ChannelAdapter();
            channelRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            channelRecyclerView.setAdapter(channelAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            channelAdapter.addAll(myData.getDataList());
        }
    }

    class DashboardsAdapter extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView dashboardRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        @BindView(R.id.generic_linear_layout)
        LinearLayout genericLinearLayout;
        DashboardAdapter dashboardAdapter;

        DashboardsAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dashboardAdapter = new DashboardAdapter();
            dashboardRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            dashboardRecyclerView.setAdapter(dashboardAdapter);
            //genericLinearLayout.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_shadow, null));
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            dashboardAdapter.addAll(myData.getDataList());
        }
    }

    class TeamUpdatesViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView teamUpdatesRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        TeamUpdateAdapter teamUpdatesAdapter;

        TeamUpdatesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            teamUpdatesAdapter = new TeamUpdateAdapter();
            teamUpdatesRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            teamUpdatesRecyclerView.setAdapter(teamUpdatesAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            teamUpdatesAdapter.addAll(myData.getDataList());
        }
    }

    class PollsViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView pollsRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        PollAdapter pollAdapter;

        PollsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            pollAdapter = new PollAdapter();
            pollsRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            pollsRecyclerView.setAdapter(pollAdapter);

        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(myData.getType());
            pollAdapter.addAll(myData.getDataList());
        }
    }

    class EventsViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView eventsRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        EventAdapter eventAdapter;

        EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            eventAdapter = new EventAdapter();
            eventsRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            eventsRecyclerView.setAdapter(eventAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(myData.getType());
            eventAdapter.addAll(myData.getDataList());

        }
    }

    class ChannelUpdatesViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView channelUpdatesRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        ChannelUpdateAdapter channelUpdateAdapter;

        ChannelUpdatesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            channelUpdateAdapter = new ChannelUpdateAdapter();
            channelUpdatesRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            channelUpdatesRecyclerView.setAdapter(channelUpdateAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(myData.getType());
            channelUpdateAdapter.addAll(myData.getDataList());
        }
    }

    class NewMembersViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView newMembersRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        NewMemberAdapter newMemberAdapter;

        NewMembersViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            newMemberAdapter = new NewMemberAdapter();
            newMembersRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            newMembersRecyclerView.setAdapter(newMemberAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(myData.getType());
            newMemberAdapter.addAll(myData.getDataList());
        }
    }

    class EmployeeUpdatesViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView employeeUpdateRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        EmployeeUpdateAdapter employeeUpdateAdapter;

        EmployeeUpdatesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            employeeUpdateAdapter = new EmployeeUpdateAdapter();
            employeeUpdateRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            employeeUpdateRecyclerView.setAdapter(employeeUpdateAdapter);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            headerTextView.setText(myData.getType());
            employeeUpdateAdapter.addAll(myData.getDataList());
        }
    }

    class TasksViewHolder extends BaseViewHolder {

        @BindView(R.id.priority_bar_1)
        View priorityBar1;
        @BindView(R.id.priority_bar_2)
        View priorityBar2;
        @BindView(R.id.task_title_textView)
        TextView taskTitleTextView;
        @BindView(R.id.task_when_textView)
        TextView taskWhenTextView;

        TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            TaskModel taskModel = (TaskModel) myData.getDataList().get(0);

            switch (taskModel.getPriority()) {
                case "high":
                    priorityBar1.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorRed, null));
                    priorityBar2.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorRed, null));
                    break;
                case "low":
                    priorityBar1.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorRed, null));
                    priorityBar2.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorMediumLightGray, null));
                    break;
                default:
                    priorityBar1.setVisibility(View.GONE);
                    priorityBar2.setVisibility(View.GONE);
            }

            taskTitleTextView.setText(taskModel.getTitle());
            taskWhenTextView.setText(taskModel.getWhen());

        }
    }

    class ClaimsViewHolder extends BaseViewHolder {

        @BindView(R.id.claims_person_imageView)
        ImageView claimsPersonImageView;
        @BindView(R.id.claims_name_textView)
        TextView claimsNameTextView;
        @BindView(R.id.claims_amount_textView)
        TextView claimsAmountTextView;
        @BindView(R.id.claims_bills_textView)
        TextView claimsBillsTextView;

        ClaimsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);

            ClaimModel claimModel = (ClaimModel) myData.getDataList().get(0);

            Picasso.get().load(claimModel.getImageURL()).fit().centerCrop().into(claimsPersonImageView);
            claimsNameTextView.setText(claimModel.getName());
            claimsAmountTextView.setText(claimModel.getAmount());
            claimsBillsTextView.setText(String.valueOf(claimModel.getBills()).concat(" bills"));
        }
    }

    class HeaderViewHolder extends BaseViewHolder {

        @BindView(R.id.header_textView)
        TextView headerTextView;

        HeaderViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);
            headerTextView.setText(myData.getHeader());
        }
    }

    class DisplayMoreViewHolder extends BaseViewHolder {

        @BindView(R.id.display_more_cardView)
        CardView displayMoreCardView;
        @BindView(R.id.mote_items_textView)
        TextView moreItemsTextView;
        @BindView(R.id.updown_imageView)
        ImageView upDownImageView;

        private boolean toggled = false;

        DisplayMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);
            if (!toggled) {
                moreItemsTextView.setText(myData.getHeader().concat(" More"));
                upDownImageView.setImageResource(R.drawable.ic_show_more);
            }
            else {
                moreItemsTextView.setText("Show less");
                upDownImageView.setImageResource(R.drawable.ic_show_less);
            }
            displayMoreCardView.setOnClickListener(v -> {
                if (!toggled) {
                    int pos = dataArrayList.indexOf(myData);
                    Log.d("POSITION", pos+"");
                    String type = dataArrayList.get(position - 1).getType();
                    for (Object object : myData.getDataList()) {
                        dataArrayList.add(pos++, new MyData(type, Collections.singletonList(object)));
                    }
                    notifyItemRangeInserted(position, myData.getDataList().size());
                    toggled = true;
                    moreItemsTextView.setText("Show less");
                    upDownImageView.setImageResource(R.drawable.ic_show_less);
                }
                else {
                    int pos = dataArrayList.indexOf(myData);
                    for (int i = 0; i < myData.getDataList().size(); i++) {
                        dataArrayList.remove(pos-1);
                        pos--;
                    }
                    toggled = false;
                    notifyItemRangeRemoved(pos+1, myData.getDataList().size());
                    moreItemsTextView.setText(myData.getHeader().concat(" More"));
                    upDownImageView.setImageResource(R.drawable.ic_show_more);
                }
            });
        }
    }

    class ThoughtViewHolder extends BaseViewHolder {

        @BindView(R.id.thought_textView)
        TextView thoughtTextView;
        @BindView(R.id.speaker_textView)
        TextView speakerTextView;

        public ThoughtViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void update(int position) {
            MyData myData = dataArrayList.get(position);
            ThoughtModel thoughtModel = (ThoughtModel) myData.getDataList().get(0);

            thoughtTextView.setText(thoughtModel.getThought());
            speakerTextView.setText(thoughtModel.getSpeaker());

        }


    }
}

