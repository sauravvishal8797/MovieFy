package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.Activity.DetailsView.movieid;
import static android.example.com.popularmovies.Data.MoviesProvider.LOG_TAG;
import static android.example.com.popularmovies.R.id.fab;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.TrailersAdapter;
import android.example.com.popularmovies.JavaClasses.MoviesDetails;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.Trailers;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private TextView releasedate;
    private TextView adult;
    private TextView rating;
    private TextView budget;
    private TextView revenue;
    private TextView overveiew;
    private RecyclerView recyclerView;
    private TextView textView1;
    private TextView ratngvalue;
    private TextView adultvalue;
    private TextView release;
    private TextView rdate;
    private TextView OriginTitle;
    private TextView Originaltitvalue;
    private FloatingActionButton fb1, fb2, fb3;
    private Animation animator1;
    private Animation animator2;
    private Animation animator3;
    private Animation animation4;
    private ArrayList<Trailers> movies;
    private MoviesDetails moviesDetails;
    private TextView ratings;
    private String key;





    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view33);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        budget = (TextView) root.findViewById(R.id.budgetvalue);
        revenue = (TextView) root.findViewById(R.id.revenue);
        textView1 = (TextView) root.findViewById(R.id.trailer);
        overveiew = (TextView) root.findViewById(R.id.overview);

        ratngvalue = (TextView) root.findViewById(R.id.value);
        adult = (TextView) root.findViewById(R.id.adulttext);
        adultvalue = (TextView) root.findViewById(R.id.adultvalue);
        release = (TextView) root.findViewById(R.id.releasetext);
        rdate = (TextView) root.findViewById(R.id.date);
        OriginTitle = (TextView) root.findViewById(R.id.orignaltit);
        Originaltitvalue = (TextView) root.findViewById(R.id.originaltitvalue);
        fb1 = (FloatingActionButton) root.findViewById(fab);
        fb2 = (FloatingActionButton) root.findViewById(R.id.fab_favourite);
        fb3 = (FloatingActionButton) root.findViewById(R.id.fab_watchlist);
        animator1 = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        animator2 = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        animator3 = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        animation4 = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);

        fb1.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);


        //LOaddata();
        new GetMoviesDetailsTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + movieid + "?api_key=e2a51d701ca40655dbb7d5156ff2f42e");
        new GetMoviesTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + movieid + "/videos?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US");


        return root;
    }

    @Override public void onClick(View view) {

    }

    class GetMoviesDetailsTask extends AsyncTask<String, Void, MoviesDetails> {

        private String Jsonresponse = " ";


        @Override protected MoviesDetails doInBackground(String... strings) {

            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);


                String name = root.getString("title");
                String posterurl = root.getString("poster_path");
                String date = root.getString("release_date");
                String rating = root.getString("vote_average");
                String summary = root.getString("overview");
                String adultv = root.getString("adult");
                String title = root.getString("original_title");
                String id = root.getString("id");
                String budget = root.getString("budget");
                String homepage = root.getString("homepage");
                String revenue = root.getString("revenue");
                String runtime = root.getString("runtime");
                JSONArray genreArray = root.getJSONArray("genre");
                StringBuilder genre = new StringBuilder();

                for (int i = 0; i < genreArray.length(); i++) {
                    JSONObject genreobject = genreArray.getJSONObject(i);
                    String g = genreobject.getString("name");
                    genre.append(g + ",");
                }
                String ge2 = genre.toString();
                moviesDetails = new MoviesDetails();
                moviesDetails.setRevenue(revenue);
                moviesDetails.setBudget(budget);
                moviesDetails.setRuntime(runtime);
                moviesDetails.setAdultvalue(adultv);
                moviesDetails.setOriginalTitle(title);
                moviesDetails.setOverview(summary);
                moviesDetails.setGenre(ge2);
                moviesDetails.setHomepage(homepage);
                moviesDetails.setId(id);
                moviesDetails.setRating(rating);
                moviesDetails.setReleasedate(date);
                moviesDetails.setUrl(posterurl);
                moviesDetails.SetName(name);



            } catch (JSONException e) {
                e.printStackTrace();
            }
            return moviesDetails;
        }

        @Override protected void onPostExecute(MoviesDetails m) {
            super.onPostExecute(m);
            // Log.i(LOG_TAG, m.getName());




            rdate.setText(m.getReleasedate());
            ratngvalue.setText(m.getRating());
            overveiew.setText(m.getOverview());
            adultvalue.setText(m.getAdultvalue());
            Originaltitvalue.setText(m.getOriginalTitle());
            budget.setText(m.getBudget());
            revenue.setText(m.getRevenue());

        }

    }


    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Trailers>> {

        private String Jsonresponse = " ";


        @Override protected ArrayList<Trailers> doInBackground(String... strings) {

            movies = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONArray array = root.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("name");
                    key = object.getString("key");
                    Trailers trailers = new Trailers();
                    trailers.setTrailer(name);
                    trailers.setUrl("https://img.youtube.com/vi/" + key + "/" + "hqdefault.jpg");
                    movies.add(trailers);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override protected void onPostExecute(final ArrayList<Trailers> movies) {
            super.onPostExecute(movies);
            Log.i(LOG_TAG, "It works");
            if (movies != null) {
                TrailersAdapter trailersAdapter = new TrailersAdapter(movies,
                        new TrailersAdapter.OnItemClickListener() {
                            @Override public void OnItemClick(int position) {
                                showTrailer(key);
                            }
                        });

                recyclerView.setAdapter(trailersAdapter);
            } else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }

    public void showTrailer(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}


