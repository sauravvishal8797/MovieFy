package android.example.com.popularmovies.Activity;

import static java.security.AccessController.getContext;

import static android.R.attr.id;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_ADULT;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_NAME;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_POSTERS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RATING;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RELEASE_DATE;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_SYNOPSIS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.TABLE_NAME;
import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;
import static android.example.com.popularmovies.R.id.fab;
import static android.example.com.popularmovies.R.id.fab_favourite;
import static android.example.com.popularmovies.R.id.fab_watchlist;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.Adapters.TrailersAdapter;
import android.example.com.popularmovies.Adapters.ViewPagerFragmentAdapter;
import android.example.com.popularmovies.Data.FavouriteMoviesHelper;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.MoviesDetails;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.Trailers;
import android.example.com.popularmovies.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsView extends AppCompatActivity implements View.OnClickListener {

    private TextView Synopsis;
    private static final String LOG_TAG = DetailsView.class.getSimpleName();
    private TextView ratings;
    private TextView ratngvalue;
    private TextView rdate;
    private TextView adultvalue;
    private TextView Originaltitvalue;
    private boolean isFabOpen = false;
    private FloatingActionButton fb1, fb2, fb3;
    private Animation animator1, animator2, animator3, animation4;

    private ImageView imageView;
    private String name1;
    private String summary;
    private String url;
    private ArrayList<Trailers> movies;
    private ProgressDialog progressDialog;
    private int i;
    private static final String id = "571b76d8c3a36864e00025a0";
    private TextView textView1;
    private String rating;
    private String releasedate;
    private MoviesDetails moviesDetails;
    private String Adit;
    public static String movieid;
    private RecyclerView recyclerView;
    private TextView TextTile;
    private TextView adult;
    private TextView budget;
    private TextView release;
    private TextView OriginTitle;
    private TextView revenue;
    private String OriginalTitlevaluereceiver;
    private String key;
    private FavouriteMoviesHelper favouriteMoviesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        Intent intent = getIntent();
        movieid = intent.getStringExtra("id");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        //favouriteMoviesHelper = new FavouriteMoviesHelper(getApplicationContext());
        //Synopsis = (TextView) findViewById(R.id.overview);
        ratings = (TextView) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.thumb);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager2);
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.setupWithViewPager(viewPager);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view33);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        budget = (TextView) findViewById(R.id.budgetvalue);
        revenue = (TextView) findViewById(R.id.revenue);
        textView1 = (TextView) findViewById(R.id.trailer);

        ratngvalue = (TextView) findViewById(R.id.value);
        adult = (TextView) findViewById(R.id.adulttext);
        adultvalue = (TextView) findViewById(R.id.adultvalue);
        release = (TextView) findViewById(R.id.releasetext);
        rdate = (TextView) findViewById(R.id.date);
        OriginTitle = (TextView) findViewById(R.id.orignaltit);
        Originaltitvalue = (TextView) findViewById(R.id.originaltitvalue);
        fb1 = (FloatingActionButton) findViewById(fab);
        fb2 = (FloatingActionButton) findViewById(R.id.fab_favourite);
        fb3 = (FloatingActionButton) findViewById(R.id.fab_watchlist);
        animator1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        animator2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        animator3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fb1.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);


        //LOaddata();
        new GetMoviesDetailsTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + movieid + "?api_key=e2a51d701ca40655dbb7d5156ff2f42e");
        new GetMoviesTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + movieid + "/videos?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US");

    }


    private void LOaddata() {

        Picasso.with(this).load(url).placeholder(R
                .drawable.birthdaycard)
                .into(imageView);

        Synopsis.setText(summary);
        rdate.setText(releasedate);
        adultvalue.setText(Adit);
        ratngvalue.setText(rating);
        ratings.setText(name1);
        Originaltitvalue.setText(OriginalTitlevaluereceiver);


    }

    @Override public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case fab_favourite:

                SQLiteDatabase database = favouriteMoviesHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLOUMN_NAME, OriginalTitlevaluereceiver);
                contentValues.put(COLOUMN_ADULT, Adit);
                contentValues.put(COLOUMN_RATING, Double.parseDouble(rating));
                contentValues.put(COLOUMN_RELEASE_DATE, releasedate);
                contentValues.put(COLOUMN_SYNOPSIS, summary);
                contentValues.put(COLOUMN_POSTERS, url);
                database.insert(TABLE_NAME, null, contentValues);

                break;

            case fab_watchlist:
                Toast.makeText(getApplicationContext(), "ghfhgjg", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CastActivity.class);
                intent.putExtra("movie_id", movieid);
                startActivity(intent);
                break;

            case fab:
                // animatefab();
                Intent intent1 = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent1);

                break;

        }

    }

    public boolean checkExists() {
        SQLiteDatabase database = favouriteMoviesHelper.getReadableDatabase();
        Cursor c = database.query(TABLE_NAME, new String[]{COLOUMN_NAME, COLOUMN_POSTERS, COLOUMN_SYNOPSIS},
                COLOUMN_NAME + "=?", new
                        String[]{OriginalTitlevaluereceiver}, null,
                null, null);
        if (c != null) {
            return false;

        } else {
            return true;
        }

    }

    public void animatefab() {

        if (isFabOpen) {

            fb3.startAnimation(animator3);
            fb1.startAnimation(animator2);
            fb2.startAnimation(animator2);
            fb1.setClickable(false);
            fb2.setClickable(false);
            isFabOpen = false;
            Log.i(LOG_TAG, "Done");

        } else {

            fb3.startAnimation(animator3);
            fb1.startAnimation(animator1);
            fb2.startAnimation(animator1);
            fb1.setClickable(true);
            fb2.setClickable(true);
            isFabOpen = true;
        }
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

                    for (i = 0; i < genreArray.length(); i++) {
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



                ratings.setText(m.getName());
                rdate.setText(m.getReleasedate());
                ratngvalue.setText(m.getRating());
                Synopsis.setText(m.getOverview());
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
                    for (i = 0; i < array.length(); i++) {
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

