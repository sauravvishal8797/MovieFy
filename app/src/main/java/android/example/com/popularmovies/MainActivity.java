package android.example.com.popularmovies;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private CoordinatorLayout coordinatorLayout;

    private ProgressDialog progressDialog;

    private int i;

    private static final String URL = "https://api.themoviedb" +
            ".org/3/movie/now_playing?api_key=e2a51d701ca40655dbb7d5156ff2f42e&page=1";

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

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Checknetworkinfo();



        



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
                for(i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("title");
                    String posterurl = object.getString("backdrop_path");
                    Movies m = new Movies();
                    m.setUrl(BASE_URL + posterurl);
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
            Log.i(LOG_TAG, "It works");
            if(movies != null){
                Log.i(LOG_TAG, "HH");
                progressDialog.dismiss();
                MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getApplicationContext());
                recyclerView.setAdapter(moviesAdapter);
            }
            else{
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }
    }
}
