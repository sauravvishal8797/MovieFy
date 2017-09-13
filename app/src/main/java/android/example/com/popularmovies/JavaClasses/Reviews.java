package android.example.com.popularmovies.JavaClasses;

/**
 * Created by saurav on 13/9/17.
 */

public class Reviews {

    private String review;
    private String author;
    private String reviewurl;

    public Reviews(){


    }

    public Reviews(String review, String author, String reviewurl){

        this.author = author;
        this.review = review;
        this.reviewurl = reviewurl;
    }

    public void setReviewurl(String reviewurl){
        this.reviewurl = reviewurl;
    }

    public String getReviewurl(){
        return reviewurl;
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
