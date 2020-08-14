package com.example.popularmovies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {

    private ArrayList<MovieModel> ArrayListOfMovies;
    public DetailsAdapterListener onClickListenerDetails;
    private Context context;

    public DisplayAdapter(ArrayList<MovieModel> arrayListOfMovies, DetailsAdapterListener onClickListener, Context context) {
        ArrayListOfMovies = arrayListOfMovies;
        this.onClickListenerDetails = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String urlExtension="https://image.tmdb.org/t/p/w342"+ArrayListOfMovies.get(position).getPoster_path();

        Picasso.get()
                .load(String.valueOf(urlExtension))
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.movie_img);

        holder.movie_name.setText(ArrayListOfMovies.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return ArrayListOfMovies.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private TextView movie_name;
        private ImageButton movie_img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_img=itemView.findViewById(R.id.movieItem_ImgView);
            movie_name=itemView.findViewById(R.id.movieItem_textView);

            movie_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                onClickListenerDetails.DetsOnClick(v,getAdapterPosition());
                }
            });

        }

    }
    public interface DetailsAdapterListener {
        void DetsOnClick(View v, int position);
    }
}
