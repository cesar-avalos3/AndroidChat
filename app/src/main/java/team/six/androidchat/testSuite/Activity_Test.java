package team.six.androidchat.testSuite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import team.six.androidchat.Add_OR_Edit_User.Async_Add_Account;
import team.six.androidchat.Add_OR_Edit_User.Async_Edit_Password;
import team.six.androidchat.Add_OR_Edit_User.Async_Edit_Username;
import team.six.androidchat.Create_OR_Edit_Chatroom.Async_Add_Friends;
import team.six.androidchat.Create_OR_Edit_Chatroom.Async_Create_Room;
import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Async_Get_Sessions;
import team.six.androidchat.Validating_User.Async_Authenticate_User;

public class Activity_Test extends AppCompatActivity {


    //Editable text for printing test results
    private EditText test;

    //String for holding test results;
   private StringBuffer test_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acitivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize string
        test_results =new StringBuffer("");

        //Initialize editText
        EditText test = (EditText) findViewById(R.id.testing);

        //For testing log in on a user
        Async_Authenticate_User test_authenticate = new Async_Authenticate_User(true,test,test_results);
        //Try to login with a user who exists
        test_authenticate.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Alec","Knutsen");

        //Test add account
        Async_Add_Account test_add_account = new Async_Add_Account(this,true,test,test_results);
        //Try to add account with same name as an already existing user
        test_add_account.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "IEXIST","Password");



        //Test edit password
        Async_Edit_Password test_edit_pass = new Async_Edit_Password(this,true,test,test_results);
        //Try to edit password with a valid existing user
        test_edit_pass.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "IEXIST","NEW_PASSWORD","Password");


        //Test edit username
        Async_Edit_Username test_edit_user = new Async_Edit_Username(this,true,test,test_results);
        //Try to edit username on a nonexisting user
        test_edit_user.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "IAMNOTINEXISTANCE","ISTILLWONTBEINEXISTENCE","Password");


        //Test add to room
        Async_Add_Friends test_add_to_room = new Async_Add_Friends(this,true,test,test_results);
        //Try to add a valid user to a room that doesnt exist
        test_add_to_room.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "ROOMDOESNTEXIST","Alec");

        //Test create room
        Async_Create_Room test_create_room = new Async_Create_Room(this,true,test,test_results);
        //Try to create a room that already exists
        test_create_room.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "ROOMALREADYEXISTS");
        


    }



}
