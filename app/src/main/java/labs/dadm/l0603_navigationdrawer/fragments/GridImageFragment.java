/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0603_navigationdrawer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import labs.dadm.l0603_navigationdrawer.R;
import labs.dadm.l0603_navigationdrawer.adapters.GridImageAdapter;

/**
 * Displays a List of Drawables in Grid form.
 * It includes no business logic, just for show.
 * It also adds actions to the ActionBar.
 */
public class GridImageFragment extends Fragment {

    // Hold reference to the custom grid adapter
    GridImageAdapter adapter;

    /**
     * Required empty public constructor.
     */
    public GridImageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The Fragment can now add actions to the ActionBar and react when they are clicked
        setHasOptionsMenu(true);
    }

    /**
     * Creates the View associated to this Fragment from a Layout resource.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get a reference to the GridView in charge of displaying the List of Drawables
        GridView grid = (GridView) inflater.inflate(R.layout.fragment_grid_images, null);
        // Create an instance of the custom grid adapter
        adapter = new GridImageAdapter(getContext());
        // Associate the adapter to the GridView
        grid.setAdapter(adapter);

        return grid;
    }

    /**
     * This method is called whenever the Fragment has been dettached from the activity,
     * so it will be newly created.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Recycle the TypedArray used in the custom grid adapter to keep the List of Drawables
        adapter.recycle();
    }

    /**
     * This method is executed when the activity is created to populate the ActionBar with actions.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_grid, menu);
    }

    /**
     * This method is executed when any action from the ActionBar is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Determine the action to take place according to the Id of the action selected
        if (item.getItemId() == R.id.mGrid) {
            // Notify the user that this action has been selected
            Toast.makeText(getContext(), R.string.menu_fragment_grid, Toast.LENGTH_SHORT).show();
            return true;
        }
        // There was no custom behaviour for that action, so let the system take care of it
        return super.onOptionsItemSelected(item);
    }

}