package android.example.com.popularmovies.Adapters;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.example.com.popularmovies.JavaClasses.Cast;
import android.example.com.popularmovies.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurav on 13/9/17.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private ArrayList<Cast> cast;


    public CastAdapter(ArrayList<Cast> cast){

        this.cast = cast;
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        Cast c = cast.get(position);

        holder.name.setText(c.getCast_name());
        holder.character.setText(c.getCast_character());
        Picasso.with(holder.itemView.getContext()).load(c.getCast_photo_url()).placeholder(R
                .drawable.birthdaycard)
                .into(holder
                        .cast_photo);


    }

    @Override public int getItemCount() {
        return cast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView character;
        private ImageView cast_photo;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.cast_namereal);
            character = (TextView) itemView.findViewById(R.id.character_played);
            cast_photo = (ImageView) itemView.findViewById(R.id.cast_image);




        }
    }
}
