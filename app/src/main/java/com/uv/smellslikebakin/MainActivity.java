package com.uv.smellslikebakin;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends LoggingActivity
        implements ListFragment.OnRecipeSelectedInterface,
        GridFragment.OnRecipeSelectedInterface{

    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    /* In order to receive event callbacks from the fragment, the activity that hosts it
         must implement the interface defined in the fragment class. */
    // for PHONE
    @Override
    public void onListRecipeSelected(int index) {
        Log.d("ClickDebug","MainActivity.onRecipeSelected()");

        ViewPagerFragment fragment = new ViewPagerFragment();

        // use bundles to pass data to the fragments
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX,index);
        // add bundle to the fragment
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // VIEWPAGER_FRAGMENT is an optional tag. Used to distinguish fragments
        fragmentTransaction.replace(R.id.placeHolder, fragment,VIEWPAGER_FRAGMENT);
        // add this transaction to the backstack (works
        fragmentTransaction.addToBackStack(null); // name is optional
        fragmentTransaction.commit();
    }

    // for TABLET
    @Override
    public void onGridRecipeSelected(int index) {
        Log.d("ClickDebug","MainActivity.onGridRecipeSelected()");
        DualPaneFragment fragment = new DualPaneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // any existing fragment were saved to savedInstanceState
        // and are recreated by super.onCreate(...)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // determine if this device is tablet
        // android will refer to the most according config.xml and take value from there
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        Log.d("MainActivity","device is tabled? "+isTablet);

        if(!isTablet){
            /* PHONE */
            // check if there are no ListFragment saved
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment==null) {
                // Create new fragment
                ListFragment fragment = new ListFragment();
                // keeps track of our fragments and manages them
                FragmentManager fragmentManager = getSupportFragmentManager();
                // API for performing a set of Fragment operations.
                FragmentTransaction fragmentTransaction =
                        // Start a series of edit operations on the Fragments associated with this FragmentManager.
                        fragmentManager.beginTransaction();

                // first argument - placeholder for the fragment, e.g. port
                // second argument- fragment object
                fragmentTransaction.add(R.id.placeHolder, fragment,LIST_FRAGMENT);

                fragmentTransaction.commit();
            }
        }else{
            /* TABLET */
            // check if there are no ListFragment saved
            GridFragment savedFragment = (GridFragment) getSupportFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment==null) {
                // Create new fragment
                GridFragment fragment = new GridFragment();
                // keeps track of our fragments and manages them
                FragmentManager fragmentManager = getSupportFragmentManager();
                // API for performing a set of Fragment operations.
                FragmentTransaction fragmentTransaction =
                        // Start a series of edit operations on the Fragments associated with this FragmentManager.
                        fragmentManager.beginTransaction();

                // first argument - placeholder for the fragment, e.g. port
                // second argument- fragment object
                fragmentTransaction.add(R.id.placeHolder, fragment,LIST_FRAGMENT);

                fragmentTransaction.commit();
            }
        }

    }

}
