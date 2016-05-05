package team.six.androidchat.Validating_User;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import team.six.androidchat.Main2Activity;

/**
 * Created by Cesar on 5/1/2016.
 */
public class Authentication extends AsyncTask<String, Void, String>{


    public boolean final_val;

    private EditText user;
    private EditText password;

    private Context context;


    public Authentication(EditText u, EditText p, Context c)
    {
        this.user = u;
        this.password = p;
        this.context = c;

    }

    public static boolean authenticate(String user, String pass)
    {
        try {

            String password = pass;
            String userName = user;
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatVerifyUser.php?username=" + URLEncoder.encode(userName, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");

            URL url = new URL(urlLink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine ="false";
            while( bf.readLine() != null) {
                inputLine ="true";
            }



            if(inputLine.equals("true")) {
                Log.d("Input Line:", inputLine);
                return true;
            }
            else
            {
                Log.d("Input Line:", inputLine);
                return false;
            }
        }catch (Exception e)
        {

            Log.d("This is trooo", e.getMessage());
        }
        return false;
    }

    protected String doInBackground(String... arg0) {
        if(authenticate(arg0[0],arg0[1]))
        {
            Log.d("doInBackGround:", "true");
            return "true";
        }
        else
        {
            Log.d("doInBackGround:", "false");
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
        if(results.equals("true"))
        {
            //Create Intent Object
            //First parameter is a Context - this object is a subclass of Activity which is a subclass of Context
            //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
            Intent intent = new Intent(context, Main2Activity.class);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
            context.startActivity(intent);

            final_val = true;

        }
        else
        {
            Log.d("onPostExecute:", "false");
            final_val = false;
        }
        login();

    }

    public boolean getFinalVal() {

        Log.d("getFinalVal:", final_val+"");
        return(final_val);
    }

    public void login() {



        if(final_val) {

            //Do Something here
            Log.d("here","Validated");


        }

        else {

            //Do something here
            Log.d("here","Not validated");
        }

    }



}


