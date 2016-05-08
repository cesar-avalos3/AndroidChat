package team.six.androidchat.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import team.six.androidchat.Add_OR_Edit_User.Activity_Add_Account;
import team.six.androidchat.R;
import team.six.androidchat.Validating_User.Async_Authenticate_User;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentLogin extends Fragment{

    private EditText user;
    private EditText password;
    private Button connect;

    private Button create;

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
        create = (Button) view.findViewById(R.id.create);

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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {
                createUser();
            }
        });

        return view;
    }

    //Login method
    private void login() {



        Async_Authenticate_User my_validate = new Async_Authenticate_User(user,password,getContext());

        my_validate.execute(user.getText().toString(), password.getText().toString());





    }

    private void createUser() {

        //Create Intent Object
        //First parameter is a Context - this object is a subclass of Activity which is a subclass of Context
        //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
        Intent intent = new Intent(this.getActivity(), Activity_Add_Account.class);

        // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
        startActivity(intent);


    }
}
