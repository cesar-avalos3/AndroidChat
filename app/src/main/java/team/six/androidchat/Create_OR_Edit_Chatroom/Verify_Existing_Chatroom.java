package team.six.androidchat.Create_OR_Edit_Chatroom;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Cesar on 5/1/2016.
 */
public class Verify_Existing_Chatroom {



    public static boolean verifyRoom(String room)
    {
        try {

            String roomName = room;
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Verify_Existing_Room.php?roomname=" + URLEncoder.encode(roomName, "utf-8");

            URL url = new URL(urlLink);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine ="false";
            while( bf.readLine() != null) {
                inputLine ="true";
            }



            if(inputLine.equals("true")) {
                Log.d("Input Line:", inputLine);
                return true;
            }
            else
            {
                Log.d("Input Line:", inputLine);
                return false;
            }
        }catch (Exception e)
        {

            Log.d("This is trooo", e.getMessage());
        }
        return false;
    }






}


