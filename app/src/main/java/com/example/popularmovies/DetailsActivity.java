package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_overView;
    private TextView tv_rating;
    private TextView tv_popularity;
    private TextView tv_date;
    private ImageView backdropImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        backdropImg=findViewById(R.id.img_backdrop);
        tv_title=findViewById(R.id.tv_title);
        tv_overView=findViewById(R.id.tv_overview);
        tv_rating=findViewById(R.id.tv_rating);
        tv_popularity=findViewById(R.id.tv_popularity);
        tv_date=findViewById(R.id.tv_releaseDate);
        Intent intent=getIntent();
        Bundle args=intent.getBundleExtra("Bundle");
        assert args != null;
        int position=args.getInt("position");
        ArrayList<MovieModel> movieList= (ArrayList<MovieModel>) args.getSerializable("ARRAYLIST");

        assert movieList != null;
        tv_title.setText(movieList.get(position).getTitle());
        tv_overView.setText(movieList.get(position).getOverview());
       double rating=movieList.get(position).getVote_average();
         tv_rating.setText(String.valueOf(rating));
        tv_date.setText(movieList.get(position).getRelease_date());
        tv_popularity.setText(String.valueOf(movieList.get(position).getPopularity()));
        String backDropUrl="https://image.tmdb.org/t/p/w342"+movieList.get(position).getBackdrop_path();
        Log.d("Pic", "onBindViewHolder: "+backDropUrl);
        Picasso.get()
                .load(backDropUrl)
                .fit()
                .placeholder(R.drawable.ic_launcher_background)
                .into(backdropImg);


    }

}

