package team.six.androidchat.Validating_User;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import team.six.androidchat.AsyncResponse;

/**
 * Created by Cesar on 5/1/2016.
 */
public class Authentication extends AsyncTask<String, Void, String>{


    private boolean final_val;


    public Authentication()
    {

    }

    public static boolean authenticate(String user, String pass)
    {
        try {

           
            String password = pass;
            String userName = user;
            URL url = new URL("http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatVerifyUser.php??username="+ URLEncoder.encode(userName, "utf-8")+"&password="+URLEncoder.encode(password, "utf-8"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while( (inputLine = bf.readLine()) != null) {
                res.append(inputLine);
            }

            //Close the input
            if(res.toString() == "true") {
                Log.v("This is trooo", "Troooo I say");
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {

        }
        return false;
    }

    protected String doInBackground(String... arg0) {
        if(authenticate(arg0[0],arg0[1]))
        {
            return "true";
        }
        else
        {
            return "false";
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    //Good lord this is a terrible way to do it
    protected void onPostExecute(String results)
    {
        boolean temp;
        if(results == "true")
        {
            final_val = true;

        }
        else
        {
            final_val = false;
        }

    }

    public boolean getFinalVal() {
        return(final_val);
    }
}
