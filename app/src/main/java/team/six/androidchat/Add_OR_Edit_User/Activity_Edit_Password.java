package team.six.androidchat.Add_OR_Edit_User;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.six.androidchat.R;

public class Activity_Edit_Password extends AppCompatActivity {

    private EditText user;
    private EditText old_password;
    private EditText new_password;
    private EditText confirm_new_password;
    private Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        user = (EditText) findViewById(R.id.username);
        old_password = (EditText) findViewById(R.id.old_password);
        new_password = (EditText) findViewById(R.id.new_password);
        confirm_new_password = (EditText) findViewById(R.id.confirm_new_password);
        send = (Button) findViewById(R.id.new_edit_pass);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                updatePassword();

            }
        });


    }

    public void updatePassword()
    {
        //Get the message context from the inpuTextstring textbox
        String userToEdit = user.getText().toString();
        //Get the author from the authorText textbox
        String passwordToEdit = old_password.getText().toString();

        String new_pass = new_password.getText().toString();
        String confirm_new = confirm_new_password.getText().toString();
        //Create an instance of the Sending Message Class
        //Pass in get to submit an HTTP get request
        //Call the execute method of the AsyncTask Class to execute the doInBackground method

        if(!(new_pass.equals(confirm_new))) {
            Context context = getApplicationContext();
            CharSequence text = "New Passwords do not match!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {


            new Async_Edit_Password(getApplicationContext()).execute(userToEdit, new_pass, passwordToEdit);


        }

    }



}
