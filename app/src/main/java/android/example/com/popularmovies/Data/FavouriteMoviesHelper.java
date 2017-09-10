package android.example.com.popularmovies.Data;

import static android.example.com.popularmovies.Data.MoviesContract.DATABASE_NAME;
import static android.example.com.popularmovies.Data.MoviesContract.DATABASE_VERSION;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_ADULT;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_ID;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_NAME;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_POSTERS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RATING;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_RELEASE_DATE;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_SYNOPSIS;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saurav on 9/9/17.
 */

public class FavouriteMoviesHelper extends SQLiteOpenHelper {


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLOUMN_NAME + " TEXT, " +
            COLOUMN_ADULT + " TEXT, " +
            COLOUMN_RATING + " DOUBLE" +
            ", " +
            COLOUMN_POSTERS + " TEXT, " +
            COLOUMN_RELEASE_DATE + " TEXT, " +
            COLOUMN_SYNOPSIS + " TEXT" + ");";

    public FavouriteMoviesHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
