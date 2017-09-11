package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.example.com.popularmovies.JavaClasses.Trailers;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurav on 10/9/17.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private ArrayList<Trailers> trailerses;
    private final OnItemClickListener onItemClickListener;


    public TrailersAdapter(ArrayList<Trailers> trailerses, OnItemClickListener onItemClickListener){

        this.trailerses = trailerses;
        this.onItemClickListener = onItemClickListener;


    }

    public interface OnItemClickListener {

        void OnItemClick(int position);
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem2, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        Trailers t = trailerses.get(position);
        Picasso.with(holder.itemView.getContext()).load(t.getUrl()).placeholder(R
                .drawable.birthdaycard)
                .into(holder
                        .imageView);
        holder.textView.setText(t.getTrailer());



    }

    @Override public int getItemCount() {
        return trailerses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            textView = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View view) {
            int p = getAdapterPosition();
            onItemClickListener.OnItemClick(p);
        }
    }
}
