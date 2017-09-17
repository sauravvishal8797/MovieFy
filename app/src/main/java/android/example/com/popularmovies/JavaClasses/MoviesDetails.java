package android.example.com.popularmovies.JavaClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saurav on 21/8/17.
 */

public class MoviesDetails {

    private String name;
    private String url;
    private String title;
    private String releasedate;
    private String rating;
    private String Overview;
    private String OriginalTitle;
    private String Adultvalue;
    private String budget;
    private String homepage;
    private String runtime;
    private String revenue;
    private String genre;
    private String id;


    public MoviesDetails(){

    }

    public MoviesDetails(String name, String url, String title, String releasedate, String rating, String Overview, String
            OriginalTitle, String Adultvalue, String id, String budget, String homepage, String runtime, String
                          revenue, String genre){

        this.name = name;
        this.url = url;
        this.title = title;
        this.releasedate = releasedate;
        this.rating = rating;
        this.Overview = Overview;
        this.OriginalTitle = OriginalTitle;
        this.Adultvalue = Adultvalue;
        this.id = id;
        this.budget = budget;
        this.homepage = homepage;
        this.runtime = runtime;
        this.revenue = revenue;
        this.genre = genre;
    }


    public void setId(String id){

        this.id = id;
    }

    public void setBudget(String budget){
        this.budget = budget;
    }

    public String getBudget(){
        return budget;
    }

    public void setHomepage(String homepage){
        this.homepage = homepage;
    }
    public String getHomepage(){
        return homepage;
    }

    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public String getRuntime(){
        return runtime;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getGenre(){
        return genre;
    }

    public void setRevenue(String revenue){
        this.revenue = revenue;
    }

    public String getRevenue(){

        return revenue;
    }

    public String getId(){

        return id;
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
