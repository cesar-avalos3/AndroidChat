package team.six.androidchat.Validating_User;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Cesar on 5/1/2016.
 */
public class Authentication extends AsyncTask<String, Void, String>{


    public boolean final_val;

    private EditText user;
    private EditText password;


    public Authentication(EditText u, EditText p)
    {
        this.user = u;
        this.password = p;

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
            Log.d("onPostExecute:", "true");
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


