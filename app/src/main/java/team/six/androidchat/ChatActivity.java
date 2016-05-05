/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename Message.java
 *
 */

//Default package which stores java files
package team.six.androidchat;

//Deafult import for Bundle used in onCreate
import android.os.Bundle;

//Handler class so that we can auto refresh the session rooms messages page
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;

//Base class for any activity
import android.support.v7.app.AppCompatActivity;

//Defaults imports for menus
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;

//Imports for textviews and editable texts
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import team.six.androidchat.Validating_User.Authentication;


/**
 * <p>
 * This is the mainActivity for the app. It displays all messages for a specified session number and allows authors to enter messages in the specified session.
 * </p>
 *
 * @author Cesar Avalos, Alec Knutsen
 * @see AppCompatActivity
 */
public class ChatActivity extends AppCompatActivity implements AsyncResponse{

    /**
     * Instane variable associated with displaying all messages in a specified session
     */
    private TextView textView;

    /**
     * Instane variable associated with displaying the session number
     */
    private EditText sessionNumber;

    /**
     * Instane variable associated with allowing the user to input a message
     */
    private EditText inputTextstring;

    /**
     * Instane variable associated with allowing the user to put in their name
     */
    private EditText authorText;

    private String currentSessionNumber;

    private boolean authbool = false;

    //A Handler allows you to send and process Message and Runnable objects associated
    //with a thread's MessageQueue.
    /**
     *Handler object
     */
    Handler handler = new Handler();

    //Represents a command that can be executed. Often used to run code in a different Thread.
    /**
     * Runnable object with one method
     */
    Runnable timedTask = new Runnable(){

        @Override
        //Run method calls the function which we want to repeat
        public void run() {
            //Call the getChatMessages every second
            getChatMessages();
            //Add the timedTask to the message queue every 1000 seconds
            handler.postDelayed(timedTask, 1000);
        }};

    /**
     * Calls the <code>onCreate</code> method of the parent class,
     * initializes all textviews and editable input, and
     * sets up a listener for clicks on the (red mail button)
     *
     * @param savedInstanceState the bundle used to pass data between activities
     *
     */
    protected void onCreate(Bundle savedInstanceState) {

        //Call the parent
        super.onCreate(savedInstanceState);

        //Set the associated view in activity chat
        setContentView(R.layout.activity_chat);

        //Associated view for getting the chat log
        textView = (TextView) findViewById(R.id.textView);

        //Add scrolling on the chat log
        textView.setMovementMethod(new ScrollingMovementMethod());

        //Associated view for Session Number Input
        sessionNumber = (EditText) findViewById(R.id.sessionNumberInput);

        //Associated input for Text Input
        inputTextstring = (EditText) findViewById(R.id.inputText);

        //Associated input for Author Input
        authorText = (EditText) findViewById(R.id.input2Text);

        //Set initial session number
        sessionNumber.setText("1");

        //Set initial value for input text
        inputTextstring.setText("Message");

        //Set initial text for the author
        authorText.setText("Author");

        //Defined floating action button (red looking email button)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Set a listener to preform action when the red email button is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                if(authbool)
                {
                    sendMessages();
                    handler.post(timedTask);
                }
            }
        });

        //Defined floating action button (red looking sync button)
        FloatingActionButton connect = (FloatingActionButton) findViewById(R.id.connect);
                connect.setOnClickListener(new View.OnClickListener() {

                    //Method to define what happens when the button is clicked
                    //Call the getMessages when the sync red button is clicked
                    @Override public void onClick(View v) {
                        if(authbool) {

                            //Different behavior now, whatever the chat session it currently is at
                            //when we click the button, this is what timedTask is going to call
                            currentSessionNumber = sessionNumber.getText().toString();
                            getChatMessages();
                            handler.post(timedTask);
                        }

                    }
        });
    }



    /**
     *Default Android method
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    /**
     *Default Android method
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the current session number and uses the <code>ConnectActivity</code> class to display messages for the session
     */
    public void getChatMessages()
    {
        //Gets the current session number for the input box
        //Create an instance of the ConnectActivity  Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method
        new ConnectActivity(textView, ConnectActivity.ACTION.GET).execute(currentSessionNumber);
    }

    /**
     * Gets the current session number and uses the <code>SendMessages</code> class to send messages for the session
     */
    public void sendMessages()
    {
        //Get the message context from the inpuTextstring textbox
        String stringToAdd = inputTextstring.getText().toString();
        //Get the author from the authorText textbox
        String authorToAdd = authorText.getText().toString();

        //Create an instance of the Sending Message Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method
        new SendingMessage().execute(currentSessionNumber,stringToAdd,authorToAdd);
    }

    @Override
    public void result(boolean output) {
        authbool = output;
    }
}
