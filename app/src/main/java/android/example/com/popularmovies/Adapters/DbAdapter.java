package android.example.com.popularmovies.Adapters;

import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_NAME;
import static android.example.com.popularmovies.Data.MoviesContract.MovieEntry.COLOUMN_POSTERS;

import com.squareup.picasso.Picasso;

import android.database.Cursor;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurav on 10/9/17.
 */

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.ViewHolder> {



    private Cursor cursor;
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();


    public DbAdapter(Cursor cursor){

        this.cursor = cursor;


    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        if(!cursor.moveToPosition(position)){
            return;
        }
        else{
            String name = cursor.getString(cursor.getColumnIndex(COLOUMN_NAME));
            String url = cursor.getString(cursor.getColumnIndex(COLOUMN_POSTERS));
            Log.i(LOG_TAG, name);
            holder.name.setText(name);
            Picasso.with(holder.itemView.getContext()).load(url).placeholder(R
                    .drawable.birthdaycard)
                    .into(holder
                            .imageView);
        }




    }

    @Override public int getItemCount() {
        return cursor.getCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            imageView  = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
