/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Add_Friends.java
 * @description Activity that creates the page for adding friends to an existing chatrrom. Uses Async_Add_Friends to update the database
 *
 */


package team.six.androidchat.Create_OR_Edit_Chatroom;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.six.androidchat.Create_OR_Edit_Chatroom.Async_Add_Friends;
import team.six.androidchat.R;

/**
 * <p>
 * This class contains a method <code>addUser</code> which creates an instance of the<code> Async_Add_Friends</code> class.
 * </p>
 * <p>
 * This method allows us to validate input for adding a user to an existing chatroom and updating the database if verified using <code> Async_Add_Friends</code>
 * </p>
 * @author Alec Knutsen
 * @see Async_Add_Friends
 */
public class Activity_Add_Friends extends AppCompatActivity {

    //All associated textviews and buttons
    private EditText room_name;
    private EditText user;
    private Button add;


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
        //Set the asscoiated layout
        setContentView(R.layout.activity_add_friends);

        //Initialize textviews and buttons
        room_name = (EditText) findViewById(R.id.room);
        user = (EditText) findViewById(R.id.add);
        add = (Button) findViewById(R.id.add_button);

        //Set up listener for add button to call addUser
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                addUser();

            }
        });


    }

    /**
     * Creates an instance of the <code>Async_Add_Friends</code> class to check that the user and room exist, then add the user to the room
     */
    private void addUser()
    {
        //Get the room name to add the friend to from the input box
        String room = room_name.getText().toString();
        //Get the friend to add from the inputbox
        String user_to_add = user.getText().toString();

        //Get activity context
        Context context = getApplicationContext();

        //Use Async_Add_Friends to handle adding the friend to the room
        new Async_Add_Friends(context,false).execute(room,user_to_add);



    }



}
