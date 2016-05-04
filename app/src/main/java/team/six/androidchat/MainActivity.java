package team.six.androidchat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class MainActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private FragmentLogin login;
    private FragmentSessions session;
    private FragmentChat chat;



    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        login = new FragmentLogin();
        session = new FragmentSessions();
        chat = new FragmentChat();

        viewPagerAdapter.addFragment(login, "LOGIN");
        viewPagerAdapter.addFragment(session, "SESSIONS");
        viewPagerAdapter.addFragment(chat, "CHAT");

        viewPager.setAdapter(viewPagerAdapter);
    }


}
