package team.six.androidchat.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.six.androidchat.R;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentChat extends Fragment
{
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.content_chat, container, false);
    }

}
