package android.example.com.popularmovies.Fragments;

import android.example.com.popularmovies.Adapters.ViewPagerFragmentAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {


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

