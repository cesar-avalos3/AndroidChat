package team.six.androidchat.Adding_User;


import android.os.AsyncTask;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Add_Account_Async  extends AsyncTask<String, Void, String>  {
    URL url;



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
