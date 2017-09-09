package android.example.com.popularmovies.Data;

import android.provider.BaseColumns;

/**
 * Created by saurav on 9/9/17.
 */

public final class MoviesContract {

    private MoviesContract(){

    }

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "Favourite Movies";

        public static final String COLOUMN_NAME = "Movie Name";

        public static final String COLOUMN_SYNOPSIS = "Synopsis";

        public static final String COLOUMN_RATING = "Ratings";

        public static final String  COLOUMN_RELEASE_DATE = "Release Date";

        public static final String COLOUMN_ADULT = "Certification";

        public static final String COLOUMN_POSTERS = "Movie Poster";
    }
}
