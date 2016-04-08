package team.six.androidchat;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

//Activity
public class ChatActivity extends AppCompatActivity {

    private TextView textView;
    private EditText sessionNumber;
    private EditText inputTextstring;

    private EditText authorText;



    //A Handler allows you to send and process Message and Runnable objects associated
    //with a thread's MessageQueue. Each Handler instance is associated with a single thread
    //and that thread's message queue. When you create a new Handler, it is bound to the
    //thread / message queue of the thread that is creating it -- from that point on, it will
    //deliver messages and runnables to that message queue and execute them as they come
    //out of the message queue.
    //Handler handler = new Handler();
    //Represents a command that can be executed. Often used to run code in a different Thread.
    Handler handler = new Handler();

    //Represents a command that can be executed. Often used to run code in a different Thread.
    Runnable timedTask = new Runnable(){

        @Override
        //Run method calls the function which we want to repeat
        public void run() {
            //Call the getChatMessages every second
            getChatMessages();
            //Add the timedTask to the message queue every 1000 seconds
            handler.postDelayed(timedTask, 1000);
        }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call the parent
        super.onCreate(savedInstanceState);

        //Set the associated view in activity chat
        setContentView(R.layout.activity_chat);

        //Associated view for getting the chat log
        textView = (TextView) findViewById(R.id.textView);

        //Associated view for Session Number Input
        sessionNumber = (EditText) findViewById(R.id.sessionNumberInput);

        //Associated input for Text Input
        inputTextstring = (EditText) findViewById(R.id.inputText);

        //Associated input for Author Input
        authorText = (EditText) findViewById(R.id.input2Text);

        //Set initial session number
        sessionNumber.setText("1");

        //Set initial value for input text
        inputTextstring.setText("Message");

        authorText.setText("Author");

        //Defined floating action button (red looking email button)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Set a listener to preform action when the red email button is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            //Method to define what happens when the button is clicked
            //Call the sendMessages and getChatMessages when the email red button is clicked
            public void onClick(View view) {
                getChatMessages();
                sendMessages();
                handler.post(timedTask);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    //Default
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getChatMessages()
    {
        //Gets the current session number for the input box
        String sessionNumberValue = sessionNumber.getText().toString();
        new ConnectActivity(textView, ConnectActivity.ACTION.GET).execute(sessionNumberValue);
    }
    public void sendMessages()
    {
        String sessionNumberValue = sessionNumber.getText().toString();
        String stringToAdd = inputTextstring.getText().toString();
        String authorToAdd = authorText.getText().toString();
        new SendingMessage(textView, SendingMessage.ACTION.GET).execute(sessionNumberValue,stringToAdd,authorToAdd);
    }
}
