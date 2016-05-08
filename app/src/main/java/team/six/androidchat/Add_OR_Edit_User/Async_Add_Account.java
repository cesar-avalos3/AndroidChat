package team.six.androidchat.Add_OR_Edit_User;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

import team.six.androidchat.Activity_Login_Page;
import team.six.androidchat.Activity_Main_Page;
import team.six.androidchat.Validating_User.Verify_Existing_User;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Async_Add_Account extends AsyncTask<String, Void, String>  {
    URL url;

    private Context context;

    public Async_Add_Account(Context c) {
        this.context = c;
    }


    public enum ACTION {GET, POST};

    protected void onPostExecute(String results){

        if(results.equals("true")) {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Username already exists!", duration);
            toast.show();
        }

        else {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "User created!", duration);
            toast.show();

            Intent intent = new Intent(context, Activity_Login_Page.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
            context.startActivity(intent);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)

        }


    }

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0)
    {
        String username = arg0[0];
        String password = arg0[1];

        Verify_Existing_User user_validate =new Verify_Existing_User();

        if(user_validate.verifyUser(username)) {
            return "true";
        }

        else {

            try {
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatAddUser.php?user=" + URLEncoder.encode(username, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");
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
            return "false";
        }


    }


}
