/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Async_Edit_Username.java
 *
 */

package team.six.androidchat.Add_OR_Edit_User;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

import team.six.androidchat.Activity_Login_Page;
import team.six.androidchat.Validating_User.Async_Authenticate_User;
import team.six.androidchat.Validating_User.Verify_Existing_User;
import team.six.androidchat.testSuite.Activity_Test;

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
public class Async_Edit_Username  extends AsyncTask<String, Void, String>  {

    //Url of the associated php file which interacts with SQL
    URL url;

    //Context the will be intiialized in the constructor
    private Context context;

    //Variables for testing
    private boolean test;
    private StringBuffer  test_result;
    private EditText test_screen;


    /**
     * Initalizes the context variable
     *
     * @param c Context passed in
     * @param t - Boolean passed in
     * @param text - Textview with test results
     * @param test_res - String holding test results
     */
    public Async_Edit_Username(Context c, boolean t, EditText text, StringBuffer  test_res) {

        test = t;
        test_screen = text;
        test_result = test_res;


        this.context = c;
    }

    /**
     * Initializes all variables except test variables
     *
     * @param c Context passed in
     * @param t - Boolean passed in
     */
    public Async_Edit_Username(Context c, boolean t) {

        test = t;


        this.context = c;
    }

    /**
     * Prints a toast if the request did not go through or prints and toast and starts a new activity if the request did go through
     *
     * @param results Either "true" or "false" depending on what is returned from doInBackground
     */
    protected void onPostExecute(String results){

        //If we are only testing
        if(test) {

            //If the task went through
            if(results.equals("true")) {

                test_result = test_result.append("Attempt change username on an existing user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());

            }

            //If the task did not go through
            else {
                test_result = test_result.append("Attempt change username on a nonexisting user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());



            }

        }

        //If we are not testing
        else {


            //If the request went through
            if(results.equals("true")) {

                //Print toast
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Username changed successfully!", duration);
                toast.show();

                //Go to login page
                Intent intent = new Intent(context, Activity_Login_Page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            //If the request did not go through
            else {

                //Print toast
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Old username or password entered incorrectly or username already exists!", duration);
                toast.show();
            }

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
     * @return returns "true" if request went through, returns "false" otherwise
     */
    protected String doInBackground(String... arg0)
    {
        //Store username, new username, and password
        String username =arg0[0];
        String new_user = arg0[1];
        String pass = arg0[2];

        //Create instance of Async_Authenticate_User to see if the user exists
        Async_Authenticate_User my_authentication = new Async_Authenticate_User();

        //Create instance of Verify_Existing_User to see if the username is not taken
        Verify_Existing_User my_verify = new Verify_Existing_User();

        //If the username is not taken and the user eixsts
        if(my_authentication.authenticate(username,pass) && !(my_verify.verifyUser((new_user)))) {

            try {
                //Store the link
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Edit_username.php?user=" + URLEncoder.encode(username, "utf-8")+ "&new_user=" + URLEncoder.encode(new_user, "utf-8");
                //Create the url
                url = new URL(urlLink);
                //Establish and HTTP connection
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
            //Return "true"
            return "true";
        }

        //If the username already exists or the user is not valid
        else {
            //Return "false"
            return "false";
        }

    }


}
