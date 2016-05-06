package team.six.androidchat.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import team.six.androidchat.R;
import team.six.androidchat.Sending_Recieving.Get_Sessions;
import team.six.androidchat.Validating_User.Authentication;

/**
 * Created by cesaravalos on 5/2/16.
 */
public class FragmentSessions extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spin;

    private static String  session_number;
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        spin = (Spinner) view.findViewById(R.id.sessions_spinner);

        Get_Sessions task = new Get_Sessions(spin,view.getContext());

        task.execute(Authentication.getUsername());


        spin.setOnItemSelectedListener(this);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        session_number = spin.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static String getSession() {

        return(session_number);
    }



}
