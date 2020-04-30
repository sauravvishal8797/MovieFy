package android.example.com.popularmovies.Activity;

import static android.example.com.popularmovies.Data.MoviesProvider.LOG_TAG;
import static android.example.com.popularmovies.R.id.fab;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import android.example.com.popularmovies.Adapters.DetailsFragmentAdapter;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsView extends AppCompatActivity implements View.OnClickListener {

   public static String movieid;
    private FloatingActionButton fb1, fb2, fb3;
    private Animation animator1;
    private Animation animator2;
    private Animation animator3;
    private Animation animation4;
    private ImageView imageView;
    private String url;
    private ImageView thumbnail;
    private TextView run;
    private TextView movietitle;
    private ProgressBar titleProgressBar;
    private ProgressBar detailProgressbar;
    private TextView moviegenre;
    private boolean isFabOpen = true;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        Intent intent = getIntent();
        movieid = intent.getStringExtra("id");
        url = intent.getStringExtra("url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager2);
        DetailsFragmentAdapter detailsFragmentAdapter = new DetailsFragmentAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(detailsFragmentAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.setupWithViewPager(viewPager);

        fb1 = (FloatingActionButton) findViewById(fab);
        thumbnail = (ImageView ) findViewById(R.id.moviethumbnail);
        run = (TextView) findViewById(R.id.duration);
        movietitle = (TextView) findViewById(R.id.main_title);
        titleProgressBar = (ProgressBar) findViewById(R.id.title_progressbar);
        moviegenre = (TextView) findViewById(R.id.Genre) ;
        imageView = (ImageView) findViewById(R.id.thumb);
        fb2 = (FloatingActionButton) findViewById(R.id.fab_favourite);
        fb3 = (FloatingActionButton) findViewById(R.id.fab_watchlist);
        animator1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        animator2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        animator3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fb1.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);


        LOaddata();
    }


    private void LOaddata() {

        Picasso.with(this).load(url).placeholder(R
                .drawable.birthdaycard)
                .into(imageView);
        Picasso.with(this).load(url).placeholder(R
                .drawable.birthdaycard)
                .into(thumbnail);
        new GetDEtailsTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + movieid + "?api_key=e2a51d701ca40655dbb7d5156ff2f42e");



    }

    @Override public void onClick(View view) {



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

    class GetDEtailsTask extends AsyncTask<String, Void, Map<String, String>> {

        private String Jsonresponse = " ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            movietitle.setVisibility(View.INVISIBLE);
            moviegenre.setVisibility(View.INVISIBLE);
            run.setVisibility(View.INVISIBLE);
            titleProgressBar.setVisibility(View.VISIBLE);
        }

        @Override protected Map<String, String> doInBackground(String... strings) {

            map = new HashMap<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                String run = root.getString("runtime");
                Integer time = Integer.parseInt(run);
                Integer hours = time/60;
                Integer min = time%60;
                String runtime = String.valueOf(hours) + " hrs " + String.valueOf(min) + " mins";
                StringBuilder genre = new StringBuilder();
                JSONArray Genres = root.getJSONArray("genres");
                for(int i = 0; i < Genres.length(); i++){
                    JSONObject object = Genres.getJSONObject(i);
                    String g = object.getString("name");
                    genre.append(g + ", ");
                }
                String title = root.getString("original_title");
                map.put("time", runtime);
                map.put("genre", String.valueOf(genre));
                map.put("title", title);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return map;
        }

        @Override protected void onPostExecute(final Map<String, String> map) {
            super.onPostExecute(map);
            Log.i(LOG_TAG, "It works");
            titleProgressBar.setVisibility(View.INVISIBLE);
            if (map != null) {
                movietitle.setVisibility(View.VISIBLE);
                moviegenre.setVisibility(View.VISIBLE);
                run.setVisibility(View.VISIBLE);
                run.setText(map.get("time"));
                movietitle.setText(map.get("title"));
                moviegenre.setText(map.get("genre"));

            } else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }
}

