/**
 *
 * @author Yarden Tamir
 * @date  4/3/2016
 * @filename Async_Send_Messages.java
 *
 */


package team.six.androidchat.Sending_Recieving;


import android.os.AsyncTask;

import android.util.Log;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;

import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Yarden on 4/5/2016.
 */
public class Async_Send_Messages extends AsyncTask<String, Void, String>  {

    //Store all class variables
    URL url;
    HttpClient client;
    HttpGet req;

    //Variables for testing
    private boolean test;
    private StringBuffer  test_result;
    private EditText test_screen;


    /**
     * Constructor to initialize class variables
     * @param t - Boolean passed in
     * @param text - Textview with test results
     * @param test_res - String holding test results
     *
     */
    public Async_Send_Messages(boolean t, EditText text, StringBuffer  test_res)
    {

        this.client = new DefaultHttpClient();
        this.req = new HttpGet();


        test = t;
        test_screen = text;
        test_result = test_res;

    }

    /**
     * Constructor to initialize class variables except test variables
     * @param t - Boolean passed in
     *
     */
    public Async_Send_Messages(boolean t)
    {

        this.client = new DefaultHttpClient();
        this.req = new HttpGet();


        test = t;


    }


    /**
     *Create a new enum for get and post requests
     * Two commonly used methods for a request-response between a client and server are: GET and POST.
     * GET - Requests data from a specified resource
     * POST - Submits data to be processed to a specified resource
     */
    public enum ACTION {GET, POST};

    /**
     * Implementation of interfacce method
     * @param results
     */
    protected void onPostExecute(String results){

        //If we are only testing
        if(test) {

        }

        //If we are not testing
        else {

        }
    }

    /**
     * Implementation of interfacce method
     *
     */
    protected void onPreExecute(){}

    /**
     * Submits an HTTP get request in the background of the app
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns an empty string
     */
    protected String doInBackground(String... arg0)
    {
        //Stores session number, message, and author
        String sessionNumber = arg0[0];
        String message_content = arg0[1];
        String message_author = arg0[2];

        try {
            //Store url
            String urlLink = "http://people.eecs.ku.edu/~cavalosb/AndroidChat/AndroidChatTransmit.php?session_number=" + URLEncoder.encode(sessionNumber, "utf-8")+ "&message_content=" + URLEncoder.encode(message_content, "utf-8") + "&message_author=" + URLEncoder.encode(message_author, "utf-8");
            //Establish url connection
            url = new URL(urlLink);
            //Establish http connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //Get results
            try
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            }
            finally
            {
                //Disconnect
                urlConnection.disconnect();
            }

        }
        catch (Exception e) {

        }
        return "";
    }


}
