/**
 *
 * @author  Alec Knutsen, Cesar Avalos
 * @date 5/8/2016
 * @filename Verify_Existing_User.java
 *
 */


package team.six.androidchat.Validating_User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Verify_Existing_User {


    /**
     * Public method to verify if the user exits in the database
     * @param user - User to check if exists
     * @return - Returns true if the user exists, false otherwise
     */
    public static boolean verifyUser(String user)
    {
        try {

            //Store the user and url
            String userName = user;
            String urlLink = "http://people.eecs.ku.edu/~aknutsen/448_Project4/Verify_Existing_User.php?username=" + URLEncoder.encode(userName, "utf-8");

            //Create new url
            URL url = new URL(urlLink);
            //Establish connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //Create buffered reader
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine ="false";
            //IF the html page has true, then we store that
            while( bf.readLine() != null) {
                inputLine ="true";
            }

            //If the user exists, return true
            if(inputLine.equals("true")) {

                return true;
            }
            //If the user does not exists, return false
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {

        }

        //If the request does not go through
        return false;
    }






}


