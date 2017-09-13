package android.example.com.popularmovies.JavaClasses;

/**
 * Created by saurav on 13/9/17.
 */

public class Reviews {

    private String review;
    private String author;

    public Reviews(){


    }

    public Reviews(String review, String author){

        this.author = author;
        this.review = review;
    }

    public void setReview(String review){

        this.review = review;
    }

    public String getReview(){

        return review;

    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){

        return author;
    }
}
