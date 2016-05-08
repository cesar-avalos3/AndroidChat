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

import team.six.androidchat.Create_New_Chatroom.Verify_Existing_Chatroom;
import team.six.androidchat.Main2Activity;
import team.six.androidchat.Validating_User.Authentication;
import team.six.androidchat.Validating_User.Verify_Existing_User;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Async_Add_Friends  extends AsyncTask<String, Void, String>  {
    URL url;


    private Context context;

    public Async_Add_Friends(Context c) {
        this.context = c;
    }

    public enum ACTION {GET, POST};

    protected void onPostExecute(String results){

        if(results.equals("true")) {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Room or user does not exist!", duration);
            toast.show();
        }

        else {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Friend added!", duration);
            toast.show();

            Intent intent = new Intent(context, Main2Activity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
            context.startActivity(intent);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)

        }
    }

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0)
    {
        String roomname = arg0[0];
        String author = arg0[1];

        Verify_Existing_User user_validate =new Verify_Existing_User();
        Verify_Existing_Chatroom room_validate = new Verify_Existing_Chatroom();


        if(!(user_validate.verifyUser(author)) || !(room_validate.verifyRoom(roomname))) {
            return("true");
        }

        else {
            try {
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Add_Friends.php?username=" + URLEncoder.encode(author, "utf-8")+ "&Roomname=" + URLEncoder.encode(roomname, "utf-8");
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
