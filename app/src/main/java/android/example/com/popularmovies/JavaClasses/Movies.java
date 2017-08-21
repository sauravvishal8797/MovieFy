package android.example.com.popularmovies.JavaClasses;

/**
 * Created by saurav on 21/8/17.
 */

public class Movies {

    private String name;
    private String url;


    public Movies(){

    }

    public Movies(String name, String url){

        this.name = name;
        this.url = url;
    }

    public void SetName(String name){

        this.name = name;
    }

    public String getName(){

        return name;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){

        return url;
    }
}
