package android.example.com.popularmovies.Adapters;

import android.example.com.popularmovies.Fragments.NowPlayingMoviesFragment;
import android.example.com.popularmovies.Fragments.PopularMoviesFragment;
import android.example.com.popularmovies.Fragments.TopRatedMoviesFragment;
import android.example.com.popularmovies.Fragments.UpcomingMoviesFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by saurav on 28/8/17.
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private int nooftabs;
    public ViewPagerFragmentAdapter(FragmentManager fm, int tabs) {

        super(fm);
        nooftabs = tabs;
    }

    @Override public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new PopularMoviesFragment();


            case 1:
                return new TopRatedMoviesFragment();

            case 2:
                return new UpcomingMoviesFragment();

            case 3:
                return new NowPlayingMoviesFragment();

            default:
                return null;
        }
    }

    @Override public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "PopularMovies";
            case 1:
                return "Top Rated";
            case 2:
                return "Upcoming";
            case 3:
                return "NowPlaying";
            default:
                return null;
        }
    }

    @Override public int getCount() {
        return nooftabs;
    }
}
