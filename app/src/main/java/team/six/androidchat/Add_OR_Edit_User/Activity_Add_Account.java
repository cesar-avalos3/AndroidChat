/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Add_Account.java
 * @description Activity that creates the page for adding a new account. Uses Async_Add_Account to update the database
 *
 */

package team.six.androidchat.Add_OR_Edit_User;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team.six.androidchat.R;


/**
 * <p>
 * This class contains a method <code>sendUser</code> which creates an instance of the<code> Async_Add_Account</code> class.
 * </p>
 * <p>
 * This method allows us to validate input for adding an account and update the database with the new account if verified using <code> Async_Add_Account</code>
 * </p>
 *
 * @author Alec Knutsen
 * @see Async_Add_Account
 */
public class Activity_Add_Account extends AppCompatActivity {

    //Declare private buttons and textviews
    private EditText user;
    private EditText password;
    private EditText confirm_password;
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
        setContentView(R.layout.activity_add_account);

        //Initialize all buttons and texts
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        send = (Button) findViewById(R.id.send);


        //Set up the send button with a listener that calls sendUser
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                sendUser();

            }
        });


    }


    /**
     * Verifies that the user password and confirm user password are correct, else uses a toast to print an error.
     * Creates an instance of the <code>Async_Add_Account</code> class to check that the username is not taken, and adds the user to the database
     * if validated.
     */
    private void sendUser()
    {
        //Get the user to add from the input box
        String userToAdd = user.getText().toString();
        //Get the password to add from the input box
        String passwordToAdd = password.getText().toString();
        //Get the confirmed password from the input box
        String confirm = confirm_password.getText().toString();

        //If the password and confirmed password are not equal, toast an error message
        if(!(confirm.equals(passwordToAdd))) {

            Context context = getApplicationContext();
            CharSequence text = confirm;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Passwords do not match", duration);
            toast.show();
        }

        //If the password and confirmed password are equal, use Async_Add_Account to handle adding the user
        else {

            new Async_Add_Account(this,false).execute(userToAdd, passwordToAdd);

        }

    }


}
