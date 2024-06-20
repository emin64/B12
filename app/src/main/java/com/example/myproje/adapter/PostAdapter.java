package com.example.myproje.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproje.databinding.RecyclerRowBinding;
import com.example.myproje.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private ArrayList<Post> postArrayList;

    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList=postArrayList;
    }

    class PostHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;
        public PostHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;
        }
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recyclerRowBinding.textViewFamily.setText(postArrayList.get(position).name);
        holder.recyclerRowBinding.textViewComment.setText(postArrayList.get(position).comment);
        holder.recyclerRowBinding.textViewFamily.setText(postArrayList.get(position).name);
        Picasso.get().load(postArrayList.get(position).downloadUrl).into(holder.recyclerRowBinding.imageViewFamily);

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }
}
