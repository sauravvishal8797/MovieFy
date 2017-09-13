package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.Data.MoviesProvider.LOG_TAG;
import static android.example.com.popularmovies.JavaClasses.Constants.ADULT;
import static android.example.com.popularmovies.JavaClasses.Constants.API_KEY;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.CAST_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.IMAGE_URL;
import static android.example.com.popularmovies.JavaClasses.Constants.MOVIE_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.ORIGINAL_TITLE;
import static android.example.com.popularmovies.JavaClasses.Constants.RATING;
import static android.example.com.popularmovies.JavaClasses.Constants.RELEASE_DATE;
import static android.example.com.popularmovies.JavaClasses.Constants.SYNOPSIS;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.com.popularmovies.Activity.DetailsView;
import android.example.com.popularmovies.Adapters.CastAdapter;
import android.example.com.popularmovies.Adapters.MoviesAdapter;
import android.example.com.popularmovies.JavaClasses.Cast;
import android.example.com.popularmovies.JavaClasses.Movies;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CastFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private ArrayList<Cast> cast;
    private CastAdapter castAdapter;



    public CastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        String id = bundle.getString("id");

        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_cast, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view35);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        new GetCastTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + id + "?api_key=" + API_KEY + "&append_to_response=credits");
        return root;

    }

    class GetCastTask extends AsyncTask<String, Void, ArrayList<Cast>> {

        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("LOading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override protected ArrayList<Cast> doInBackground(String... strings) {

            cast = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONObject credits = root.getJSONObject("credits");
                JSONArray array = credits.getJSONArray("cast");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("name");
                    String character = object.getString("character");
                    String casturl = object.getString("profile_path");
                    Cast c = new Cast();
                    c.setCast_name(name);
                    c.setCast_character("as " + character);
                    c.setCast_photo_url(BASE_URL + casturl);
                    cast.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return cast;
        }

        @Override protected void onPostExecute(final ArrayList<Cast> cast) {
            super.onPostExecute(cast);
            Log.i(LOG_TAG, "It works");
            if (cast != null) {
                Log.i(LOG_TAG, cast.get(2).getCast_name());
                progressDialog.dismiss();

                castAdapter = new CastAdapter(cast);

                recyclerView.setAdapter(castAdapter);
            }


            else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }

}
