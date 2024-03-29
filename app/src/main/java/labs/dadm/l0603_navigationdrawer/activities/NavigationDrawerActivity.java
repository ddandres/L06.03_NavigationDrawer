/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0603_navigationdrawer.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import labs.dadm.l0603_navigationdrawer.R;
import labs.dadm.l0603_navigationdrawer.fragments.GridImageFragment;
import labs.dadm.l0603_navigationdrawer.fragments.ListStringFragment;
import labs.dadm.l0603_navigationdrawer.fragments.LogInFragment;
import labs.dadm.l0603_navigationdrawer.fragments.SignInFragment;

// Provides access to different Fragments by means of a NavigationDrawer.
// To ease its use with material Design components, the default ActionBar will be replaced by
// a ToolBar, and this activity should implement the onNavigationItemSelectedListener.
public class NavigationDrawerActivity extends AppCompatActivity {

    // Hold references to the ToolBar and DrawerLayout
    Toolbar toolBar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // Get a reference to the custom ToolBar
        toolBar = findViewById(R.id.toolBar);
        // Replace the default ActionBar (there should be none) by this ToolBar
        setSupportActionBar(toolBar);
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            // Show the user that selecting home will return one level up rather than to the top level
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Set drawable to be used when DISPLAY_HOME_AS_UP is enabled (hamburger in this case)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            // Display the app's name on the ActionBar
            getSupportActionBar().setTitle(R.string.app_name);
        }
        // Get a reference to the DrawerLayout (enables to pull a drawer from the screen's edge)
        drawerLayout = findViewById(R.id.drawerLayout);

        // Get a reference to the NavigationView in charge of displaying the options in the drawer
        NavigationView navigationView = findViewById(R.id.navView);
        // Sets the listener to be notified when any element of the NavigationView is clicked
        navigationView.setNavigationItemSelectedListener(this::selectedItem);

        // Open the drawer when the activity starts
        drawerLayout.openDrawer(GravityCompat.START);

        // If the activity is being created for the first time, then display the QuotationFragment,
        // otherwise let the system recreate whatever was being displayed previously
        if (savedInstanceState == null) {
            final Bundle bundle = new Bundle();
            bundle.putString("username", "David");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fcvLayout, LogInFragment.class, bundle)
                    .commit();

            ((NavigationView) findViewById(R.id.navView)).setCheckedItem(R.id.mLoginNavigation);
        }

    }

    // This method is executed when any action from the ActionBar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Open the drawer when the home (hamburger) icon is clicked
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // This method is called whenever the user presses the Back button.
    // If the drawer is open then just close it. Finish the application otherwise (default behaviour).
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

    // This method is called whenever an item in the NavigationView is clicked.
    // Replace the Fragment that is being displayed by the one selected.
    // Change the title displayed in the Actionbar accordingly.
    private boolean selectedItem(MenuItem item) {

        Class<? extends Fragment> fragment = null;
        Bundle bundle = null;

        // Determine the action to take place according to the Id of the action selected
        int selectedItem = item.getItemId();
        if (selectedItem == R.id.mLoginNavigation) {
            // Display LogInFragment
            bundle = new Bundle();
            bundle.putString("username", "David");
            fragment = LogInFragment.class;
            // Display the LogInFragment title on the ActionBar
            toolBar.setTitle(R.string.title_login_fragment);
        } else if (selectedItem == R.id.mSignInNavigation) {
            // Display SignInFragment
            fragment = SignInFragment.class;
            // Display the SignInFragment title on the ActionBar
            toolBar.setTitle(R.string.title_signin_fragment);
        } else if (selectedItem == R.id.mListNavigation) {
            // Display ListStringFragment
            fragment = ListStringFragment.class;
            // Display the ListStringFragment title on the ActionBar
            toolBar.setTitle(R.string.title_list_fragment);
        } else if (selectedItem == R.id.mGridNavigation) {
            // Display GridViewFragment
            fragment = GridImageFragment.class;
            // Display the GridViewFragment title on the ActionBar
            toolBar.setTitle(R.string.title_grid_fragment);
        }

        if (fragment != null) {
            // Replace the existing Fragment by the new one
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fcvLayout, fragment, bundle)
                    .commit();
        }

        item.setChecked(true);

        // Close the drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}