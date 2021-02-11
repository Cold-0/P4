package com.openclassroom.mareu.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.callback.IDeleteMeeting;
import com.openclassroom.mareu.databinding.FragmentMeetingItemBinding;
import com.openclassroom.mareu.model.Meeting;

import java.util.List;
import java.util.Random;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.ViewHolder> {
    private static final Random r = new Random();
    private List<Meeting> mValues;
    public FragmentMeetingItemBinding binding;
    private final Context mContext;
    private final IDeleteMeeting mDeleteMeeting;

    public MeetingListRecyclerViewAdapter(Context context, List<Meeting> items, IDeleteMeeting deleteMeeting) {
        mValues = items;
        mContext = context;
        mDeleteMeeting = deleteMeeting;
    }

    public void setList(List<Meeting> list) {
        if (list != mValues) {
            mValues = list;
            notifyDataSetChanged();
        }
    }

    public List<Meeting> getList() {
        return mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                FragmentMeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    public static int getRandomColor() {
        return Color.HSVToColor(new float[]{r.nextFloat() * 360, 0.25f, 0.75f}); // use HSV to get pastel color (sat 25%, value 75%, so only varying HUE
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mValues.get(position);
        String subject = mContext.getString(R.string.item_meeting_subject_format, meeting.getSubject(), meeting.getTime(), meeting.getRoom());
        holder.mBinding.subjectMeeting.setText(subject);
        holder.mBinding.participantsMeeting.setText(formatParticipantList(meeting.getParticipants()));
        holder.mBinding.colorMeeting.setColorFilter(getRandomColor());
        holder.mBinding.removeMeeting.setOnClickListener(v -> {
            mDeleteMeeting.deleteMeetingCallback(meeting);
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