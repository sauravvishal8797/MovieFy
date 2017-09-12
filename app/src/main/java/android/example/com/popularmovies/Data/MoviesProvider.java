package android.example.com.popularmovies.Data;

import static android.example.com.popularmovies.Data.MoviesContract.CONTENT_AUTHORITY;
import static android.example.com.popularmovies.Data.MoviesContract.PATH;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by saurav on 12/9/17.
 */

public class MoviesProvider extends ContentProvider {

    public static final String LOG_TAG = MoviesProvider.class.getSimpleName();

    public static final int MOVIES = 100;
    public static final int MOVIES_ID = 101;
    private FavouriteMoviesHelper favouriteMoviesHelper;

    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        uriMatcher.addURI(CONTENT_AUTHORITY, PATH, MOVIES);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH + "/", MOVIES_ID );
    }

    @Override public boolean onCreate() {

        favouriteMoviesHelper = new FavouriteMoviesHelper(getContext());
        return false;
    }

    @Nullable @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1,
                        @Nullable String s1) {
        return null;
    }

    @Nullable @Override public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
                                @Nullable String[] strings) {
        return 0;
    }
}
