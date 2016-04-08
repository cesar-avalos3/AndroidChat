/**
 * @author Cesar Avalos
 * @date  4/3/2016
 * @filename SplashActivity.java
 */



package team.six.androidchat;

//Import Intent so we can start ConnectActivity from this file
import android.content.Intent;
import android.os.Bundle;
//Import Base Activity Class
import android.support.v7.app.AppCompatActivity;

/*
 * Activity Class that is loaded when the APP is created.
 * Loads the APP and displays a fleeting icon, then calls the ConnectActivity to start the program
 * @author Cesar Avalos
 *
 */
public class SplashActivity extends AppCompatActivity {



     /*
     * Calls the <code>onCreate</code> method of the parent class.
     * Creates a new intent that communicates with <code>ConnectActivity</code> class.
     * Loads the ConnectActivity and destroys the <code>SplashActivity</code>.
     * @param savedInstanceBundle the bundle used to pass data between activites
     */
    protected void onCreate(Bundle savedInstanceBundle)
    {
        //Call super class
        super.onCreate(savedInstanceBundle);

        //Create intent -
        //First parameter is a Context - Activity class is subclass of Context
        //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
        Intent intent = new Intent(this, ChatActivity.class);
        //Start activity with the intent (i.e. start the chatActivity
        startActivity(intent);
        //Remove activity SplashActivty from the activity stack
        finish();
    }

}

