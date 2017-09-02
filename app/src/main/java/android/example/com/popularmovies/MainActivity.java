package android.example.com.popularmovies;

import static android.R.attr.ems;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.OnItemClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ListView listView;

    private DrawerLayout drawerLayout;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private String [] Items;

    private OnItemClickListener OnItemClickListener;

    private CoordinatorLayout coordinatorLayout;

    private ArrayAdapter<String> arrayAdapter;

    private ProgressDialog progressDialog;

    private int i;

    private static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";

    private static final String TOP_RATED = "https://api.themoviedb" +
            ".org/3/movie/top_rated?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";


    private static final String API_KEY = "e2a51d701ca40655dbb7d5156ff2f42e";

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w500/";

    private static final String MOVIE_TITLE = "Title";

    private static final String RELEASE_DATE = "Release Date";

    private static final String SYNOPSIS = "Overview";

    private static final String RATING = "Rating";


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
        Items = getResources().getStringArray(R.array.drawer_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Log.i(LOG_TAG, "SSSS");
        Log.i(LOG_TAG, "FGHJGG");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Checknetworkinfo();







    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.top_rated:
                new GetMoviesTask().execute(TOP_RATED);
                return true;
            case R.id.popular:
                new GetMoviesTask().execute(URL);
            default:
                return super.onOptionsItemSelected(item);

        }
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

    private void Checknetworkinfo(){

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){

            new GetMoviesTask().execute(URL);





        }
        else{
            Snackbar.make(coordinatorLayout, "Internet connection is off", Snackbar.LENGTH_LONG)
                    .setAction("Settings", new View.OnClickListener() {
                        @Override public void onClick(View view) {

                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);

                        }
                    }).show();






        }




    }



    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Movies>>{

        private ArrayList<Movies> movies;
        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
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
            if(movies != null){
                Log.i(LOG_TAG, "HH");
                progressDialog.dismiss();
                MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getApplicationContext(),
                        new OnItemClickListener() {
                            @Override public void OnItemClick(Movies movies) {

                                Intent intent = new Intent(MainActivity.this, DetailsView.class);
                                intent.putExtra(MOVIE_TITLE, movies.getName());
                                intent.putExtra(RELEASE_DATE, movies.getReleasedate());
                                intent.putExtra(RATING, movies.getRating());
                                intent.putExtra(SYNOPSIS, movies.getOverview());
                                startActivity(intent);

                            }
                        });
                recyclerView.setAdapter(moviesAdapter);
            }
            else{
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }
    }
}
