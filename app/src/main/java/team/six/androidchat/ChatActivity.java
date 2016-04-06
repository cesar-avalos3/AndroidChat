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

public class ChatActivity extends AppCompatActivity {

    private TextView textView;
    private EditText sessionNumber;
    private EditText inputTextstring;

    Handler handler = new Handler();
    Runnable timedTask = new Runnable(){

        @Override
        public void run() {
            getChatMessages();
            handler.postDelayed(timedTask, 1000);
        }};

    public void refreshActivity()
    {
        new ConnectActivity(textView, ConnectActivity.ACTION.GET).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        textView = (TextView) findViewById(R.id.textView);
        sessionNumber = (EditText) findViewById(R.id.sessionNumberInput);
        inputTextstring = (EditText) findViewById(R.id.inputText);
        sessionNumber.setText("1");
        inputTextstring.setText("Please Input your text here");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessages();
                getChatMessages();
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
        String sessionNumberValue = sessionNumber.getText().toString();
        new ConnectActivity(textView, ConnectActivity.ACTION.GET).execute(sessionNumberValue);
    }
    public void sendMessages()
    {
        String stringToAdd = inputTextstring.getText().toString();
        new SendingMessage();
    }
}
