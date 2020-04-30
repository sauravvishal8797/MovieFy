package android.example.com.popularmovies.Adapters;

import android.example.com.popularmovies.Fragments.FavouriteMoviesFragment;
import android.example.com.popularmovies.Fragments.FavouriteTvSeriesFragment;
import android.example.com.popularmovies.Fragments.NowPlayingMoviesFragment;
import android.example.com.popularmovies.Fragments.PopularMoviesFragment;
import android.example.com.popularmovies.Fragments.TopRatedMoviesFragment;
import android.example.com.popularmovies.Fragments.UpcomingMoviesFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by saurav on 8/9/17.
 */

public class FavouritesFragmentAdapter extends FragmentStatePagerAdapter {


    private int nooftabs;
    public FavouritesFragmentAdapter(FragmentManager fm, int tabs) {

        super(fm);
        nooftabs = tabs;
    }

    @Override public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new FavouriteMoviesFragment();


            case 1:
                return new FavouriteTvSeriesFragment();

            default:
                return null;
        }
    }

    @Override public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Favourite Movies";
            case 1:
                return "Favourite Tv Series";

            default:
                return null;
        }
    }

    @Override public int getCount() {
        return nooftabs;
    }
}


