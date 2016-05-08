package team.six.androidchat.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team.six.androidchat.Adding_User.EditPassword;
import team.six.androidchat.Adding_User.Edit_Username;
import team.six.androidchat.Main2Activity;
import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Get_Profile;
import team.six.androidchat.Validating_User.Authentication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class UserProfile extends Fragment {


    private TextView sessions;

    private Button edit_pass;

    private Button edit_user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Inflate the layout for this fragment

        sessions = (TextView) view.findViewById(R.id.sessions);

        edit_pass = (Button) view.findViewById(R.id.my_edit_pass);

        edit_user = (Button) view.findViewById(R.id.edit_user);

        edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EditPassword.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                view.getContext().startActivity(intent);

            }
        });

        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),Edit_Username.class);

                // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
                view.getContext().startActivity(intent);

            }
        });

        Get_Profile my_task = new Get_Profile(sessions);
        ;
        my_task.execute(Authentication.getUsername());
        return view;
    }
}

