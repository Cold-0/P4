package com.example.mareu;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mareu.databinding.FragmentMeetingItemBinding;
import com.example.mareu.model.Meeting;

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
        holder.mBinding.subjectMeeting.setText(holder.mMeeting.getSubject());
        holder.mBinding.colorMeeting.setColorFilter(holder.mMeeting.getColor());
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