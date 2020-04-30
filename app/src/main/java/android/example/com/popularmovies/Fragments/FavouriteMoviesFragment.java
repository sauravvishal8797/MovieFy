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
import static android.os.Build.VERSION_CODES.M;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.DbAdapter;
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
    private String[] s = {COLOUMN_NAME, COLOUMN_POSTERS};


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
        DbAdapter dbAdapter = new DbAdapter(cursor);
        recyclerView.setAdapter(dbAdapter);

        return view;
    }

    private Cursor getdatafromdatabase(){

        SQLiteDatabase database = favouriteMoviesHelper.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null );
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
