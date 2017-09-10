package android.example.com.popularmovies.JavaClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saurav on 21/8/17.
 */

public class Movies implements Parcelable {

    private String name;
    private String url;
    private String title;
    private String releasedate;
    private String rating;
    private String Overview;
    private String OriginalTitle;
    private String Adultvalue;
    private String id;


    public Movies(){

    }

    public Movies(String name, String url, String title, String releasedate, String rating, String Overview, String
            OriginalTitle, String Adultvalue, String id){

        this.name = name;
        this.url = url;
        this.title = title;
        this.releasedate = releasedate;
        this.rating = rating;
        this.Overview = Overview;
        this.OriginalTitle = OriginalTitle;
        this.Adultvalue = Adultvalue;
        this.id = id;
    }

    private Movies(Parcel p){

        this.name = p.readString();
        this.url = p.readString();
        this.title = p.readString();
        this.releasedate = p.readString();
        this.rating = p.readString();
        this.Overview = p.readString();
        this.OriginalTitle = p.readString();
        this.Adultvalue = p.readString();
        this.id = p.readString();
    }

    public void setId(String id){

        this.id = id;
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

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(releasedate);
        parcel.writeString(rating);
        parcel.writeString(Overview);
        parcel.writeString(OriginalTitle);
        parcel.writeString(title);
        parcel.writeString(id);

    }

    private static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>(){
        @Override public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }

        @Override public Movies[] newArray(int i) {
            return new Movies[0];
        }
    };
}
