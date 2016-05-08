package team.six.androidchat.Add_OR_Edit_User;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.six.androidchat.R;

public class Activity_Add_Account extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private EditText confirm_password;
    private Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        send = (Button) findViewById(R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                sendUser();

            }
        });


    }

    public void sendUser()
    {
        //Get the message context from the inpuTextstring textbox
        String userToAdd = user.getText().toString();
        //Get the author from the authorText textbox
        String passwordToAdd = password.getText().toString();

        String confirm = confirm_password.getText().toString();
        //Create an instance of the Sending Message Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method

        if(!(confirm.equals(passwordToAdd))) {
            Context context = getApplicationContext();
            CharSequence text = confirm;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Passwords do not match", duration);
            toast.show();
        }

        else {

            new Async_Add_Account(this).execute(userToAdd, passwordToAdd);


        }

    }



}
