/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename Async_Recieve_Messages.java
 *
 */

//Deafult package which stores all java files
package team.six.androidchat.Sending_Recieving;


//The AsyncTask class allows us to work in the background without disrupting the apps User Interface
import android.content.Context;
import android.os.AsyncTask;

//Used to display HTML in a textview
import android.text.Html;


//For modifying a text view
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


//Used for HTTP Requests
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;

//Used for reading input from a file
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import team.six.androidchat.testSuite.Activity_Test;


/**
 * <p>
 * This class contains a method <code>doBackground</code> which overrides a method in the <code>AsyncTask</code> class.
 * </p>
 * <p>
 * This method allows us to preform operations in the background without interupting the apps user interface.
 * </p>
 * <p>
 * The <code>doBackground</code> method submits an HTTP get request. If the request runs through, the text of the page is returned in a string
 *
 * @author Cesar Avalos, Alec Knutsen
 * @see AsyncTask
 */
public class Async_Get_Sessions extends AsyncTask<String, Void, String> {

    /**
     * Variable that will store an instance of the HttpClient Class
     */
    HttpClient client;


    /**
     * Variable that will store an instance of the HttpGet Class
     */
    HttpGet req;

    /**
     *Represens a Textview to be modified
     */
    Spinner sessions;

    /**
     * Context to be added
     */
    Context context;

    //Variables for testing
    private boolean test;
    private StringBuffer  test_result;
    private EditText test_screen;


    /**
     * Arraylist to hold all sessions for a specified user
     */
    ArrayList<String> items = new ArrayList<>();






    /**
     * Instantiates instance variables <code>state</code>,<code>chatText</code>,<code>client</code>, and <code>req</code>
     *
     * @param s the Spinner to be modified with all chatrooms for the user
     * @param c - Context passed in as a a pameter
     * @param t - Boolean passed in
     */
    public Async_Get_Sessions(Spinner s, Context c, boolean t)
    {

        //Initialize all variables
        this.context = c;
        this.sessions = s;
        this.client = new DefaultHttpClient();
        this.req = new HttpGet();

        test = t;
    }

    /**
     * Alternate constructor for just initializing the boolean
     * @param t - Boolean passed in
     * @param text - Textview with test results
     * @param test_res - String holding test results
     */
    public Async_Get_Sessions(boolean t, EditText text, StringBuffer  test_res)
    {

        test = t;
        test_screen = text;
        test_result = test_res;

    }


    /**
     * Sets the spinner with the sessions
     *
     * @param results
     */
    protected void onPostExecute(String results)
    {

        //If we are only testing
        if(test) {


        }

        //If we are not testing
        else {


            //Create new ArrayAdapter (spinner) and set it
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sessions.setAdapter(adapter);

        }


    }

    /**
     * Submits an HTTP get request in the background of the app
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns an empty string
     */
    protected String doInBackground(String... arg0)
    {


        try {

            //Store the user which is the first parameter passed in
            String user = arg0[0];

            // A URI is a uniform resource identifier which allows us to access resources over the web
            //This method sets the URI for the HttpGet Object (req)
            req.setURI(new URI("http://people.eecs.ku.edu/~aknutsen/448_Project4/Get_Sessions.php?username="+ URLEncoder.encode(user, "utf-8")));

            //Executes the HTTP Request (using the execute method, the HttpGet Object (req) and the DefaultHttpClient Object client)
            HttpResponse res = client.execute(req);

            //Take the content from the Webpage as an input file to buffered reader
            BufferedReader input = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

            //StringBuffer is like a string, but can be modified
            StringBuffer stringB = new StringBuffer(" ");

            //Read lines
            String line = input.readLine();


            String my_string = "";
            //Sessions names are seperated by !. This for loop extracts the exclamation points to get the names
            for(int i =0; i < line.length(); i++) {

                if(line.substring(i,i+1).equals("!")) {

                    items.add(Html.fromHtml(my_string).toString());
                    my_string = "";
                }

                else {

                    my_string = my_string + line.substring(i,i+1);

                }
            }


            //Close the input
            input.close();

            //Convert the buffered string to a string and return it\
            return stringB.toString();
        }

        //Catch any errors
        catch (Exception e) {
        }


        //Return empty string as default
        return "";
    }



}
