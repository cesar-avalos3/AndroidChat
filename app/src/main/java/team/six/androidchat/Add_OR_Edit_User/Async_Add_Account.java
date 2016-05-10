/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Async_Add_Account.java
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
import team.six.androidchat.Activity_Main_Page;
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
public class Async_Add_Account extends AsyncTask<String, Void, String>  {

    //Url of the associated php file which interacts with SQL
    URL url;

    //Variables for testing
    private boolean test;
    private StringBuffer test_result;
    private EditText test_screen;

    //Context the will be intiialized in the constructor
    private Context context;

    /**
     * Initalizes the context variable
     *
     * @param c Context passed in
     * @param t - Boolean passed in
     * @param text - Textview with test results
     * @param test_res - String holding test results
     */
    public Async_Add_Account(Context c, boolean t, EditText text, StringBuffer test_res) {

        test = t;
        test_screen = text;
        test_result = test_res;


        this.context = c;
    }


    /**
     * Inialize all varaibles except test variables
     *
     * @param c Context passed in
     * @param t - Boolean passed in
     */
    public Async_Add_Account(Context c, boolean t) {

        test = t;


        this.context = c;
    }


    /**
     * Prints a toast if the request did not go through or prints and toast and starts a new activity if the request did go through
     *
     * @param results Either "true" or "false" depending on what is returned from doInBackground
     */
    @Override
    protected void onPostExecute(String results){

        //If we are only testing
        if(test) {

            //If the task did not go through
            if(results.equals("true")) {

                test_result = test_result.append("Attempt Create account on already existing user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());

            }

            //If the task went through
            else {
                test_result = test_result.append("Attempt Create account on a nonexisting user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());



            }

        }

        //If we are not testing
        else {

            //If the request did not go through (the user already exists), print a toast
            if(results.equals("true")) {

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Username already exists!", duration);
                toast.show();
            }

            //If the request went through and the database was updated, print a toast and start a new activity
            else {

                //Print toast
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "User created!", duration);
                toast.show();


                //Start activity
                Intent intent = new Intent(context, Activity_Login_Page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        }


    }

    /**
     *Implementation of AsyncTask Interface method
     *
     */
    @Override
    protected void onPreExecute(){}

    /**
     * Submits an HTTP get request in the background of the app
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns "true" if request did not go through, returns "false" otherwise
     */
    @Override
    protected String doInBackground(String... arg0)
    {
        //Store username and password passed in as a parameter
        String username = arg0[0];
        String password = arg0[1];

        //Create an instance of the Verify_Existing_User class so we can use the user_validate method to see if the username already exists
        Verify_Existing_User user_validate =new Verify_Existing_User();

        //If the username already exists
        if(user_validate.verifyUser(username)) {
            return "true";
        }

        //If the username does not exists
        else {

            try {
                //Store the link
                String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatAddUser.php?user=" + URLEncoder.encode(username, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");
                //Create a new url
                url = new URL(urlLink);
                //Establish http connection
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
            //Return false
            return "false";
        }


    }


}
