/**
 *
 * @author Alec Knutsen, Cesar Avalos
 * @date  5/8/2016
 * @filename FragmentChat.java
 * Fragment associated with displaying chat on main page
 *
 */


package team.six.androidchat.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Async_Recieve_Messages;
import team.six.androidchat.Sending_Recieving.Async_Send_Messages;
import team.six.androidchat.Validating_User.Async_Authenticate_User;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentChat extends Fragment
{

    /**
     * Instance variable for textboxes and buttons
     */
    private TextView session_messages;

    private EditText user_message;

    private FloatingActionButton sendMessage;

    private FloatingActionButton refresh_page;

    //Stores the current session number
    private String currentSessionNumber;


    /**
     * Call parent onCreate
     *
     * @param savedInstance
     */
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);


    }



    /**
     * Initializes all buttons and textboxes and sets  click listeners
     *
     * @param inflater
     * @param container
     * @param savedInstance
     * @return View - View associated with FragmentChatLayout
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {

        //Find associated view
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        //Initializes textbox for displaying messages
        session_messages = (TextView) view.findViewById(R.id.sesMess);
        //Establish scrolling for messages
        session_messages.setMovementMethod(new ScrollingMovementMethod());


        //Initialize message input
        user_message = (EditText) view.findViewById(R.id.messageInput);
        user_message.setText("Message");

        //Initialize buttons
        sendMessage = (FloatingActionButton) view.findViewById(R.id.sendMess);
        refresh_page = (FloatingActionButton) view.findViewById(R.id.refresh);

        //Set a listener to preform action when the red email button is clicked
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                //Send the chat messages
                sendMessages();
                handler.post(timedTask);

            }
        });

        //Defined floating action button (red looking sync button)
        refresh_page.setOnClickListener(new View.OnClickListener() {

            //Method to define what happens when the button is clicked
            //Call the getMessages when the sync red button is clicked
            @Override public void onClick(View v) {


                //Get the current session number and get the chat messages
                currentSessionNumber = FragmentSessions.getSession();
                getChatMessages();
                handler.post(timedTask);


            }
        });

        return view;
    }





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
     * Gets the current session number and uses the <code>Async_Recieve_Messages</code> class to display messages for the session
     */
    public void getChatMessages()
    {
        //Gets the current session number for the input box
        //Create an instance of the Async_Recieve_Messages  Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method
        new Async_Recieve_Messages(session_messages, Async_Recieve_Messages.ACTION.GET,false).execute(currentSessionNumber);
    }

    /**
     * Gets the current session number and uses the <code>SendMessages</code> class to send messages for the session
     */
    public void sendMessages()
    {
        //Get the message content
        String stringToAdd = user_message.getText().toString();
        //Get the author
        String authorToAdd = Async_Authenticate_User.getUsername();

        //Create an instance of the Sending Message Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method
        new Async_Send_Messages(false).execute(currentSessionNumber,stringToAdd,authorToAdd);
    }


}


