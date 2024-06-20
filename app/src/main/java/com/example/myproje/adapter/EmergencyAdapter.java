package com.example.myproje.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproje.databinding.EmergencyRowBinding;
import com.example.myproje.model.Emergency;

import java.util.ArrayList;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyHolder> {

    private ArrayList<Emergency> emergencyArrayList;

    public EmergencyAdapter(ArrayList<Emergency> emergencyArrayList) {
        this.emergencyArrayList = emergencyArrayList;
    }


    @NonNull
    @Override
    public EmergencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmergencyRowBinding emergencyRowBinding=EmergencyRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new EmergencyHolder(emergencyRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyHolder holder, int position) {
        holder.emergencyRowBinding.emergencyName.setText(emergencyArrayList.get(position).name);
        holder.emergencyRowBinding.emergencyNumber.setText(emergencyArrayList.get(position).number);

    }

    @Override
    public int getItemCount() {
        return emergencyArrayList.size();
    }

    class EmergencyHolder extends RecyclerView.ViewHolder {
        EmergencyRowBinding emergencyRowBinding;


        public EmergencyHolder(EmergencyRowBinding emergencyRowBinding) {
            super(emergencyRowBinding.getRoot());
            this.emergencyRowBinding=emergencyRowBinding;
        }
    }
}
