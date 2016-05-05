package team.six.androidchat.Adding_User;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Add_Account_Async  extends AsyncTask<String, Void, String>  {
    URL url;
    URLConnection con;
    HttpClient client;
    ACTION state;
    HttpGet req;


    public enum ACTION {GET, POST};

    protected void onPostExecute(String results){}

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0)
    {
        String username = arg0[0];
        String password = arg0[1];
        try {
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatAddUser.php?user=" + URLEncoder.encode(username, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");
            url = new URL(urlLink);
            Log.d("Tag", urlLink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return "bnelh";
    }


}
