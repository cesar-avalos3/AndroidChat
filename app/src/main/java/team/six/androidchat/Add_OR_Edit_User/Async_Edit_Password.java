/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Async_Edit_Password.java
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
public class Async_Edit_Password  extends AsyncTask<String, Void, String>  {

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
    public Async_Edit_Password(Context c, boolean t, EditText text, StringBuffer  test_res) {

        test = t;
        test_screen = text;
        test_result = test_res;

        this.context = c;
    }

    /**
     * Initalizes all variables except test variables
     *
     * @param c Context passed in
     * @param t - Boolean passed in
     */
    public Async_Edit_Password(Context c, boolean t) {

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

                test_result = test_result.append("Attempt change password on a valid user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());

            }

            //If the task did not go through
            else {
                test_result = test_result.append("Attempt change password on a nonexisting user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());


            }

        }

        //If we are not testing
        else {


            //If the password was changed successfully
            if(results.equals("true")) {

                //Print toast
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Password changed successfully!", duration);
                toast.show();


                //Start new activity
                Intent intent = new Intent(context, Activity_Login_Page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }


            //If the request does not go through print a toast
            else {

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Username or old password entered incorrectly!", duration);
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
        //Store username, new password, and old password
        String username =arg0[0];
        String password = arg0[1];
        String old_pass = arg0[2];

        //Create new instance of Async_Authenticate_User to user the authenticate method to see if the user exists
        Async_Authenticate_User my_authentication = new Async_Authenticate_User();

        //If the user exists
        if(my_authentication.authenticate(username,old_pass)) {

            try {
                //Store the url
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Edit_Password.php?user=" + URLEncoder.encode(username, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");
                //Create the url
                url = new URL(urlLink);
               //Create HTTP connection
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

        //Return "false" if the username or password is wrong
        else {
            return "false";
        }

    }


}
