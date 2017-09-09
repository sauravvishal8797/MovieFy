package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_ADULT;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_NAME;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_POSTERS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RATING;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RELEASE_DATE;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_SYNOPSIS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.TABLE_NAME;
import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.IMAGE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.MOVIE_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.ORIGINAL_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.RATING;
import static android.example.com.popularmovies.JavaClasses.Constants.RELEASE_DATE;
import static android.example.com.popularmovies.JavaClasses.Constants.SYNOPSIS;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.Data.FavouriteMoviesHelper;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteMoviesFragment extends Fragment {

    private   ArrayList<Movies> movies;
    private String name;
    private String url;
    private String adult;
    private String synopsis;
    private String release;
    private String rating;
    private static final String LOG_TAG = FavouriteMoviesFragment.class.getSimpleName();

    private FavouriteMoviesHelper favouriteMoviesHelper;


    public FavouriteMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        favouriteMoviesHelper = new FavouriteMoviesHelper(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_movies, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view33);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
       Cursor cursor = getdatafromdatabase();
        loadArraylist(cursor);
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, new MoviesAdapter.OnItemClickListener() {
            @Override public void OnItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsView.class);

                Movies movies1 = movies.get(position);
                Log.i(LOG_TAG, movies1.getName());
                Log.i(LOG_TAG, movies1.getUrl());
                intent.putExtra(MOVIE_TITLE, movies1.getName());
                intent.putExtra(SYNOPSIS, movies1.getOverview());
                intent.putExtra(IMAGE_URL, movies1.getUrl());
                intent.putExtra(ADULT, movies1.getAdultvalue());
                intent.putExtra(RATING, movies1.getRating());
                intent.putExtra(RELEASE_DATE, movies1.getReleasedate());
                intent.putExtra(ORIGINAL_TITLE, movies1.getOriginalTitle());
                startActivity(intent);
            }
        });
        return view;
    }

    private Cursor getdatafromdatabase(){

        SQLiteDatabase database = favouriteMoviesHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;

    }

    private ArrayList<Movies> loadArraylist(Cursor cursor){

        movies = new ArrayList<>();



        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex(COLOUMN_NAME));
            url = cursor.getString(cursor.getColumnIndex(COLOUMN_POSTERS));
            adult = cursor.getString(cursor.getColumnIndex(COLOUMN_ADULT));
            synopsis = cursor.getString(cursor.getColumnIndex(COLOUMN_SYNOPSIS));
            release = cursor.getString(cursor.getColumnIndex(COLOUMN_RELEASE_DATE));
            rating = cursor.getString(cursor.getColumnIndex(COLOUMN_RATING));
            Movies m = new Movies();
            m.setOriginalTitle(name);
            m.setAdultvalue(adult);
            m.setRating(rating);
            m.setOverview(synopsis);
            m.setUrl(url);
            m.setReleasedate(release);
            movies.add(m);
        }

        return movies;
    }

}
