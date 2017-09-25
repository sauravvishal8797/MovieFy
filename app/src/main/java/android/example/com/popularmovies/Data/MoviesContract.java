package android.example.com.popularmovies.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by saurav on 9/9/17.
 */

public final class MoviesContract {


    public static final String CONTENT_AUTHORITY = "android.example.com.popularmovies";

    public static final Uri BASE_URI = Uri.parse("Content://" + CONTENT_AUTHORITY);

    public static final String PATH = "Movies";

    private MoviesContract(){

    }

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "Movies";

        public static final String COLOUMN_ID = "id";

        public static final String MOVIIE_ID = "movieid";

        public static final String COLOUMN_NAME = "MovieName";

        public static final String COLOUMN_SYNOPSIS = "Synopsis";

        public static final String COLOUMN_RATING = "Ratings";

        public static final String  COLOUMN_RELEASE_DATE = "ReleaseDate";

        public static final String COLOUMN_ADULT = "Certification";

        public static final String COLOUMN_POSTERS = "MoviePoster";
    }
}
