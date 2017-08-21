package android.example.com.popularmovies;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private CoordinatorLayout coordinatorLayout;

    private ArrayList<Movies> movies;

    private ProgressBar progressBar;

    private int i;

    private static final String URL = "https://api.themoviedb" +
            ".org/3/discover/movie?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US&sort_by=popularity" +
            ".desc&include_adult=false&include_video=false";

    private static final String API_KEY = "e2a51d701ca40655dbb7d5156ff2f42e";

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w185/";


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movies = new ArrayList<>();
        new GetMoviesTask().execute(URL);

        



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

    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Movies>>{

        private ArrayList<Movies> movies;
        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override protected ArrayList<Movies> doInBackground(String... strings) {

            movies = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONArray array = root.getJSONArray("results");
                for(i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("title");
                    String posterurl = object.getString("backdrop");
                    Movies m = new Movies();
                    m.setUrl(posterurl);
                    m.SetName(name);
                    movies.add(m);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override protected void onPostExecute(ArrayList<Movies> movies) {
            super.onPostExecute(movies);
            if(movies != null){
                MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getApplicationContext());
                recyclerView.setAdapter(moviesAdapter);
            }
            else{
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }
    }
}
