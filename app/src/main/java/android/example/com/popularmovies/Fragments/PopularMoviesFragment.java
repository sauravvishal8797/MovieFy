package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.ID;
import static android.example.com.popularmovies.JavaClasses.Constants.IMAGE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.MOVIE_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.ORIGINAL_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.RATING;
import static android.example.com.popularmovies.JavaClasses.Constants.RELEASE_DATE;
import static android.example.com.popularmovies.JavaClasses.Constants.SYNOPSIS;

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
import android.example.com.popularmovies.JavaClasses.EndlessRecyclerViewScrollListener;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends Fragment {

    private ArrayList<Movies> movies;
    private static final String LOG_TAG = PopularMoviesFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private int i;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private SwipeRefreshLayout layout;

    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Movies m = (Movies) view.findViewById(R.id.thumbnail).getTag();
            Log.i("lalalala", m.getName());
            Intent intent = new Intent(getContext(), DetailsView.class);
            Log.i(LOG_TAG, m.getName());
            Log.i(LOG_TAG, m.getUrl());
            intent.putExtra("url", m.getUrl());
            intent.putExtra(ID, m.getId());
            startActivity(intent);

        }
    };


    public PopularMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        layout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                Checknetworkinfo();
            }
        });
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view33);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                loadNextDataFromApi(page);

            }
        };
        Checknetworkinfo();
       // Log.i("opppp", String.valueOf(movies.size()));
        return rootview;
    }

    public void loadNextDataFromApi(int offset){

        new GetMoviesTask().execute("https://api.themoviedb" +
                ".org/3/movie/popular?api_key=e2a51d701ca40655dbb7d5156ff2f42e&append_to_response=credits&&"
                + "page=" + offset );
    }

    private void Checknetworkinfo() {

        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {

            new GetMoviesTask().execute(Constants.POPULAR);
        } else {
            Toast.makeText(getContext(), "You need to switch on the internet", Toast.LENGTH_SHORT).show();
        }
    }

    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Movies>> {

        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("LOading");
            progressDialog.setCanceledOnTouchOutside(false);
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
                    String title = object.getString("original_title");
                    String id = object.getString("id");
                    Movies m = new Movies();
                    m.setUrl(BASE_URL + posterurl);
                    m.SetName(name);
                    m.setOverview(summary);
                    m.setReleasedate(date);
                    m.setRating(rating);
                    m.setAdultvalue(adultv);
                    m.setOriginalTitle(title);
                    m.setId(id);
                    movies.add(m);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override protected void onPostExecute(final ArrayList<Movies> movies) {
            super.onPostExecute(movies);
            Log.i(LOG_TAG, "It works");
            if (movies != null) {
                Log.i(LOG_TAG, movies.get(2).getName());
                progressDialog.dismiss();
                layout.setRefreshing(false);
                moviesAdapter = new MoviesAdapter(movies);
                //moviesAdapter.setOnClickListener(onClickListener);
                moviesAdapter.setOnClickListener(onClickListener);
                recyclerView.setAdapter(moviesAdapter);
            }
            else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }
}
