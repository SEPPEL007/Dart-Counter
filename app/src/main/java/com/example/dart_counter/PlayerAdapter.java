package com.example.dart_counter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private ArrayList<Player> playersNames;

    public PlayerAdapter(ArrayList<Player> players){
        playersNames = players;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;

        public MyViewHolder (View v) {
            super(v);
            playerName = v.findViewById(R.id.playersName);
        }
    }

    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.playerName.setText(playersNames.get(position).getName());
    }

    public int getItemCount(){
        return playersNames.size();
    }
}
