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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.subhrajyoti.myday.R;
import me.subhrajyoti.myday.data.pojo.BirthdayModel;
import me.subhrajyoti.myday.data.pojo.ChannelModel;
import me.subhrajyoti.myday.data.pojo.ChannelUpdateModel;
import me.subhrajyoti.myday.data.pojo.DashboardModel;
import me.subhrajyoti.myday.data.pojo.EmployeeUpdateModel;
import me.subhrajyoti.myday.data.pojo.EventModel;
import me.subhrajyoti.myday.data.pojo.MyData;
import me.subhrajyoti.myday.data.pojo.NewMemberModel;
import me.subhrajyoti.myday.data.pojo.PollModel;
import me.subhrajyoti.myday.data.pojo.ProjectModel;
import me.subhrajyoti.myday.data.pojo.QuickViewModel;
import me.subhrajyoti.myday.data.pojo.TeamUpdateModel;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.BaseViewHolder>{

    private List<MyData> myDataList = new ArrayList<>();

    private BirthdayAdapter birthdayAdapter;
    private ChannelAdapter channelAdapter;
    private ChannelUpdateAdapter channelUpdateAdapter;
    private DashboardAdapter dashboardAdapter;
    private EmployeeUpdateAdapter employeeUpdateAdapter;
    private EventAdapter eventAdapter;
    private NewMemberAdapter newMemberAdapter;
    private PollAdapter pollAdapter;
    private ProjectsAdapter projectsAdapter;
    private QuickViewCardAdapter quickViewCardAdapter;
    private TeamUpdateAdapter teamUpdateAdapter;

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
        if (i == QUICKVIEW) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.quickview_layout, viewGroup, false);
            customViewHolder = new QuickViewHolder(v);
        }
        else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.generic_recyclerview_row, viewGroup, false);
            customViewHolder = new GenericViewHolder(v);
        }

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        if (position != 0)
            if (position % 2 == 0)
                viewHolder.recyclerview_row_card.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.lightGray, null));
            else
                viewHolder.recyclerview_row_card.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorWhite, null));

        switch (position) {
            case BIRTHDAY:
                viewHolder.update(birthdayAdapter);
                break;
            case CHANNEL:
                viewHolder.update(channelAdapter);
                break;
            case CHANNEL_UPDATE:
                viewHolder.update(channelUpdateAdapter);
                break;
            case DASHBOARD:
                viewHolder.update(dashboardAdapter);
                break;
            case EMPLOYEE_UPDATE:
                viewHolder.update(employeeUpdateAdapter);
                break;
            case EVENT:
                viewHolder.update(eventAdapter);
                break;
            case NEW_MEMBER:
                viewHolder.update(newMemberAdapter);
                break;
            case POLL:
                viewHolder.update(pollAdapter);
                break;
            case PROJECT:
                viewHolder.update(projectsAdapter);
                break;
            case QUICKVIEW:
                viewHolder.update(quickViewCardAdapter);
                break;
            case TEAM_UPDATE:
                viewHolder.update(teamUpdateAdapter);
                break;
            default: break;
        }
    }


    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public void addAll(List<MyData> myDataList) {
        this.myDataList = myDataList;
        Log.d("SIZE",myDataList.size()+"");
        for (MyData myData : myDataList) {
            switch (myData.getType()) {
                case "birthdays":
                    birthdayAdapter = new BirthdayAdapter(myData.makeArrayListFromJsonArray(BirthdayModel.class));
                    break;
                case "channels":
                    channelAdapter = new ChannelAdapter(myData.makeArrayListFromJsonArray(ChannelModel.class));
                    break;
                case "channel update":
                    channelUpdateAdapter = new ChannelUpdateAdapter(myData.makeArrayListFromJsonArray(ChannelUpdateModel.class));
                    break;
                case "quickview":
                    quickViewCardAdapter = new QuickViewCardAdapter(myData.makeArrayListFromJsonArray(QuickViewModel.class));
                    break;
                case "projects":
                    projectsAdapter = new ProjectsAdapter(myData.makeArrayListFromJsonArray(ProjectModel.class));
                    break;
                case "dashboard":
                    dashboardAdapter = new DashboardAdapter(myData.makeArrayListFromJsonArray(DashboardModel.class));
                    break;
                case "team updates":
                    teamUpdateAdapter = new TeamUpdateAdapter(myData.makeArrayListFromJsonArray(TeamUpdateModel.class));
                    break;
                case "polls":
                    pollAdapter = new PollAdapter(myData.makeArrayListFromJsonArray(PollModel.class));
                    break;
                case "events":
                    eventAdapter = new EventAdapter(myData.makeArrayListFromJsonArray(EventModel.class));
                    break;
                case "new members":
                    newMemberAdapter = new NewMemberAdapter(myData.makeArrayListFromJsonArray(NewMemberModel.class));
                    break;
                case "employee updates":
                    employeeUpdateAdapter = new EmployeeUpdateAdapter(myData.makeArrayListFromJsonArray(EmployeeUpdateModel.class));
                    break;
                default: break;
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

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyclerview_row_card)
        CardView recyclerview_row_card;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void update(RecyclerView.Adapter adapter);
    }

    class QuickViewHolder extends BaseViewHolder {

        @BindView(R.id.date_textview)
        TextView dateTextView;
        @BindView(R.id.day_textview)
        TextView dayTextView;
        @BindView(R.id.add_button)
        ImageView addButton;
        @BindView(R.id.quickview_card_recyclerview)
        RecyclerView quickviewCardRecyclerview;

        public QuickViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dateTextView.setText("24 September");
            dayTextView.setText("Today");
            quickviewCardRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        }

        @Override
        public void update(RecyclerView.Adapter adapter) {
            quickviewCardRecyclerview.setAdapter(adapter);
        }
    }

    class GenericViewHolder extends BaseViewHolder {

        @BindView(R.id.generic_recyclerview)
        RecyclerView genericRecyclerView;
        @BindView(R.id.header_textView)
        TextView headerTextView;

        public GenericViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            genericRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }

        @Override
        public void update(RecyclerView.Adapter adapter) {
            genericRecyclerView.setAdapter(adapter);
            Log.d("ADAPTER_TYPE", adapter.getClass().getName());
        }

    }

}
