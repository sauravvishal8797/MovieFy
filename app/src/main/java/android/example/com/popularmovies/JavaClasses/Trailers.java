package android.example.com.popularmovies.JavaClasses;

import android.widget.ImageView;

/**
 * Created by saurav on 10/9/17.
 */

public class Trailers {

    private String trailer;
    private String url;

    public Trailers(){

    }


    public Trailers(String trailer, String url){

        this.trailer = trailer;
        this.url = url;
    }

    public void setTrailer(String trailer){

        this.trailer = trailer;
    }

    public String getTrailer(){

        return trailer;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
}
