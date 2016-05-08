/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Async_Add_Friends.java
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

import team.six.androidchat.Create_OR_Edit_Chatroom.Verify_Existing_Chatroom;
import team.six.androidchat.Activity_Main_Page;
import team.six.androidchat.Validating_User.Verify_Existing_User;

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
public class Async_Add_Friends  extends AsyncTask<String, Void, String>  {

    //Url of the associated php file which interacts with SQL
    URL url;

    //Context the will be initialized in the constructor
    private Context context;


    /**
     * Initalizes the context variable
     *
     * @param c Context passed in
     */
    public Async_Add_Friends(Context c) {
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
            Toast toast = Toast.makeText(context, "Room or user does not exist!", duration);
            toast.show();

        }

        //If the request went through
        else {

            //Print a toast
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Friend added!", duration);
            toast.show();

            //Go back to the mainn screen
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
        //Store roomname and user to add
        String roomname = arg0[0];
        String user_to_add = arg0[1];

        //Create instance of Verify_Existing_User and Verify_Existing_Chatroom to see if the user and chatroom exits
        Verify_Existing_User user_validate =new Verify_Existing_User();
        Verify_Existing_Chatroom room_validate = new Verify_Existing_Chatroom();

        //If the user does not exist or the chatroom does not exists return "true"
        if(!(user_validate.verifyUser(user_to_add)) || !(room_validate.verifyRoom(roomname))) {
            return("true");
        }

        //If the username and chatroom are valid
        else {
            try {
                //Create a link
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Add_Friends.php?username=" + URLEncoder.encode(user_to_add, "utf-8")+ "&Roomname=" + URLEncoder.encode(roomname, "utf-8");
                //Create the url
                url = new URL(urlLink);
               //Establish an HTTP conenction
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //Get input
                try
                {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                }finally
                {
                    urlConnection.disconnect();
                }
                //Disconnect
                urlConnection.disconnect();


            }
            catch (Exception e) {

            }
            //Return false if the request went through
            return "false";
        }


    }


}
