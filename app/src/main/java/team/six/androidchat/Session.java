package team.six.androidchat;

import java.util.ArrayList;

/**
 * Created by Cesar on 4/1/2016.
 */
public class Session {

    ArrayList<Message> Messages;
    int size;
    int sessionID;

    public Session()
    {
        size = 0;
    }

    public void addMessage(Message message)
    {
        Messages.add(message);
    }

}
