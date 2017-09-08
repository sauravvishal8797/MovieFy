package android.example.com.popularmovies.Fragments;


import android.example.com.popularmovies.Adapters.FavouritesFragmentAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesActivityFragment extends Fragment {


    public FavouritesActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_favourites_activity, container, false);
        Toolbar toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        toolbar.setTitle("Favourites");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        ViewPager viewPager = (ViewPager) rootview.findViewById(R.id.pager);
        FavouritesFragmentAdapter favouritesFragmentAdapter = new FavouritesFragmentAdapter(getFragmentManager(), 2);
        viewPager.setAdapter(favouritesFragmentAdapter);
        TabLayout tabLayout = (TabLayout) rootview.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return rootview;
    }

}
