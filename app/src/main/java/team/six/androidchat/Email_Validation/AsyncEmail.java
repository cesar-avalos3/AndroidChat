/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename ConnectActivity.java
 *
 */

//Deafult package which stores all java files
package team.six.androidchat.Email_Validation;


//The AsyncTask class allows us to work in the background without disrupting the apps User Interface
import android.os.AsyncTask;

//Used to display HTML in a textview

//Used for debuggging
import android.util.Log;

//For modifying a text view


//Used for HTTP Requests

//Used for reading input from a file

import team.six.androidchat.Email_Validation.GMailSender;


/**
 * <p>
 * This class contains a method <code>doBackground</code> which overrides a method in the <code>AsyncTask</code> class.
 * </p>
 * <p>
 * This method allows us to preform operations in the background without interupting the apps user interface.
 * </p>
 * <p>
 * The <code>doBackground</code> method submits an HTTP get request. If the request runs through, the text of the page is returned in a string
 *
 * @author Cesar Avalos, Alec Knutsen
 * @see AsyncTask
 */
public class AsyncEmail extends AsyncTask<String, Void, String> {

    private String class_name;

    public AsyncEmail() {

        class_name = this.getClass().getName();
    }

    protected String doInBackground(String... arg0)
    {

        try {


            GMailSender sender = new GMailSender("username@gmail.com", "password");

            sender.sendMail("This is Subject",
                    "This is Body",
                    "aqknutsen@gmail.com",
                    "bahmtay@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
        //Return empty string as default
        return "";
    }



}
