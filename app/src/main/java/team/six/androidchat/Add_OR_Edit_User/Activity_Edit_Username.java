package team.six.androidchat.Add_OR_Edit_User;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.six.androidchat.R;

public class Activity_Edit_Username extends AppCompatActivity {

    private EditText old_user;
    private EditText password;
    private EditText new_user;
    private EditText confirm_new_user;
    private Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_username);

        old_user = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        new_user = (EditText) findViewById(R.id.new_username);
        confirm_new_user = (EditText) findViewById(R.id.confirm_new_username);
        send = (Button) findViewById(R.id.new_edit_user);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                updateUser();

            }
        });


    }

    public void updateUser()
    {
        //Get the message context from the inpuTextstring textbox
        String userToEdit = old_user.getText().toString();
        //Get the author from the authorText textbox
        String pass = password.getText().toString();

        String new_us = new_user.getText().toString();
        String confirm_new = confirm_new_user.getText().toString();
        //Create an instance of the Sending Message Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method

        if(!(new_us.equals(confirm_new))) {
            Context context = getApplicationContext();
            CharSequence text = "New usernames do not match!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {


            new Async_Edit_Username(getApplicationContext()).execute(userToEdit, new_us, pass);


        }

    }



}

