package team.six.androidchat.Not_To_Important;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{
    protected List<Fragment> fragmentList = new ArrayList<>();
    protected List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public Fragment getItem(int index)
    {
        return fragmentList.get(index);
    }

    public int getCount()
    {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    public CharSequence getPageTitle(int index)
    {
        return fragmentTitleList.get(index);
    }

}
