package team.six.androidchat.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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


    private EditText sessions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Inflate the layout for this fragment

        sessions = (EditText) view.findViewById(R.id.sessions);

        Get_Profile my_task = new Get_Profile(sessions);
        ;
        my_task.execute(Authentication.getUsername());
        return view;
    }
}

