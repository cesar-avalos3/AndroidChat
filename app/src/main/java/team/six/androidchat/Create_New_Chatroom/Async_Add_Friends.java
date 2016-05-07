package team.six.androidchat.Adding_User;


import android.os.AsyncTask;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

import team.six.androidchat.Validating_User.Authentication;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Async_Add_Friends  extends AsyncTask<String, Void, String>  {
    URL url;



    public enum ACTION {GET, POST};

    protected void onPostExecute(String results){}

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0)
    {
        String roomname = arg0[0];
        String author = arg0[1];

        try {
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Add_Friends.php?username=" + URLEncoder.encode(author, "utf-8")+ "&Roomname=" + URLEncoder.encode(roomname, "utf-8");
            url = new URL(urlLink);
            Log.d("Tag", urlLink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            }finally
            {
                urlConnection.disconnect();
            }
            System.out.println("here");

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return "bnelh";
    }


}
