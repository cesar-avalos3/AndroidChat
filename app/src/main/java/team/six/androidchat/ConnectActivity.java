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
 * Created by Cesar on 4/1/2016.
 */
public class ConnectActivity extends AsyncTask<String, Void, String> {

    URL url;
    URLConnection con;
    HttpClient client;
    ACTION state;
    HttpGet req;

    TextView chatText;
    Context context;

    public enum ACTION {GET, POST};

    public void onPreExecute()
    {

    }

    protected void onPostExecute(String results)
    {
        this.chatText.setText(Html.fromHtml(results));
    }

    protected String doInBackground(String... arg0)
    {
        if(state == ACTION.GET) {
            try {
                String sessionNumber = arg0[0];
                req.setURI(new URI("http://people.eecs.ku.edu/~cavalosb/AndroidChat/AndroidChatReceive.php?session_number="+sessionNumber));
                HttpResponse res = client.execute(req);
                BufferedReader input = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

                StringBuffer stringB = new StringBuffer(" ");
                String line = input.readLine();
                while (line != null) {
                    stringB.append(line);
                    line = input.readLine();
                }
                input.close();
                return stringB.toString();
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

    public ConnectActivity(TextView chatText, ACTION state)
    {
        //Default make GET the state
        this.state = state;
        //this.context = context;
        this.chatText = chatText;
        this.client = new DefaultHttpClient();
        this.req = new HttpGet();
    }

    public void toggleState()
    {
        if(state == ACTION.GET)
        {
            state = ACTION.POST;
        }
        else
        {
            state = ACTION.GET;
        }
    }

}
