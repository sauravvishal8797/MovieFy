package android.example.com.popularmovies;

import android.example.com.popularmovies.Fragments.MainActivityFragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      FragmentManager frag = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction F = frag.beginTransaction();
        MainActivityFragment m = new MainActivityFragment();
        F.add(R.id.lllll, m);
        F.commit();

    }


    }

