package com.example.mareu;

import androidx.recyclerview.widget.RecyclerView;

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

    public MeetingListRecyclerViewAdapter(List<Meeting> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                FragmentMeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mMeeting = mValues.get(position);
        holder.mBinding.subjectMeeting.setText(String.format("%s - %s", holder.mMeeting.getTime(), holder.mMeeting.getSubject()));
        StringBuilder participantListStringBuilder = new StringBuilder();
        int size = holder.mMeeting.getParticipants().size();
        for (String participant: holder.mMeeting.getParticipants()) {
            participantListStringBuilder.append(participant);
            size--;
            if(size > 0)
             participantListStringBuilder.append(", ");
        }
        holder.mBinding.meetingRoom.setText(holder.mMeeting.getRoom());
        holder.mBinding.participantsMeeting.setText(participantListStringBuilder.toString());
        holder.mBinding.colorMeeting.setColorFilter(holder.mMeeting.getColor());
        holder.mBinding.removeMeeting.setOnClickListener(v -> {
            EventBus.getDefault().post(new RemoveMeetingEvent(holder.mMeeting));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentMeetingItemBinding mBinding;
        public Meeting mMeeting;

        public ViewHolder(FragmentMeetingItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}