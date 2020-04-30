package android.example.com.popularmovies.Activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.example.com.popularmovies.R;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);



        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.rrrrrr, new Fragment(), "LIST")
                    .commit();

        }
    }
}
