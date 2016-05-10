/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Create_Room.java
 * @description Activity that creates the page for creating a chatroom. Uses Async_Create_Room to update the database
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
 * This class contains a method <code>createRoom</code> which creates an instance of the<code> Async_Create_Room</code> class.
 * </p>
 * <p>
 * This method allows us to validate input for creating a chatroom and updating the database if verified using <code> Async_Create_Room</code>
 * </p>
 * @author Alec Knutsen
 * @see Async_Create_Room
 */
public class Activity_Create_Room extends AppCompatActivity {

    //Textviews and buttons
    private EditText room_name;
    private Button create;



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
        //Set the associated layout
        setContentView(R.layout.activity_create_room);

        //Initialize textviews and buttons
        room_name = (EditText) findViewById(R.id.name);
        create = (Button) findViewById(R.id.create);


        //Set up a click event for the create button to call createRoom
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                createRoom();

            }
        });


    }

    /**
     * Creates an instance of the <code>Async_Create_Room</code> class to check that the room name is not taken and create the room if it is not
     */
    private void createRoom()
    {
        //Get the room to create from the textbox
        String roomToAdd = room_name.getText().toString();

        //Get the application context
        Context context = getApplicationContext();

        //Use Aysnc_Create_Room to handle database interactions and creating the room
        new Async_Create_Room(context,false).execute(roomToAdd);



    }



}
