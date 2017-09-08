package android.example.com.popularmovies.Activity;

import static android.example.com.popularmovies.R.id.fab;
import static android.example.com.popularmovies.R.id.fab_favourite;
import static android.example.com.popularmovies.R.id.fab_watchlist;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.example.com.popularmovies.R;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsView extends AppCompatActivity implements View.OnClickListener{

    private TextView Synopsis;
    private static final String LOG_TAG = DetailsView.class.getSimpleName();
    private TextView ratings;
    private TextView ratngvalue;
    private TextView rdate;
    private TextView adultvalue;
    private TextView Originaltitvalue;
    private boolean isFabOpen = false;
    private FloatingActionButton fb1, fb2, fb3;
    private Animation animator1, animator2, animator3, animation4;

    private ImageView imageView;
    private String name1;
    private String summary;
    private String url;
    private String rating;
    private String releasedate;
    private String Adit;
    private TextView TextTile;
    private TextView adult;
    private TextView release;
    private TextView OriginTitle;
    private String OriginalTitlevaluereceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);


        Synopsis = (TextView) findViewById(R.id.overview);
        ratings = (TextView) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.main_imageview_placeholder);
        ratngvalue = (TextView) findViewById(R.id.value);
        adult = (TextView) findViewById(R.id.adulttext);
        adultvalue = (TextView) findViewById(R.id.adultvalue);
        release = (TextView) findViewById(R.id.releasetext);
        rdate = (TextView) findViewById(R.id.date);
        OriginTitle = (TextView) findViewById(R.id.orignaltit);
        Originaltitvalue = (TextView) findViewById(R.id.originaltitvalue);
        fb1 = (FloatingActionButton) findViewById(fab);
        fb2 = (FloatingActionButton) findViewById(R.id.fab_favourite);
        fb3 = (FloatingActionButton) findViewById(R.id.fab_watchlist);
        animator1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        animator2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        animator3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fb1.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);



        Intent intent = getIntent();
        name1 = intent.getStringExtra("Title");
        summary = intent.getStringExtra("Overview");
        url = intent.getStringExtra("Url");
        rating = intent.getStringExtra("Rating");
        releasedate = intent.getStringExtra("Release Date");
        Adit = intent.getStringExtra("Adult");
        OriginalTitlevaluereceiver = intent.getStringExtra("OriginalTitle");
        LOaddata();

    }


    private void LOaddata() {

        Picasso.with(this).load(url).placeholder(R
                .drawable.birthdaycard)
                .into(imageView);

        Synopsis.setText(summary);
        rdate.setText(releasedate);
        adultvalue.setText(Adit);
        ratngvalue.setText(rating);
        ratings.setText(name1);
        Originaltitvalue.setText(OriginalTitlevaluereceiver);


    }

    @Override public void onClick(View view) {

        int id = view.getId();

        switch (id){

            case fab_favourite:
               Intent intent = new Intent(this, FavouritesActivity.class);
                startActivity(intent);
                break;

            case fab_watchlist:
                Toast.makeText(getApplicationContext(), "ghfhgjg", Toast.LENGTH_SHORT).show();
                break;

            case fab:
               animatefab();
                break;

        }

    }

    public void animatefab(){

        if(isFabOpen){

            fb3.startAnimation(animator3);
            fb1.startAnimation(animator2);
            fb2.startAnimation(animator2);
            fb1.setClickable(false);
            fb2.setClickable(false);
            isFabOpen = false;
            Log.i(LOG_TAG, "Done");

        }
        else{

            fb3.startAnimation(animator3);
            fb1.startAnimation(animator1);
            fb2.startAnimation(animator1);
            fb1.setClickable(true);
            fb2.setClickable(true);
            isFabOpen = true;
        }
    }
}
