/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename Session.java
 *
 */

//Package that stores all java files for the android app
package team.six.androidchat.Not_To_Important;

//Import package for array lists
import java.util.ArrayList;

import team.six.androidchat.Not_To_Important.Message;

/**
 * Class with one public method <code>addMessage</code> which adds a <code>Message</code> object to an arrayList.
 *
 *
 * @author Cesar Avalos
 *
 */
public class Session {

    /**
     * Array list that stores all messages
     */
    ArrayList<Message> Messages;
    /**
     * Integer to store the size of the array list
     */
    int size;

    /**
     * Constructor that initializes the instance variable <code>size</code>
     */
    public Session()
    {
        size = 0;
    }

    /**
     * Adds the <code>Message</code> object passed in as a parameter to the <code>Messages</code> arraylist
     *
     * @param message the object <code>Message</code> which will be added to the arraylist <code>Messages</code>
     * @see Message
     */
    public void addMessage(Message message)
    {
        //Add the object to the array list
        Messages.add(message);
    }

}
