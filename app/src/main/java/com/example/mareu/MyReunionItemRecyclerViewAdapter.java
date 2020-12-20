package com.example.mareu;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.databinding.FragmentReunionItemBinding;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MyReunionItemRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionItemRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mValues;
    public FragmentReunionItemBinding binding;

    public MyReunionItemRecyclerViewAdapter(List<Meeting> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                FragmentReunionItemBinding.inflate(LayoutInflater.from(parent.getContext()))
        );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mMeeting = mValues.get(position);
        //holder.mBinding.subjectMeeting.setText(holder.mMeeting.getSubject());
        //holder.mBinding.colorMeeting.setColorFilter(holder.mMeeting.getColor());
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentReunionItemBinding mBinding;
        public Meeting mMeeting;

        public ViewHolder(FragmentReunionItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}