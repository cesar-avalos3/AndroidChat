package team.six.androidchat;

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

/**
 * Created by Yarden on 4/5/2016.
 */
public class SendingMessage  extends AsyncTask<String, Void, String>  {
    URL url;
    URLConnection con;
    HttpClient client;
    ACTION state;
    HttpGet req;

    TextView chatText;
    Context context;

    public enum ACTION {GET, POST};

    protected String doInBackground(String... arg0)
    {
        if(state == ACTION.GET) {
            try {
                String sessionNumber = arg0[0];
                String message_content = arg0[1];
                String message_author = arg0[2];
                req.setURI(new URI("http://people.eecs.ku.edu/~cavalosb/AndroidChat/AndroidChatTransmit.php?session_number="+sessionNumber+"&message_content="+message_content+"&message_author="+message_author));
                HttpResponse res = client.execute(req);
                System.out.println("here");



            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
        }
        else
        {
            // Post things
        }
        return "This is not supposed to happen";
    }

    public SendingMessage(TextView chatText, ACTION state)
    {
        //Default make GET the state
        this.state = state;
        //this.context = context;
        this.chatText = chatText;
        this.client = new DefaultHttpClient();
        this.req = new HttpGet();
    }
}
