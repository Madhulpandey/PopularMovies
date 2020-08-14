package com.example.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DisplayAdapter displayAdapter;
    private Button btn;
    String response=null;
    ArrayList<MovieModel> finalListOfmovies=new ArrayList<>();
    MovieModel model=null;
    ArrayList<MovieModel> movieList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        new ApiUtils().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.sortBy_popularity){

        }
        if(item.getItemId()==R.id.sortBy_rating){

        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    private class ApiUtils extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String link="https://api.themoviedb.org/3/discover/movie?api_key=4748185fcb844df5666eabf5c38f7e46";

            URL url= null;
            try {
                url = new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection= null;
            try {
                assert url != null;
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert connection != null;
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            InputStream in= null;
            try {
                in = new BufferedInputStream(connection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            response=convertStreamToString(in);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            tv.setText(response);
            try {
                  finalListOfmovies=parseMovie(response);
                displayAdapter=new DisplayAdapter(finalListOfmovies, new DisplayAdapter.DetailsAdapterListener() {
                    @Override
                    public void DetsOnClick(View v, int position) {
                        Intent intent= new Intent(MainActivity.this,DetailsActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("ARRAYLIST",(Serializable) finalListOfmovies);
                        bundle.putInt("position",position);
                        intent.putExtra("Bundle",bundle);
                        startActivity(intent);
                        //Toast.makeText(MainActivity.this, "CHal raha hai brooo", Toast.LENGTH_SHORT).show();
                    }
                }, MainActivity.this);
                recyclerView.setAdapter(displayAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void shiftActivity()
    {
    }

    private  String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static ArrayList<MovieModel> parseMovie(String json) throws JSONException{

        JSONObject obj=new JSONObject(json);
        ArrayList<MovieModel> listofMovies=new ArrayList<>();
        JSONArray results=obj.optJSONArray("results");
        String data=null;
        assert results != null;
        for(int i = 0; i<results.length(); i++){
            MovieModel model=new MovieModel();
            JSONObject jsonObject=results.getJSONObject(i);
            String posterPath=jsonObject.optString("poster_path").toString();
            String BackDropPath=jsonObject.optString("backdrop_path").toString();
            int id=Integer.parseInt(jsonObject.optString("id").toString());
            String title=jsonObject.optString("title").toString();
            double popularity=Double.parseDouble(jsonObject.optString("popularity").toString());
            double voteAvg=Double.parseDouble(jsonObject.optString("vote_average").toString());
            String overView=jsonObject.optString("overview").toString();
            String release_date=jsonObject.optString("release_date").toString();

            listofMovies.add(new MovieModel(popularity,posterPath,id,BackDropPath,title,voteAvg,overView,release_date));
        }
        return listofMovies;

    }

}