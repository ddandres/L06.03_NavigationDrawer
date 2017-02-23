/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.sdm.l0603_navigationdrawer.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import labs.sdm.l0603_navigationdrawer.R;
import labs.sdm.l0603_navigationdrawer.fragments.GridImageFragment;
import labs.sdm.l0603_navigationdrawer.fragments.ListStringFragment;
import labs.sdm.l0603_navigationdrawer.fragments.LogInFragment;
import labs.sdm.l0603_navigationdrawer.fragments.SignInFragment;

/**
 * Provides access to different Fragments by means of a NavigationDrawer.
 * To ease its use with material Design components, the default ActionBar will be replaced by
 * a ToolBar, and this activity should implement the onNavigationItemSelectedListener.
 */
public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Hold references to the ToolBar, DrawerLayout, and ActionBarDrawerToggle
    Toolbar toolBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // Get a reference to the custom ToolBar
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        // Replace the default ActionBar (there should be none) by this ToolBar
        setSupportActionBar(toolBar);
        // Display the app's name on the ActionBar
        getSupportActionBar().setTitle(R.string.app_name);

        // Get a reference to the DrawerLayout (enables to pull a drawer from the screen's edge)
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        // Create a new ActionBarDrawerToggle, that links the drawer to the ToolBar
        // to implement the recommended design for navigation drawers
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Associate the ActionBarDrawerToggle to the drawer, so any change in the state
        // of the drawer will be notified to the Toolbar to react accordingly
        drawerLayout.addDrawerListener(toggle);
        // Synchronize the state of the ToolBar with that of the DrawerLayout
        toggle.syncState();

        // Get a reference to the NavigationView in charge of displaying the options in the drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        // Sets the listener to be notified when any element of the NavigationView is clicked
        navigationView.setNavigationItemSelectedListener(this);

        // Open the drawer when the activity starts
        drawerLayout.openDrawer(GravityCompat.START);
    }

    /**
     * This method is called whenever the user presses the Back button.
     * If the drawer is open then just close it. Finish the application otherwise (default behaviour).
     */
    @Override
    public void onBackPressed() {
        // Check whether the drawer is open
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // Proceed with the default behaviour (finish the application)
        else {
            super.onBackPressed();
        }
    }

    /**
     * This method is called whenever an item in the NavigationView is clicked.
     * Replace the Fragment that is being displayed by the one selected.
     * Change the title displayed in the Actionbar accordingly.
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        String tag = null;

        // Determine the action to take place according to the Id of the action selected
        switch (item.getItemId()) {

            // Display LogInFragment
            case R.id.mLoginNavigation:
                // Get or create a new LogInFragment
                tag = "login";
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = LogInFragment.newInstance("David");
                }
                // Display the LogInFragment title on the ActionBar
                toolBar.setTitle(R.string.title_login_fragment);
                break;

            // Display SignInFragment
            case R.id.mSignInNavigation:
                // Get or create a new SignInFragment
                tag = "signin";
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new SignInFragment();
                }
                // Display the SignInFragment title on the ActionBar
                toolBar.setTitle(R.string.title_signin_fragment);
                break;

            // Display ListStringFragment
            case R.id.mListNavigation:
                // Get or create a new ListStringFragment
                tag = "list";
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new ListStringFragment();
                }
                // Display the ListStringFragment title on the ActionBar
                toolBar.setTitle(R.string.title_list_fragment);
                break;

            // Display GridViewFragment
            case R.id.mGridNavigation:
                // Get or create a new GridViewFragment
                tag = "grid";
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new GridImageFragment();
                }
                // Display the GridViewFragment title on the ActionBar
                toolBar.setTitle(R.string.title_grid_fragment);
                break;
        }

        // Replace the existing Fragment by the new one
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, tag)
                .commit();

        // Close the drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}