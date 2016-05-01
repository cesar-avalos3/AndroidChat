package team.six.androidchat;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Cesar on 5/1/2016.
 */
public class Authentication extends AsyncTask<String, Void, String>{
    public AsyncResponse result = null;
    boolean authBool;

    public Authentication(AsyncResponse asyncResponse)
    {
        this.result = asyncResponse;
    }

    public static boolean authenticate()
    {
        try {
            String password = "Vote for pedro";
            String userName = "Pedro";
            URL url = new URL("http://people.eecs.ku.edu/~cavalosb/AndroidChatProject4/login.php?name="+ URLEncoder.encode(userName, "utf-8")+"&password="+URLEncoder.encode(password, "utf-8"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while( (inputLine = bf.readLine()) != null) {
                res.append(inputLine);
            }
            Log.v("JSON:", res.toString());
            JSONObject jsonObject = new JSONObject(res.toString());
            //Close the input
            if(jsonObject.getInt("success") == 1) {
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
       if(authenticate())
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
            temp = true;
        }
        else
        {
            temp = false;
        }
        Log.v("The results are: ", ""+results);
        result.result(temp);
        }
}
