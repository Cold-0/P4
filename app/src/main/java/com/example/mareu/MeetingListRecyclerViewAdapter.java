package com.example.mareu;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mareu.databinding.FragmentMeetingItemBinding;
import com.example.mareu.events.RemoveMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mValues;
    public FragmentMeetingItemBinding binding;
    private Context mContext;

    public MeetingListRecyclerViewAdapter(Context context, List<Meeting> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                FragmentMeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mValues.get(position);
        String subject = mContext.getString(R.string.item_meeting_subject_format, meeting.getSubject(), meeting.getTime(), meeting.getRoom());
        holder.mBinding.subjectMeeting.setText(subject);
        holder.mBinding.participantsMeeting.setText(formatParticipantList(meeting.getParticipants()));
        holder.mBinding.colorMeeting.setColorFilter(meeting.getColor());
        holder.mBinding.removeMeeting.setOnClickListener(v -> {
            EventBus.getDefault().post(new RemoveMeetingEvent(meeting));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private String formatParticipantList(List<String> participants) {
        StringBuilder participantListStringBuilder = new StringBuilder();
        int size = participants.size();
        for (String participant : participants) {
            participantListStringBuilder.append(participant);
            size--;
            if (size > 0)
                participantListStringBuilder.append(", ");
        }
        return participantListStringBuilder.toString();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentMeetingItemBinding mBinding;

        public ViewHolder(FragmentMeetingItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}