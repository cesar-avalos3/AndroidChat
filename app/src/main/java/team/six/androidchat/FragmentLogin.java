package team.six.androidchat;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentLogin extends Fragment{

    private EditText user;
    private EditText password;
    private Button connect;

    public FragmentLogin(){}

    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        user = (EditText) view.findViewById(R.id.user);
        password = (EditText) view.findViewById(R.id.password);
        connect = (Button) view.findViewById(R.id.button);

        user.setText("username");
        password.setText("password");

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                login();
            }
        });

        return view;
    }

    //Login method
    public void login() {


        /*
            //Do some sort of authetication then load an activity
         */
        boolean success = true;

        if(success) {

            //Create Intent Object
            //First parameter is a Context - this object is a subclass of Activity which is a subclass of Context
            //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
            Intent intent = new Intent(this.getActivity(), MainActivity.class);

            // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
            startActivity(intent);


        }

    }
}
