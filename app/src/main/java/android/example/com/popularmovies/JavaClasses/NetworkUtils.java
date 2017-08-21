package android.example.com.popularmovies.JavaClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by saurav on 21/8/17.
 */

public class NetworkUtils {


    public static URL CreateUrl(String url){

        URL url1 = null;

        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url1;
    }

    public static String MakeHttpRequest(URL url){

        String JsonResponse = " ";

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                JsonResponse = ReadFromInputStream(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return JsonResponse;


    }

    public static String ReadFromInputStream(InputStream inputStream){

        StringBuilder builder = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while(line != null){
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();
    }
}
