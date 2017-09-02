package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.MOVIE_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.NOW_PLAYING;
import static android.example.com.popularmovies.JavaClasses.Constants.ORIGINAL_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.POPULAR;
import static android.example.com.popularmovies.JavaClasses.Constants.RATING;
import static android.example.com.popularmovies.JavaClasses.Constants.RELEASE_DATE;
import static android.example.com.popularmovies.JavaClasses.Constants.SYNOPSIS;
import static android.example.com.popularmovies.JavaClasses.Constants.TOP_RATED;
import static android.example.com.popularmovies.JavaClasses.Constants.UPCOMING;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Constants;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.OnItemClickListener;
import android.example.com.popularmovies.MainActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    private CoordinatorLayout coordinatorLayout;
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private int i;
    private String[] Android_version = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };


    private String[] Urls = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_main_activity, container, false);

        coordinatorLayout = (CoordinatorLayout) rootview.findViewById(R.id.coordinator);
        Log.i(LOG_TAG, "SSSS");
        Log.i(LOG_TAG, "FGHJGG");
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Checknetworkinfo();


        return rootview;
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }


    private ArrayList<Movies> getData(){

        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        for(i = 0; i < Android_version.length; i++){
            Movies m = new Movies();
            m.SetName(Android_version[i]);
            m.setUrl(Urls[i]);
            moviesArrayList.add(m);

        }
        return moviesArrayList;

    }


    private void Checknetworkinfo() {

        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {

            new GetMoviesTask().execute(POPULAR);


        } else {
            Snackbar.make(coordinatorLayout, "Internet connection is off", Snackbar.LENGTH_LONG)
                    .setAction("Settings", new View.OnClickListener() {
                        @Override public void onClick(View view) {

                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);

                        }
                    }).show();


        }


    }


    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Movies>> {

        private ArrayList<Movies> movies;
        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("LOading");
            progressDialog.show();
        }

        @Override protected ArrayList<Movies> doInBackground(String... strings) {

            movies = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONArray array = root.getJSONArray("results");
                for (i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("title");
                    String posterurl = object.getString("poster_path");
                    String date = object.getString("release_date");
                    String rating = object.getString("vote_average");
                    String summary = object.getString("overview");
                    String adultv = object.getString("adult");
                    String originaltitle = object.getString("original_title");
                    Movies m = new Movies();
                    m.setUrl(BASE_URL + posterurl);
                    m.SetName(name);
                    m.setOverview(summary);
                    m.setReleasedate(date);
                    m.setRating(rating);
                    m.setAdultvalue(adultv);
                    m.setOriginalTitle(originaltitle);
                    movies.add(m);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override protected void onPostExecute(ArrayList<Movies> movies) {
            super.onPostExecute(movies);
            Log.i(LOG_TAG, "It works");
            if (movies != null) {
                Log.i(LOG_TAG, "HH");
                progressDialog.dismiss();
                MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getContext(),
                        new OnItemClickListener() {
                            @Override public void OnItemClick(Movies movies) {

                                Intent intent = new Intent(getContext(), DetailsView.class);
                                intent.putExtra(MOVIE_TITLE, movies.getName());
                                intent.putExtra(RELEASE_DATE, movies.getReleasedate());
                                intent.putExtra(RATING, movies.getRating());
                                intent.putExtra(SYNOPSIS, movies.getOverview());
                                intent.putExtra(ORIGINAL_TITLE, movies.getOriginalTitle());
                                intent.putExtra(ADULT, movies.getAdultvalue());
                                intent.putExtra(RELEASE_DATE, movies.getReleasedate());
                                intent.putExtra(Constants.IMAGE_URL, movies.getUrl());
                                startActivity(intent);

                            }
                        });
                recyclerView.setAdapter(moviesAdapter);
            } else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }
    }
}

