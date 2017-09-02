package android.example.com.popularmovies.JavaClasses;

import android.content.Context;
import android.content.Intent;
import android.example.com.popularmovies.Fragments.NowPlayingMoviesFragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by saurav on 29/8/17.
 */

public class Constants {

    public static final String API_KEY = "e2a51d701ca40655dbb7d5156ff2f42e";

    public static final String BASE_URL = "http://image.tmdb.org/t/p/w500/";

    public static final String MOVIE_TITLE = "Title";

    public static final String RELEASE_DATE = "Release Date";

    public static final String SYNOPSIS = "Overview";

    public static final String RATING = "Rating";

    public static final String IMAGE_URL = "Url";

    public static final String ORIGINAL_TITLE = "OriginalTitle";

    public static final String ADULT = "Adult";

    public static final String TOP_RATED = "https://api.themoviedb" +
            ".org/3/movie/top_rated?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";

    public static final String POPULAR =
            "https://api.themoviedb.org/3/movie/popular?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";

    public static final String NOW_PLAYING = "https://api.themoviedb" +
            ".org/3/movie/now_playing?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";

    public static final String UPCOMING = "https://api.themoviedb" +
            ".org/3/movie/upcoming?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US";







}
