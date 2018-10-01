package me.subhrajyoti.myday.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.EmployeeUpdateModel;
import me.subhrajyoti.myday.data.pojo.NewMemberModel;
import me.subhrajyoti.myday.utils.Utils;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ChannelModel;
import me.subhrajyoti.myday.data.pojo.ChannelUpdateModel;
import me.subhrajyoti.myday.data.pojo.DashboardModel;
import me.subhrajyoti.myday.data.pojo.EventModel;
import me.subhrajyoti.myday.data.pojo.MyData;
import me.subhrajyoti.myday.data.pojo.PollModel;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
import me.subhrajyoti.myday.data.pojo.QuickViewModel;
import me.subhrajyoti.myday.data.pojo.TeamUpdateModel;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.BaseViewHolder>{

    private List<MyData> myDataList = new ArrayList<>();

    private final int PROJECT = 0, BIRTHDAY = 1, QUICKVIEW = 2, CHANNEL = 3, DASHBOARD = 4, TEAM_UPDATE = 5,
            POLL = 6, EVENT = 7, CHANNEL_UPDATE = 8, NEW_MEMBER = 9, EMPLOYEE_UPDATE = 10;
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
            default:
                customViewHolder = new QuickViewHolder(v);
        }

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        if (position != 0)
            if (position % 2 == 0)
                viewHolder.recyclerViewRowCard.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.lightGray, null));
            else
                viewHolder.recyclerViewRowCard.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorWhite, null));
        viewHolder.update(myDataList.get(position));
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public void addAll(List<MyData> myDataList) {
        this.myDataList = myDataList;
        for (MyData myData : myDataList) {
            switch (myData.getType()) {
                case "projects":
                    myData.makeArrayListFromJsonArray(ProjectModel.class);
                    break;
                case "birthdays":
                    myData.makeArrayListFromJsonArray(BirthdayModel.class);
                    break;
                case "channels":
                    myData.makeArrayListFromJsonArray(ChannelModel.class);
                    break;
                case "dashboard":
                    myData.makeArrayListFromJsonArray(DashboardModel.class);
                    break;
                case "team updates":
                    myData.makeArrayListFromJsonArray(TeamUpdateModel.class);
                    break;
                case "polls":
                    myData.makeArrayListFromJsonArray(PollModel.class);
                    break;
                case "events":
                    myData.makeArrayListFromJsonArray(EventModel.class);
                    break;
                case "channel update":
                    myData.makeArrayListFromJsonArray(ChannelUpdateModel.class);
                    break;
                case "new members":
                    myData.makeArrayListFromJsonArray(NewMemberModel.class);
                    break;
                case "employee updates":
                    myData.makeArrayListFromJsonArray(EmployeeUpdateModel.class);
                    break;
                default:
                    myData.makeArrayListFromJsonArray(QuickViewModel.class);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        switch (myDataList.get(position).getType()) {
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
        public void update(MyData myData) {
            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            projectsAdapter.addAll(myData.getDataList());
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyclerview_row_card)
        CardView recyclerViewRowCard;

        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void update(MyData myData);
    }

    class BirthdayViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView birthdayRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        BirthdayAdapter birthdayAdapter;

        BirthdayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            birthdayAdapter = new BirthdayAdapter();
            birthdayRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            birthdayRecyclerView.setAdapter(birthdayAdapter);
        }

        @Override
        public void update(MyData myData) {
            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            birthdayAdapter.addAll(myData.getDataList());
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
            headerTextView.setText(Utils.capitalizeFirstCharacter(myData.getType()));
            channelAdapter.addAll(myData.getDataList());
        }
    }

    class DashboardsAdapter extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView dashboardRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;
        DashboardAdapter dashboardAdapter;

        DashboardsAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dashboardAdapter = new DashboardAdapter();
            dashboardRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            dashboardRecyclerView.setAdapter(dashboardAdapter);
        }

        @Override
        public void update(MyData myData) {
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
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
        public void update(MyData myData) {
            headerTextView.setText(myData.getType());
            employeeUpdateAdapter.addAll(myData.getDataList());
        }
    }
}
