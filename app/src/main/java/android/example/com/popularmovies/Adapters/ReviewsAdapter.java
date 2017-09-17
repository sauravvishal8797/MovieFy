package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import android.example.com.popularmovies.JavaClasses.Reviews;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by saurav on 13/9/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private ArrayList<Reviews> review;
    private final OnItemClickListener onItemClickListener;


    public ReviewsAdapter(ArrayList<Reviews> review, OnItemClickListener onItemClickListener){

        this.review = review;
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {

        void OnItemClick(int position);
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        Reviews r = review.get(position);
        String review = r.getReview();
        String aut = r.getAuthor();
        holder.mAuthor.setText(aut);
        holder.mReview.setText(review);

    }

    @Override public int getItemCount() {
        return review.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mReview;
        private TextView mAuthor;

        public ViewHolder(View itemView) {
            super(itemView);

            mReview = (TextView) itemView.findViewById(R.id.review);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View view) {

            int pos = getAdapterPosition();
            onItemClickListener.OnItemClick(pos);


        }
    }
}
