package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by saurav on 21/8/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.Viewholder> {

    private ArrayList<Movies> moviesArrayList;
    private Context mContext;

    public MoviesAdapter(ArrayList<Movies> movies, Context context){

        moviesArrayList = movies;
        mContext = context;

    }


    @Override public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(Viewholder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public Viewholder(View itemView) {
            super(itemView);
        }
    }
}
