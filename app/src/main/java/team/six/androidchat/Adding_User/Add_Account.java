package team.six.androidchat.Adding_User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.six.androidchat.Adding_User.Add_Account_Async;
import team.six.androidchat.R;

public class Add_Account extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private EditText confirm_password;
    private Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_send__verification);

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        send = (Button) findViewById(R.id.send);

        user.setText("Email");
        password.setText("Password");
        confirm_password.setText("Confirm Password");

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
        new Add_Account_Async().execute(userToAdd, passwordToAdd);
    }



}
