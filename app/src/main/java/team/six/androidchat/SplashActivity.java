/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename SplashActivity.java
 *
 */


//Base package for all android packages
package team.six.androidchat;

//Import Intent so we can start ConnectActivity from this file
import android.content.Intent;
import android.os.Bundle;
//Import Base Activity Class
import android.support.v7.app.AppCompatActivity;

/**
 * <p>
 * Activity Class that is loaded when the App is created.
 * Inherits from the base android <code>AppCompatActivity</code>.
 * </p>
 * <p>
 * Loads the App and displays a fleeting icon, then calls the <code>ConnectActivity</code> to start the program
 *</p>
 *
 * @author Cesar Avalos
 * @see AppCompatActivity
 */
public class SplashActivity extends AppCompatActivity {



    /**
     * calls the <code>onCreate</code> method of the parent class,
     * creates a new intent that communicates with the <code>ConnectActivity</code> class, and
     * loads the <code>ConnectActivity</code> and destroys the <code>SplashActivity</code>.
     *
     * @param savedInstanceBundle the bundle used to pass data between activities
     *@see ConnectActivity
     *
     */
    protected void onCreate(Bundle savedInstanceBundle)
    {
        //Call super class
        super.onCreate(savedInstanceBundle);

        //Create Intent Object
        //First parameter is a Context - this object is a subclass of Activity which is a subclass of Context
        //Second parameter - Class to which the intent should be delivered (i.e. the activity that should be started)
        Intent intent = new Intent(this, MainActivity.class);

        // Call method startActivity  from the android Intent Class (i.e. start the chatActivity)
        startActivity(intent);
        //Call method finish from the android AppCompatActivity Class (i.e. Remove activity SplashActivty from the activity stack)
        finish();
    }

}

