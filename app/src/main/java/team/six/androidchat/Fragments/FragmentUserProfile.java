/**
 *
 * @author Alec Knutsen, Cesar Avalos
 * @date  5/8/2016
 * @filename FragmentUserProfile.java
 * Fragment associated with displaying the user profile
 *
 */



package team.six.androidchat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import team.six.androidchat.Add_OR_Edit_User.Activity_Edit_Password;
import team.six.androidchat.Add_OR_Edit_User.Activity_Edit_Username;
import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Async_Get_User_Profile;
import team.six.androidchat.Validating_User.Async_Authenticate_User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FragmentUserProfile extends Fragment {


    //DEclare all textviews and buttons
    private TextView sessions;

    private Button edit_pass;

    private Button edit_user;



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
     * @param savedInstanceState
     * @return View - View associated with FragmentChatLayout
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Initialize view
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);

        //Initialize all textviews and buttons
        sessions = (TextView) view.findViewById(R.id.sessions);

        edit_pass = (Button) view.findViewById(R.id.my_edit_pass);

        edit_user = (Button) view.findViewById(R.id.edit_user);

        //Set click listener
        edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Activity_Edit_Password.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                view.getContext().startActivity(intent);

            }
        });

        //Set click listener
        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),Activity_Edit_Username.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                view.getContext().startActivity(intent);

            }
        });


        //Declare an instance of Async_Get_User_Profile to get the profile of the user on create
        Async_Get_User_Profile my_task = new Async_Get_User_Profile(sessions);
        my_task.execute(Async_Authenticate_User.getUsername());
        //Return the view
        return view;
    }
}

