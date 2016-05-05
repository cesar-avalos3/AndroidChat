package team.six.androidchat.Sending_Recieving;


import android.os.AsyncTask;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;

import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Yarden on 4/5/2016.
 */
public class SendingMessage  extends AsyncTask<String, Void, String>  {
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
        String sessionNumber = arg0[0];
        String message_content = arg0[1];
        String message_author = arg0[2];
        try {
            String urlLink = "http://people.eecs.ku.edu/~cavalosb/AndroidChat/AndroidChatTransmit.php?session_number=" + URLEncoder.encode(sessionNumber, "utf-8")+ "&message_content=" + URLEncoder.encode(message_content, "utf-8") + "&message_author=" + URLEncoder.encode(message_author, "utf-8");
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

    public SendingMessage()
    {
        //Default make GET the state
        this.state = state;
        //this.context = context;
        this.client = new DefaultHttpClient();
        this.req = new HttpGet();
    }
}
