package com.example.popularmovies;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;

//http://api.themoviedb.org/3/movie/popular?api_key=4748185fcb844df5666eabf5c38f7e46
public class MovieModel implements Serializable {
    private double popularity;
    private int vote_count;
    private Boolean video;
    private String poster_path;
    private int id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private ArrayList<Integer> genre_ids;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;

    public MovieModel() {
    }

    public MovieModel(String poster_path, int id, String backdrop_path, String title, double vote_average, String overview, String release_date) {
        this.poster_path = poster_path;
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    public MovieModel(double popularity, String poster_path, int id, String backdrop_path, String title, double vote_average, String overview, String release_date) {
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    public MovieModel(double popularity, int vote_count, Boolean video, String poster_path, int id, boolean adult, String backdrop_path, String original_language, String original_title, ArrayList<Integer> genre_ids, String title, double vote_average, String overview, String release_date) {
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.poster_path = poster_path;
        this.id = id;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}