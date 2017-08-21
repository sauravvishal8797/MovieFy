package android.example.com.popularmovies;

import java.util.ArrayList;

import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private CoordinatorLayout coordinatorLayout;

    private ArrayList<Movies> movies;

    private ProgressBar progressBar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movies = new ArrayList<>();
        movies = getData();
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getApplicationContext());

        recyclerView.setAdapter(moviesAdapter);
        



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
}
