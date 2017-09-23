package android.example.com.popularmovies.Adapters;

import android.example.com.popularmovies.Fragments.CastFragment;
import android.example.com.popularmovies.Fragments.FavouriteMoviesFragment;
import android.example.com.popularmovies.Fragments.FavouriteTvSeriesFragment;
import android.example.com.popularmovies.Fragments.InfoFragment;
import android.example.com.popularmovies.Fragments.ReviewsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by saurav on 23/9/17.
 */

public class DetailsFragmentAdapter  extends FragmentStatePagerAdapter {

    private int nooftabs;

    public DetailsFragmentAdapter(FragmentManager fm, int nooftaabs){

        super(fm);
        this.nooftabs = nooftaabs;
    }
    @Override public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new InfoFragment();


            case 1:
                return new CastFragment();

            case 2:
                return new ReviewsFragment();

            default:
                return null;
        }
    }

    @Override public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Info";
            case 1:
                return "Cast";
            case 2:
                return "Reviews";

            default:
                return null;
        }
    }


    @Override public int getCount() {
        return nooftabs;
    }
}
