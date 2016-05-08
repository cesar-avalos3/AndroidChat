/**
 *
 * @author Cesar Avalos
 * @date  5/2/2016
 * @filename ViewPagerAdapter.java
 * Uses the FragmentPAger adpater as its parent class
 *
 */


package team.six.androidchat.Not_To_Important;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter{

    //Array list for fragments
    protected List<Fragment> fragmentList = new ArrayList<>();
    //Array list for fragment titles
    protected List<String> fragmentTitleList = new ArrayList<>();

    /**
     * Call parent method
     * @param fm
     */
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    /**
     * Returns fragment at the specified index
     *
     * @param index
     * @return
     */
    public Fragment getItem(int index)
    {
        return fragmentList.get(index);
    }

    /**
     * Returns the number of fragment
     *
     * @return int - Returns the number of fragments
     */
    public int getCount()
    {
        return fragmentList.size();
    }

    /**
     * Adds fragment and title to specified arrays
     *
     * @param fragment - Fragment to add
     * @param title - Fragment title to add
     */
    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    /**
     * Returns the fragment title at the index
     *
     * @param index
     * @return CharSquenece - Title of index
     */
    public CharSequence getPageTitle(int index)
    {
        return fragmentTitleList.get(index);
    }

}
