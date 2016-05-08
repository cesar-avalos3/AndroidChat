package team.six.androidchat.Adding_User;


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

import team.six.androidchat.Main2Activity;
import team.six.androidchat.Validating_User.Authentication;
import team.six.androidchat.Validating_User.Verify_Existing_User;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Async_Edit_Username  extends AsyncTask<String, Void, String>  {
    URL url;
    private Context context;

    public Async_Edit_Username(Context c) {
        this.context = c;
    }


    public enum ACTION {GET, POST};

    protected void onPostExecute(String results){

        if(results.equals("true")) {


            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Username changed successfully!", duration);
            toast.show();


            //Create Intent Object
            //First parameter is a Context - this object is a subclass of Activity which is a subclass of Context
            //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
            Intent intent = new Intent(context, Main2Activity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
            context.startActivity(intent);
        }

        else {

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Old username or password entered incorrectly or username already exists!", duration);
            toast.show();
        }

    }

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0)
    {
        String username =arg0[0];
        String new_user = arg0[1];
        String pass = arg0[2];




        Authentication my_authentication = new Authentication();
        Verify_Existing_User my_verify = new Verify_Existing_User();
        if(my_authentication.authenticate(username,pass) && !(my_verify.verifyUser((new_user)))) {

            try {
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Edit_username.php?user=" + URLEncoder.encode(username, "utf-8")+ "&new_user=" + URLEncoder.encode(new_user, "utf-8");
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
            return "true";
        }

        else {
            return "false";
        }

    }


}
