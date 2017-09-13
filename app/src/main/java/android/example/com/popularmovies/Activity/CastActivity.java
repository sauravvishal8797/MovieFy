package android.example.com.popularmovies.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.example.com.popularmovies.R;

public class CastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        Intent intent = getIntent();
        String id = intent.getStringExtra("movie_id");

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fafafa, new Fragment(), "LIST")
                    .commit();

        }

    }
}
