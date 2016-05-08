/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Async_Create_Room.java
 *
 */


package team.six.androidchat.Create_OR_Edit_Chatroom;


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

import team.six.androidchat.Activity_Main_Page;
import team.six.androidchat.Create_OR_Edit_Chatroom.Verify_Existing_Chatroom;
import team.six.androidchat.Validating_User.Async_Authenticate_User;

/**
 * <p>
 * This class contains a method <code>doInBackground</code> which overrides a method in the <code>AsyncTask</code> class.
 * </p>
 * <p>
 * This method allows us to preform operations in the background without interupting the apps user interface.
 * </p>
 *  * <p>
 * This class contains a method <code>onPostExecute</code> which overrides a method in the <code>AsyncTask</code> class.
 * </p>
 * <p>
 * This method allows us to preform operations after doInBackground has completed.
 * </p>
 *
 * @author Alec Knutsen
 * @see AsyncTask
 */
public class Async_Create_Room  extends AsyncTask<String, Void, String>  {

    //Url of the associated php file which interacts with SQL
    URL url;

    //Context the will be intiialized in the constructor
    private Context context;


    /**
     * Initalizes the context variable
     *
     * @param c Context passed in
     */
    public Async_Create_Room(Context c) {
        this.context = c;
    }


    /**
     * Prints a toast if the request did not go through or prints and toast and starts a new activity if the request did go through
     *
     * @param results Either "true" or "false" depending on what is returned from doInBackground
     */
    protected void onPostExecute(String results){

        //If the request does not go through, print a toast
        if(results.equals("true")) {

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Room already exists!", duration);
            toast.show();

        }

        //If the request goes through
        else {

            //Toast
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Room Created!", duration);
            toast.show();

            //Go back to main screen
            Intent intent = new Intent(context, Activity_Main_Page.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }

    /**
     *Implementation of AsyncTask Interface method
     *
     */
    protected void onPreExecute(){}

    /**
     * Submits an HTTP get request in the background of the app
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns "true" if request did not go through, returns "false" otherwise
     */
    protected String doInBackground(String... arg0)
    {
        //Store the room to the parameter passed in
        String roomname = arg0[0];
        //Store the user as the current user logged in
        String author = Async_Authenticate_User.getUsername();

        try {
            //Create an instance of the Verify_Existing_Chatrom to see if the room already exists
            Verify_Existing_Chatroom verify = new Verify_Existing_Chatroom();

            //If the room exists return "true"
            if(verify.verifyRoom(roomname)) {
                return "true";
            }

            //If the room does not exist
            else {
                //Establish a conenction
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Create_Room.php?username=" + URLEncoder.encode(author, "utf-8")+ "&Roomname=" + URLEncoder.encode(roomname, "utf-8");
                //Create a url
                url = new URL(urlLink);
                //Establish connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //Get input
                try
                {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                }finally
                {
                    urlConnection.disconnect();
                }
                //Return false if does not go through
                return("false");
            }


        }
        catch (Exception e) {

        }
        //If try does not go through
        return "false";
    }


}
