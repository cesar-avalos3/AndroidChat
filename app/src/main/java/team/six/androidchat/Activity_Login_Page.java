/**
 *
 * @author Alec Knutsen
 * @date  5/8/2016
 * @filename Activity_Login_Page.java
 * @description Activity that creates the opening login page. Uses FragmentLogin to set up the page view
 *
 */


package team.six.androidchat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import team.six.androidchat.Fragments.FragmentLogin;
import team.six.androidchat.Not_To_Important.ViewPagerAdapter;


/**
 * <p>
 * This class contains a method <code>setupViewPager</code> which uses the <code>FragmentLogin</code> class to set up the view for the login page.
 * </p>
 * @author Alec Knutsen, Cesar Avalos
 * @see FragmentLogin
 * @see ViewPagerAdapter
 */
public class Activity_Login_Page extends AppCompatActivity
{
    //Variables for layouts
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentLogin login;




    /**
     * Calls the <code>onCreate</code> method of the parent class,
     * sets up the viewPager for the layout of the page
     * @param savedInstance the bundle used to pass data between activities
     *
     */
    protected void onCreate(Bundle savedInstance)
    {
        //call parent on create
        super.onCreate(savedInstance);
        //set associated layout
        setContentView(R.layout.activity_login_page);

        //Initialize the viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //Call the setupViewPager method
        setupViewPager(viewPager);

        //Initialize the tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * Creates an instance of the <code>ViewPagerAdapter</code> and <code>FragmentLogin</code>to set up the login page view
     */
    private void setupViewPager(ViewPager viewPager)
    {
        //Create a new instance of the ViewPagerAdapater
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Create a new instance of FragmentLogin
        login = new FragmentLogin();


        //Add the fragment to the ViewPagerAdapter
        viewPagerAdapter.addFragment(login, "LOGIN");


        viewPager.setAdapter(viewPagerAdapter);
    }


}
