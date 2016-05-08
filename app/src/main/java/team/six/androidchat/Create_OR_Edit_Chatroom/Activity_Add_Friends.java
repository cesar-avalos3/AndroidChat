package team.six.androidchat.Create_OR_Edit_Chatroom;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.six.androidchat.Add_OR_Edit_User.Async_Add_Friends;
import team.six.androidchat.R;

public class Activity_Add_Friends extends AppCompatActivity {

    private EditText room_name;
    private EditText user;
    private Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        room_name = (EditText) findViewById(R.id.room);
        user = (EditText) findViewById(R.id.add);
        add = (Button) findViewById(R.id.add_button);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages when the email red button is clicked
            public void onClick(View view) {

                addUser();

            }
        });


    }

    public void addUser()
    {
        //Get the message context from the inpuTextstring textbox
        String room = room_name.getText().toString();
        String user_to_add = user.getText().toString();


        Context context = getApplicationContext();


        new Async_Add_Friends(context).execute(room,user_to_add);



    }



}
