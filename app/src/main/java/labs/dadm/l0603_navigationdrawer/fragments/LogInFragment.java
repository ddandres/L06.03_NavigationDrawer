/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0603_navigationdrawer.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import labs.dadm.l0603_navigationdrawer.R;

// Displays an UI to let the user log in into a system. It includes no business logic, just for show.
// Fragments should have just one empty constructor, so required parameters are passed through a
// Factory pattern. Those parameters are included as arguments within the Fragment, so they can be
// recovered in case of destruction and re-creation. It also adds actions to the ActionBar.
public class LogInFragment extends Fragment {

    // Required empty public constructor.
    public LogInFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The Fragment can now add actions to the ActionBar and react when they are clicked
        setHasOptionsMenu(true);
    }

    // Creates the View associated to this Fragment from a Layout resource.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Update the user name according to the obtained argument
        final EditText etUser = view.findViewById(R.id.etUserName);
        etUser.setText(requireArguments().getString("username"));
        return view;
    }

    // This method is executed when the activity is created to populate the ActionBar with actions.
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        // Generate the Menu object from the XML resource file
        inflater.inflate(R.menu.menu_login, menu);
    }

    // This method is executed when any action from the ActionBar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Determine the action to take place according to the Id of the action selected
        if (item.getItemId() == R.id.mLogin) {
            // Notify the user that this action has been selected
            Toast.makeText(getContext(), R.string.menu_fragment_login, Toast.LENGTH_SHORT).show();
            return true;
        }
        // There was no custom behaviour for that action, so let the system take care of it
        return super.onOptionsItemSelected(item);
    }
}
