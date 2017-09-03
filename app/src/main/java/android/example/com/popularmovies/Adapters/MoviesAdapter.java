package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurav on 21/8/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.Viewholder> {

    private ArrayList<Movies> moviesArrayList;
    private OnItemClickListener mItemClickListener;

    public MoviesAdapter(ArrayList<Movies> movies, OnItemClickListener onItemClickListener){

        moviesArrayList = movies;
        mItemClickListener = onItemClickListener;


    }
    public interface OnItemClickListener {

        void OnItemClick(int position);
    }


    @Override public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);
        return new Viewholder(view);

    }

    @Override public void onBindViewHolder(Viewholder holder, int position) {

        Movies m = moviesArrayList.get(position);
        holder.name.setText(m.getName());
        Picasso.with(holder.itemView.getContext()).load(moviesArrayList.get(position).getUrl()).placeholder(R
                .drawable.birthdaycard)
                .into(holder
                .imageView);


    }


    @Override public int getItemCount() {
        return moviesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private ImageView imageView;

        public Viewholder(View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            imageView  = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }


        @Override public void onClick(View view) {
            int pos = getAdapterPosition();
            mItemClickListener.OnItemClick(pos);
        }
    }
}
