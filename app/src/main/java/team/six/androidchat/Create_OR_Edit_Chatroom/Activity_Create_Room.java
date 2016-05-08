package team.six.androidchat.Create_OR_Edit_Chatroom;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.six.androidchat.Add_OR_Edit_User.Async_Create_Room;
import team.six.androidchat.R;

public class Activity_Create_Room extends AppCompatActivity {

    private EditText room_name;
    private Button create;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        room_name = (EditText) findViewById(R.id.name);
        create = (Button) findViewById(R.id.create);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                createRoom();

            }
        });


    }

    public void createRoom()
    {
        //Get the message context from the inpuTextstring textbox
        String roomToAdd = room_name.getText().toString();


        Context context = getApplicationContext();

        new Async_Create_Room(context).execute(roomToAdd);




    }



}