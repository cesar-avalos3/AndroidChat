package team.six.androidchat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import team.six.androidchat.Fragments.FragmentChat;
import team.six.androidchat.Fragments.FragmentSessions;
import team.six.androidchat.Fragments.FragmentUserProfile;
import team.six.androidchat.Not_To_Important.ViewPagerAdapter;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class Activity_Main_Page extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private FragmentUserProfile profile;
    private FragmentChat chat;
    private FragmentSessions session;




    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login_page);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        profile = new FragmentUserProfile();
        chat  = new FragmentChat();
        session = new FragmentSessions();


        viewPagerAdapter.addFragment(profile, "Profile");
        viewPagerAdapter.addFragment(session, "ChatRooms");
        viewPagerAdapter.addFragment(chat, "ChatLog");



        viewPager.setAdapter(viewPagerAdapter);
    }


}