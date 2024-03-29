package com.fiqartamin.moviecatalogue3.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fiqartamin.moviecatalogue3.Model.Movie;
import com.fiqartamin.moviecatalogue3.R;

import java.util.ArrayList;
import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MyViewHolder> {
    private List<Movie> mData;

    public MovieViewAdapter(ArrayList<Movie> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MovieViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup,false);

        final MyViewHolder vHolder = new MyViewHolder(view);

        vHolder.clMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vHolder.itemView.getContext(), mData.get(vHolder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewAdapter.MyViewHolder holder, int i) {
        holder.tvTitle.setText(mData.get(i).getTitle());
        holder.tvDesc.setText(mData.get(i).getOverview());
        holder.tvRelease.setText(mData.get(i).getRelease());

        String photo1 = "https://image.tmdb.org/t/p/w500" + mData.get(i).getPhoto();
        Glide.with(holder.itemView.getContext())
                .load(photo1)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clMovie;
        private TextView tvTitle, tvDesc, tvRelease;
        private ImageView imgPhoto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clMovie = itemView.findViewById(R.id.movie_const);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvDesc = itemView.findViewById(R.id.movie_desc);
            tvRelease = itemView.findViewById(R.id.movie_release);
            imgPhoto = itemView.findViewById(R.id.movie_img);
        }
    }
}
