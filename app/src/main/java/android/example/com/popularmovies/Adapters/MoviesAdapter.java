package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.OnItemClickListener;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurav on 21/8/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.Viewholder> {

    private ArrayList<Movies> moviesArrayList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public MoviesAdapter(ArrayList<Movies> movies, Context context, OnItemClickListener onItemClickListener){

        moviesArrayList = movies;
        mContext = context;
        mItemClickListener = onItemClickListener;


    }


    @Override public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);
        return new Viewholder(view);

    }

    @Override public void onBindViewHolder(Viewholder holder, int position, OnItemClickListener Listener) {

        Movies m = moviesArrayList.get(position);
        holder.name.setText(m.getName());
        Picasso.with(holder.itemView.getContext()).load(moviesArrayList.get(position).getUrl()).placeholder(R
                .drawable.birthdaycard)
                .resize(120,
                        60).into(holder
                .imageView);
        holder.bind(m, Listener, holder.itemView);



    }

    public void bind(final Movies m, final OnItemClickListener listener, View view){

        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                listener.OnItemClick(m);
            }
        });

    }

    @Override public int getItemCount() {
        return moviesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView imageView;

        public Viewholder(View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            imageView  = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
