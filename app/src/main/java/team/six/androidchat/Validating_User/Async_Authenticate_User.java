/**
 *
 * @author  Alec Knutsen, Cesar Avalos
 * @date 5/8/2016
 * @filename Async_Authenticate_User.java
 *
 */


package team.six.androidchat.Validating_User;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import team.six.androidchat.Activity_Main_Page;
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
 * @author Alec Knutsen, Cesar Avalos
 * @see AsyncTask
 */
public class Async_Authenticate_User extends AsyncTask<String, Void, String>{


    //Store all class variables
    private EditText user;
    private EditText password;
    private static String username = "";
    private Context context;


    //Variables for testing
    private boolean test;
    private StringBuffer  test_result;
    private EditText test_screen;

    /***
     * Constructor that initialize the context, username, password, and test boolean
     * @param u - Username
     * @param p - Password
     * @param c - Context
     * @param t - Boolean passed in
     */
    public Async_Authenticate_User(EditText u, EditText p, Context c, boolean t)
    {
        this.user = u;
        this.password = p;
        this.context = c;

        test = t;

    }

    /**
     * Initialize testing boolean
     * @param t - Boolean passed in
     * @param text - Textview with test results
     * @param test_res - String holding test results
     */
    public Async_Authenticate_User(boolean t, EditText text, StringBuffer  test_res)
    {

        test = t;
        test_screen = text;
        test_result = test_res;

    }

    /**
     * Empty contructor
     */
    public Async_Authenticate_User()
    {


    }

    /**
     * Verifies user
     * @param user - User to verify
     * @param pass - Password of user to verify
     * @return - boolean - Returns true if the user and password exist, false otherwise
     */
    public static boolean authenticate(String user, String pass)
    {
        try {

            //Store username and password
            String password = pass;
            String userName = user;

            //Get url
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/AndroidChatVerifyUser.php?username=" + URLEncoder.encode(userName, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8");
            //Create new url object
            URL url = new URL(urlLink);
            //Establish connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //Create buffered reader
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine ="false";
            //If the user is valid, the file will read "true"
            while( bf.readLine() != null) {
                inputLine ="true";
            }

            //If the user is valid, return true
            if(inputLine.equals("true")) {

                return true;
            }

            //If the user is not vali return false
            else
            {

                return false;
            }
        }
        catch (Exception e)
        {

        }

        //Return false if request does not go through
        return false;
    }

    /**
     * Calls authenticate method
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns an empty string
     */
    protected String doInBackground(String... arg0) {

        //If the user is valid, return the string "true"
        if(authenticate(arg0[0],arg0[1]))
        {

            return "true";
        }

        //If the user is not valid, return the string "false"
        else
        {

            return "false";
        }
    }

    /**
     * Implement interface method
     */
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * Starts new activity if user is authenticated, otherwise toast an error
     * @param results - String returned from doInBackground
     */
    protected void onPostExecute(String results) {


        //If we are only testing
        if(test) {

            //If the task went through
            if(results.equals("true")) {

                test_result = test_result.append("Attempt Login with an existing user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());

            }

            //If the task did not go through
            else {
                test_result.append("Attempt Login with a non existing user:\n" +
                        " Passed\n");
                test_screen.setText(test_result.toString());



            }

        }

        //If we are not testing
        else {

            //If the user is authenticated
            if (results.equals("true")) {
                //Store the username
                username = user.getText().toString();

                //Start the main screen
                Intent intent = new Intent(context, Activity_Main_Page.class);
                context.startActivity(intent);

                //Print a welcome message
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "WELCOME " + username + "!", duration);
                toast.show();


            }

            //If the user is not authenticated
            else {
                //Print an error
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Invalid Username or Password!", duration);
                toast.show();

            }

        }

    }

    /**
     * Sets the static username
     * @param u - Sets the username class variable to the value passed in
     */
    public static void setUsername(String u) {

        username = u;

    }

    /**
     * Returns the class variable username
     * @return
     */
    public static String getUsername() {

        return(username);
    }



}


