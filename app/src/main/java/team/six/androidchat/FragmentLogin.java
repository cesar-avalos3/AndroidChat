package team.six.androidchat;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentLogin extends Fragment{
    public FragmentLogin(){}

    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
