package android.example.com.popularmovies.Activity;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.example.com.popularmovies.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsView extends AppCompatActivity {

    private TextView Synopsis;
    private TextView ratings;
    private TextView ratngvalue;
    private TextView rdate;
    private TextView adultvalue;
    private TextView Originaltitvalue;

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
        TextTile = (TextView) findViewById(R.id.main_textview_title);
        ratings = (TextView) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.main_imageview_placeholder);
        ratngvalue = (TextView) findViewById(R.id.value);
        adult = (TextView) findViewById(R.id.adulttext);
        adultvalue = (TextView) findViewById(R.id.adultvalue);
        release = (TextView) findViewById(R.id.releasetext);
        rdate = (TextView) findViewById(R.id.date);
        OriginTitle = (TextView) findViewById(R.id.orignaltit);
        Originaltitvalue = (TextView) findViewById(R.id.originaltitvalue);


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
}
