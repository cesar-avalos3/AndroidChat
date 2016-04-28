/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename ConnectActivity.java
 *
 */

//Deafult package which stores all java files
package team.six.androidchat;


//The AsyncTask class allows us to work in the background without disrupting the apps User Interface
import android.os.AsyncTask;

//Used to display HTML in a textview
import android.text.Html;

//Used for debuggging
import android.util.Log;

//For modifying a text view
import android.widget.TextView;


//Used for HTTP Requests
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.net.URI;

//Used for reading input from a file
import java.io.BufferedReader;
import java.io.InputStreamReader;



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
public class ConnectActivity extends AsyncTask<String, Void, String> {

    /**
     * Variable that will store an instance of the HttpClient Class
     */
    HttpClient client;

    /**
     * Define a instance variable of Type Action (where ACTION is the enum above). Either get or post request
     */
    ACTION state;

    /**
     * Variable that will store an instance of the HttpGet Class
     */
    HttpGet req;

    /**
     *Represens a Textview to be modified
     */
    TextView chatText;


    /**
     *Create a new enum for get and post requests
     * Two commonly used methods for a request-response between a client and server are: GET and POST.
     * GET - Requests data from a specified resource
     * POST - Submits data to be processed to a specified resource
     */
    public enum ACTION {GET, POST};


    /**
     * Instantiates instance variables <code>state</code>,<code>chatText</code>,<code>client</code>, and <code>req</code>
     *
     * @param chatText the TextView to be modified with all the messages from the chat
     * @param state the Action which the instance variable state will be set to (represents the HTTP request method, get or post)
     */
    public ConnectActivity(TextView chatText, ACTION state)
    {
        //Store the state to whatever was passed in as a parameter
        this.state = state;

        //Store the chatText to whatever was passed in as a parameter
        this.chatText = chatText;

        //Create an instace of the DefaulthttpClient class
        this.client = new DefaultHttpClient();

        //Create an instace of the HttpGet class
        this.req = new HttpGet();
    }


    /**
     * Sets the <code>chatText</code> to an HTML page with the string passed in as a parameter
     *
     * @param results the String to display in an HTML page
     */
    protected void onPostExecute(String results)
    {
        //Set the text of the chat text to the results passed in as a parameter in the form of an html page
        this.chatText.setText(Html.fromHtml(results));
    }



    /**
     * Submits an HTTP get request in the background of the app
     *
     * @param arg0 the String... represents an array of strings which can be of varying length [represents session_num in this case].
     * @return returns an empty string
     */
    protected String doInBackground(String... arg0)
    {
        //If the action is a get HTTP request
        if(state == ACTION.GET) {
            try {
                //Store the session number which is the first parameter passed in
                String sessionNumber = arg0[0];

                // A URI is a uniform resource identifier which allows us to access resources over the web
                //This method sets the URI for the HttpGet Object (req)
                req.setURI(new URI("http://people.eecs.ku.edu/~cavalosb/AndroidChat/AndroidChatReceive.php?session_number="+sessionNumber));

                //Executes the HTTP Request (using the execute method, the HttpGet Object (req) and the DefaultHttpClient Object client)
                HttpResponse res = client.execute(req);

                //Take the content from the Webpage as an input file to buffered reader
                BufferedReader input = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

                //StringBuffer is like a string, but can be modified
                StringBuffer stringB = new StringBuffer(" ");

                //Read lines
                String line = input.readLine();
                while (line != null) {
                    //Append new strings
                    stringB.append(line);
                    line = input.readLine();
                }
                //Close the input
                input.close();

                //Convert the buffered string to a string and return it
                return stringB.toString();
            }

            //Catch any errors
            catch (Exception e) {
                Log.e("Not able to connect: ", e.getMessage());
            }
        }

        //Return empty string as default
        return "";
    }



}
