/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Edit_Password.java
 * @description Activity that creates the page for editing a user password. Uses Async_Edit_Password to update the database
 *
 */


package team.six.androidchat.Add_OR_Edit_User;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.six.androidchat.R;

/**
 * <p>
 * This class contains a method <code>updatePassword</code> which creates an instance of the<code> Async_Edit_Password</code> class.
 * </p>
 * <p>
 * This method allows us to validate input for adding a new password and updating the database with the new password if verified using <code> Async_Edit_Password</code>
 * </p>
 *
 * @author Alec Knutsen
 * @see Async_Edit_Password
 */
public class Activity_Edit_Password extends AppCompatActivity {

    private EditText user;
    private EditText old_password;
    private EditText new_password;
    private EditText confirm_new_password;
    private Button send;


    /**
     * Calls the <code>onCreate</code> method of the parent class,
     * initializes all textviews and buttons, and
     * sets up a listener for clicks
     *
     * @param savedInstanceState the bundle used to pass data between activities
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Call parent onCreate
        super.onCreate(savedInstanceState);

        //Find associated layout
        setContentView(R.layout.activity_edit_password);

        //Initialize text and buttons
        user = (EditText) findViewById(R.id.username);
        old_password = (EditText) findViewById(R.id.old_password);
        new_password = (EditText) findViewById(R.id.new_password);
        confirm_new_password = (EditText) findViewById(R.id.confirm_new_password);
        send = (Button) findViewById(R.id.new_edit_pass);

        //Set up a listener for the send button to call the updatePassword method
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                updatePassword();

            }
        });


    }

    /**
     * Verifies that the new user password and confirm new user password are correct, else uses a toast to print an error.
     * Creates an instance of the <code>Async_Edit_Password</code> class to update the password in the database
     * if validated.
     */
    public void updatePassword()
    {
        //Get the user whose password we want to edit from the input text
        String userToEdit = user.getText().toString();

        //Get the old password to edit from the input box
        String passwordToEdit = old_password.getText().toString();

        //Get the new password from the input box
        String new_pass = new_password.getText().toString();

        //Get the confirmed new password from the input box
        String confirm_new = confirm_new_password.getText().toString();

        //If the new password and confirmed new password are not equal, toast an error
        if(!(new_pass.equals(confirm_new))) {

            Context context = getApplicationContext();
            CharSequence text = "New Passwords do not match!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //If the new password and confirmed new password are equal, call Async_Edit_Password to handle editing the password in the database
        else {


            new Async_Edit_Password(getApplicationContext()).execute(userToEdit, new_pass, passwordToEdit);


        }

    }



}
