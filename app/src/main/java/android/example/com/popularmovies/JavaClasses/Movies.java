package android.example.com.popularmovies.JavaClasses;

/**
 * Created by saurav on 21/8/17.
 */

public class Movies {

    private String name;
    private String url;
    private String title;
    private String releasedate;
    private String rating;
    private String Overview;
    private String OriginalTitle;
    private String Adultvalue;


    public Movies(){

    }

    public Movies(String name, String url, String title, String releasedate, String rating, String Overview, String
            OriginalTitle, String Adultvalue){

        this.name = name;
        this.url = url;
        this.title = title;
        this.releasedate = releasedate;
        this.rating = rating;
        this.Overview = Overview;
        this.OriginalTitle = OriginalTitle;
        this.Adultvalue = Adultvalue;
    }

    public void setAdultvalue(String Adultvalue){

        this.Adultvalue = Adultvalue;
    }

    public String getAdultvalue(){

        return Adultvalue;
    }

    public void setOriginalTitle(String OriginalTitle){
        this.OriginalTitle = OriginalTitle;
    }

    public String getOriginalTitle(){

        return OriginalTitle;
    }

    public void SetName(String name){

        this.name = name;
    }

    public String getName(){

        return name;
    }

    public void SetTitle(String title){

        this.title = title;
    }

    public String getTitle(){

        return title;
    }

    public void setReleasedate(String releasedate){

        this.releasedate = releasedate;
    }

    public String getReleasedate(){

        return releasedate;
    }

    public void setRating(String rating){

        this.rating = rating;
    }

    public String getRating(){

        return rating;
    }

    public void setOverview(String Overview){

        this.Overview = Overview;
    }

    public String getOverview(){

        return Overview;
    }

    public void setUrl(String url){

        this.url = url;
    }

    public String getUrl(){

        return url;
    }
}
