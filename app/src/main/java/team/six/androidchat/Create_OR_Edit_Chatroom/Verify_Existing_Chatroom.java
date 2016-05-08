/**
 *
 * @author  Alec Knutsen
 * @date 5/8/2016
 * @filename Verify_Existing_Chatroom
 * @description - Finds if a chatroom already exists
 *
 */



package team.six.androidchat.Create_OR_Edit_Chatroom;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Verify_Existing_Chatroom {


    /**
     *
     * @param room - Room to see if exists
     * @return - Returns true if the room exists, false otherwise
     */
    public static boolean verifyRoom(String room)
    {
        try {

            //Store the roomname
            String roomName = room;
            //Store the url
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Verify_Existing_Room.php?roomname=" + URLEncoder.encode(roomName, "utf-8");
            //Establish url
            URL url = new URL(urlLink);
            //Establish HTTP conenction
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //Get a new buffered reader
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine ="false";
            //If the room exists the html file will have "true"
            while( bf.readLine() != null) {
                inputLine ="true";
            }

            //If the room exists
            if(inputLine.equals("true")) {
                return true;
            }
            //If the room does not exist
            else
            {

                return false;
            }
        }
        catch (Exception e)
        {

        }
        return false;
    }






}


