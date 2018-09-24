package android.example.com.popularmovies.Data;

import static android.example.com.popularmovies.Data.MoviesContract.CONTENT_AUTHORITY;
import static android.example.com.popularmovies.Data.MoviesContract.PATH;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH + "/#", MOVIES_ID );
    }

    @Override public boolean onCreate() {

        favouriteMoviesHelper = new FavouriteMoviesHelper(getContext());
        return true;
    }

    @Nullable @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1,
                        @Nullable String s1) {
        SQLiteDatabase db = favouriteMoviesHelper.getReadableDatabase();
        Cursor cursor = null;
        final int match = uriMatcher.match(uri);
        switch (match){
            case MOVIES:
                cursor = db.query(MoviesContract.MovieEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            case MOVIES_ID:
                s = MoviesContract.MovieEntry.COLOUMN_ID + "=?";
                strings1 = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MoviesContract.MovieEntry.TABLE_NAME, null, s, strings1, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri" + uri);
        }
        return cursor;

    }

    @Nullable @Override public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase db = favouriteMoviesHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri uri1;
        switch(match){

            case MOVIES:
                long id = db.insert(MoviesContract.MovieEntry.TABLE_NAME, null, contentValues);
                if(id > 0){
                    uri1 = ContentUris.withAppendedId(MoviesContract.BASE_URI, id);
                }
                else{
                    throw new android.database.sqlite.SQLiteException("Cant insert into the database");
                }
                break;
            default:
                throw new UnsupportedOperationException("Uri not valid" + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri1;

    }

    @Override public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
                                @Nullable String[] strings) {
        return 0;
    }
}
