/**
 *
 * @author Alec Knutsen, Cesar Avalos
 * @date  5/8/2016
 * @filename FragmentSessions.java
 * Fragment associated with displaying the user chatrooms on the page
 *
 */


package team.six.androidchat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import team.six.androidchat.Create_OR_Edit_Chatroom.Activity_Add_Friends;
import team.six.androidchat.Create_OR_Edit_Chatroom.Activity_Create_Room;
import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Async_Get_Sessions;
import team.six.androidchat.Validating_User.Async_Authenticate_User;


public class FragmentSessions extends Fragment implements AdapterView.OnItemSelectedListener {

    //Declare all spinners, and buttons
    private Spinner spin;

    private Button create_room;

    private Button add_to_room;

    //Stores the session number
    private static String  session_number;

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
     * Initializes all buttons and textboxes and sets click listeners
     *
     * @param inflater
     * @param container
     * @param savedInstance
     * @return View - View associated with FragmentChatLayout
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        //Store view
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        //Iniialize spinners and buttons
        spin = (Spinner) view.findViewById(R.id.sessions_spinner);
        create_room = (Button) view.findViewById(R.id.create_room);
        add_to_room = (Button) view.findViewById(R.id.add_to_room);


        //Set click listener
        create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Activity_Create_Room.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                startActivity(intent);
            }
        });

        //Set click listener
        add_to_room.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Activity_Add_Friends.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                startActivity(intent);
            }
        });

        //Declare and execute the Async_Get_Sessions class to fill in the spinner with chatroom names
        Async_Get_Sessions task = new Async_Get_Sessions(spin,view.getContext(),false);

        task.execute(Async_Authenticate_User.getUsername());


        //Initialize the spinner
        spin.setOnItemSelectedListener(this);

        //Return the view
        return view;
    }

    /**
     * Implemntation of Interface method for spinner
     * @param parent
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        session_number = spin.getSelectedItem().toString();
    }

    /**
     * Implemntation of Interface method for spinner
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Returns the session number that we are on
     * @return String - session number that we are on
     */
    public static String getSession() {

        return(session_number);
    }



}
