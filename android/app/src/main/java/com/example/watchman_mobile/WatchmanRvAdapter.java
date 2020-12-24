package com.example.watchman_mobile;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WatchmanRvAdapter extends RecyclerView.Adapter<WatchmanRvAdapter.WatchmanViewHolder>{

    @NonNull
    @Override
    public WatchmanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchmanViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WatchmanViewHolder extends RecyclerView.ViewHolder{

        public WatchmanViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
