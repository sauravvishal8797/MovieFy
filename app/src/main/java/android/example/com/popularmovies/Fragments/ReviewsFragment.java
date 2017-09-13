package android.example.com.popularmovies.Fragments;


import static android.example.com.popularmovies.Data.MoviesProvider.LOG_TAG;
import static android.example.com.popularmovies.JavaClasses.Constants.BASE_URL;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.example.com.popularmovies.Adapters.CastAdapter;
import android.example.com.popularmovies.Adapters.ReviewsAdapter;
import android.example.com.popularmovies.JavaClasses.Cast;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.Reviews;
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
public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private ArrayList<Reviews> review;
    private ReviewsAdapter reviewsAdapter;



    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reviews, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view33);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return root;
    }

    class GetCastTask extends AsyncTask<String, Void, ArrayList<Reviews>> {

        private String Jsonresponse = " ";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("LOading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override protected ArrayList<Reviews> doInBackground(String... strings) {

            review = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONArray array = root.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String rev = object.getString("content");
                    String author = object.getString("author");
                    String revurl = object.getString("url");
                    Reviews r = new Reviews();
                    r.setAuthor(author);
                    r.setReview(rev);
                    r.setReviewurl(revurl);
                    review.add(r);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return review;
        }

        @Override protected void onPostExecute(final ArrayList<Reviews> review) {
            super.onPostExecute(review);
            Log.i(LOG_TAG, "It works");
            if (review != null) {
                Log.i(LOG_TAG, review.get(2).getReviewurl());
                progressDialog.dismiss();

                reviewsAdapter = new ReviewsAdapter(review);

                recyclerView.setAdapter(reviewsAdapter);
            }


            else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }
}
