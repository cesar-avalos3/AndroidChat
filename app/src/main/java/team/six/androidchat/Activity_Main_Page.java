/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Main_Page.java
 * @description Activity that creates the main page for the app. Uses FragmenUserProfile, FragmentLogin, and FragmentChat
 *
 */



package team.six.androidchat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import team.six.androidchat.Fragments.FragmentChat;
import team.six.androidchat.Fragments.FragmentLogin;
import team.six.androidchat.Fragments.FragmentSessions;
import team.six.androidchat.Fragments.FragmentUserProfile;
import team.six.androidchat.Not_To_Important.ViewPagerAdapter;


/**
 * <p>
 * This class contains a method <code>setupViewPager</code> which uses the <code>FragmentUserProfile, FragmentSessions, FragmentChat</code> class to set up the view for the main page.
 * </p>
 * @author Alec Knutsen, Cesar Avalos
 * @see FragmentLogin
 * @see FragmentSessions
 * @see FragmentChat
 * @see ViewPagerAdapter
 */
public class Activity_Main_Page extends AppCompatActivity
{
    //Initialize all view layouts
    private TabLayout tabLayout;
    private ViewPager viewPager;


    //Initialize all fragment variables
    private FragmentUserProfile profile;
    private FragmentChat chat;
    private FragmentSessions session;




    /**
     * Calls the <code>onCreate</code> method of the parent class,
     * sets up the viewPager for the layout of the page
     * @param savedInstance the bundle used to pass data between activities
     *
     */
    @Override
    protected void onCreate(Bundle savedInstance)
    {
        //call parent onCreate
        super.onCreate(savedInstance);
        //Find associated view
        setContentView(R.layout.activity_login_page);

        //Initialize and set up viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Initialize and set up tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * Creates an instance of the <code>ViewPagerAdapter</code> and <code>FragmentUserProfile</code> and <code>FragmentChat</code> and <code>FragmentSessions</code>to set up the login page view
     */
    private void setupViewPager(ViewPager viewPager)
    {
        //Create instance of the ViewPagerAdapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Initialize all fragments
        profile = new FragmentUserProfile();
        chat  = new FragmentChat();
        session = new FragmentSessions();


        //Add all fragments to the viewpager
        viewPagerAdapter.addFragment(profile, "Profile");
        viewPagerAdapter.addFragment(session, "ChatRooms");
        viewPagerAdapter.addFragment(chat, "ChatLog");



        viewPager.setAdapter(viewPagerAdapter);
    }


}
