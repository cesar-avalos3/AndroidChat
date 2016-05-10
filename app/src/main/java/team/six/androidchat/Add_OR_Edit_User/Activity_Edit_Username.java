/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Edit_Username.java
 * @description Activity that creates the page for editing a username. Uses Async_Edit_Username to update the database
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
 * This class contains a method <code>updateUser</code> which creates an instance of the<code> Async_Edit_Username</code> class.
 * </p>
 * <p>
 * This method allows us to validate input for adding new user and updating the database with the new user if verified using <code> Async_Edit_Username</code>
 * </p>
 *
 * @author Alec Knutsen
 * @see Async_Edit_Username
 */
public class Activity_Edit_Username extends AppCompatActivity {

    //All textboxes and buttons
    private EditText old_user;
    private EditText password;
    private EditText new_user;
    private EditText confirm_new_user;
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
        //Call parent on create
        super.onCreate(savedInstanceState);
        //Find associated view
        setContentView(R.layout.activity_edit_username);

        //Initialize all buttons and views
        old_user = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        new_user = (EditText) findViewById(R.id.new_username);
        confirm_new_user = (EditText) findViewById(R.id.confirm_new_username);
        send = (Button) findViewById(R.id.new_edit_user);

        //Set up listener for send button to call updateUser
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                updateUser();

            }
        });


    }

    /**
     * Verifies that the new username and confirm new username are correct, else uses a toast to print an error.
     * Creates an instance of the <code>Async_Edit_Username</code> class to check that the username is not taken, and updates the username in the database
     * if validated.
     */
    private void updateUser()
    {
        //Get user to edit from the textbox
        String userToEdit = old_user.getText().toString();

        //Get password of the user to edit from the textbox
        String pass = password.getText().toString();

        //Get new username from the textbox
        String new_us = new_user.getText().toString();

        //Get confirmed new username from the textbox
        String confirm_new = confirm_new_user.getText().toString();

        //If the new username and confirmed are not equal toast an error
        if(!(new_us.equals(confirm_new))) {

            Context context = getApplicationContext();
            CharSequence text = "New usernames do not match!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //If the new username and confirmed are equal, call Async_Edit_Username to handle database interactions
        else {

            new Async_Edit_Username(getApplicationContext(),false).execute(userToEdit, new_us, pass);

        }

    }



}

