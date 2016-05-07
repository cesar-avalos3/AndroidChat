/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename ConnectActivity.java
 *
 */

//Deafult package which stores all java files
package team.six.androidchat.Sending_Recieving;


//The AsyncTask class allows us to work in the background without disrupting the apps User Interface
import android.content.Context;
import android.os.AsyncTask;

//Used to display HTML in a textview
import android.text.Html;

//Used for debuggging
import android.util.Log;

//For modifying a text view
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.net.URLEncoder;
import java.util.ArrayList;

import team.six.androidchat.Validating_User.Authentication;


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
public class Get_Sessions extends AsyncTask<String, Void, String> {

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
    Spinner sessions;

    Context context;


    ArrayList<String> items = new ArrayList<>();


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
    public Get_Sessions(Spinner s, Context c)
    {


        this.context = c;
        //Store the chatText to whatever was passed in as a parameter
        this.sessions = s;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sessions.setAdapter(adapter);
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

            //Store the session number which is the first parameter passed in
            String user = arg0[0];

            Log.d("username",user);

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
            for(int i =0; i < line.length(); i++) {

                if(line.substring(i,i+1).equals("!")) {
                    Log.d("MY STRING:", my_string);
                    items.add(Html.fromHtml(my_string).toString());
                    my_string = "";
                }

                else {

                    my_string = my_string + line.substring(i,i+1);

                }
            }


            Log.d("Herefsklkjlsdfkjldfskjl","Here");
            //Close the input
            input.close();

            //Convert the buffered string to a string and return it

            return stringB.toString();
        }

        //Catch any errors
        catch (Exception e) {
            Log.e("Not able to connect: ", e.getMessage());
        }


        //Return empty string as default
        return "";
    }



}
