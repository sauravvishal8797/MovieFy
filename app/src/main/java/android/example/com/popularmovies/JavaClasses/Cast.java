package android.example.com.popularmovies.JavaClasses;

/**
 * Created by saurav on 13/9/17.
 */

public class Cast {

    private String cast_name;
    private String cast_character;
    private String cast_photo_url;

    public Cast(){

    }


    public Cast(String cast_character, String cast_name, String cast_photo_url){

        this.cast_name = cast_name;
        this.cast_character = cast_character;
        this.cast_photo_url = cast_photo_url;
    }

    public void setCast_name(String cast_name){
        this.cast_name = cast_name;
    }

    public void setCast_character(String cast_character){
        this.cast_character = cast_character;
    }

    public void setCast_photo_url(String cast_photo_url){

        this.cast_photo_url = cast_photo_url;
    }

    public String getCast_name(){

        return cast_name;
    }

    public String getCast_character(){
        return cast_character;
    }

    public String getCast_photo_url(){

        return cast_photo_url;
    }
}
