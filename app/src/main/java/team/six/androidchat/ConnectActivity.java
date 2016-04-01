package team.six.androidchat;

import android.os.AsyncTask;
import android.util.Log;

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

    public enum ACTION {GET, POST};

    public void onPreExecute()
    {
    }

    protected void onPostExecute()
    {
    }

    protected String doInBackground(String... arg0)
    {
        if(state == ACTION.GET)
        {
            get("http://people.eecs.ku.edu/~cavalosb/AndroidChat/androidChat.php");
        }
        return "Ble";
    }

    public ConnectActivity()
    {
        //Default make GET the state
        state = ACTION.GET;
        client = new DefaultHttpClient();
        req = new HttpGet();

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

    public String get(String link)
    {
        try {
            url = new URL(link);
            con = url.openConnection();
            req.setURI(new URI(link));
            HttpResponse res = client.execute(req);
            BufferedReader input = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            StringBuffer stringB = new StringBuffer(" ");
            String line = "";
            while((line = input.readLine()) != null)
            {
                stringB.append(line);
                break;
            }
            input.close();
            return stringB.toString();
        }catch(Exception e)
        {
            Log.e("Error: ",e.getMessage());
        }
        return "This is not supposed to happen";
    }

}
