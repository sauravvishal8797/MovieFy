package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.MOVIE_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.ORIGINAL_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.POPULAR;
import static android.example.com.popularmovies.JavaClasses.Constants.RATING;
import static android.example.com.popularmovies.JavaClasses.Constants.RELEASE_DATE;
import static android.example.com.popularmovies.JavaClasses.Constants.SYNOPSIS;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.Adapters.ViewPagerFragmentAdapter;
import android.example.com.popularmovies.JavaClasses.Constants;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    private CoordinatorLayout coordinatorLayout;
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    private int i;
    private String[] Android_version = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };


    private String[] Urls = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_main_activity, container, false);

        ViewPager viewPager = (ViewPager) rootview.findViewById(R.id.pager);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        TabLayout layout = (TabLayout) rootview.findViewById(R.id.tab_layout);
        layout.setupWithViewPager(viewPager);



        return rootview;
    }




}

