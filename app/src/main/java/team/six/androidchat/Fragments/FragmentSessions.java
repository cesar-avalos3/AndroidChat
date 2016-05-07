package team.six.androidchat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import team.six.androidchat.Create_New_Chatroom.Add_Friends;
import team.six.androidchat.Create_New_Chatroom.CreateRoom;
import team.six.androidchat.MainActivity;
import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Get_Sessions;
import team.six.androidchat.Validating_User.Authentication;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentSessions extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spin;

    private static String  session_number;

    private Button create_room;

    private Button add_to_room;

    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        spin = (Spinner) view.findViewById(R.id.sessions_spinner);

        create_room = (Button) view.findViewById(R.id.create_room);

        add_to_room = (Button) view.findViewById(R.id.add_to_room);



        create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateRoom.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                startActivity(intent);
            }
        });

        add_to_room.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Add_Friends.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                startActivity(intent);
            }
        });

        Get_Sessions task = new Get_Sessions(spin,view.getContext());

        task.execute(Authentication.getUsername());


        spin.setOnItemSelectedListener(this);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        session_number = spin.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static String getSession() {

        return(session_number);
    }



}
