package android.example.com.popularmovies.Activity;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.example.com.popularmovies.Adapters.TrailersAdapter;
import android.example.com.popularmovies.JavaClasses.NetworkUtils;
import android.example.com.popularmovies.JavaClasses.Trailers;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.example.com.popularmovies.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private ArrayList<Trailers> movies;
    private int i;
    private RecyclerView recyclerView;
    private static final String LOG_TAG = Main3Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haori_recycle);
        Intent intent = getIntent();
        String ide = intent.getStringExtra("id");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view33);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        new GetMoviesTask().execute("https://api.themoviedb" +
                ".org/3/movie/" + ide + "/videos?api_key=e2a51d701ca40655dbb7d5156ff2f42e&language=en-US");
    }

    class GetMoviesTask extends AsyncTask<String, Void, ArrayList<Trailers>> {

        private String Jsonresponse = " ";


        @Override protected ArrayList<Trailers> doInBackground(String... strings) {

            movies = new ArrayList<>();
            String url = strings[0];
            URL url1 = NetworkUtils.CreateUrl(url);
            Jsonresponse = NetworkUtils.MakeHttpRequest(url1);
            try {
                JSONObject root = new JSONObject(Jsonresponse);
                JSONArray array = root.getJSONArray("results");
                for (i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("name");
                    String key = object.getString("key");
                    Trailers trailers = new Trailers();
                    trailers.setTrailer(name);
                    trailers.setUrl("https://img.youtube.com/vi/" + key + "/" + i + ".jpg");
                    movies.add(trailers);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override protected void onPostExecute(final ArrayList<Trailers> movies) {
            super.onPostExecute(movies);
            Log.i(LOG_TAG, "It works");
            if (movies != null) {
                TrailersAdapter trailersAdapter = new TrailersAdapter(movies,
                        new TrailersAdapter.OnItemClickListener() {
                            @Override public void OnItemClick(int position) {
                                Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
                            }
                        });

                recyclerView.setAdapter(trailersAdapter);
            }





            else {
                Log.e(LOG_TAG, "Null arraylist returned");
            }
        }

    }
}
