package com.uv.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment {

    private int mIndex;
    public static final String KEY_RECIPE_INDEX="recipe_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ViewPagerFragment","onCreateView");
        // retrieve index from passed arguments
        int index =getArguments().getInt(KEY_RECIPE_INDEX);
        //set title to the selected recipe
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);

        // pass selected recipe to our fragments
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX,index);

        final CheckBoxesFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(bundle);

        final CheckBoxesFragment directionsFragment = new DirectionsFragment();
        directionsFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            // we use ChildFragmentManager to deal with fragments inside fragments
            // requires  android.support.v4.app.Fragment instead of android.app.Fragment

            // Returns fragment associated with a specified position
            // во время инициализации будет вызван getCount() раз
            // position будет передачаться от 0 до getCount()-1
            // тем самым загружая все фрагменты, указанные здесь
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                Log.d("ViewPagerFragment","getItem("+position+")");
                if(position==0)
                    return ingredientsFragment;
                return directionsFragment;
            }

            // This method is used by TabLayout
            @Override
            public CharSequence getPageTitle(int position) {
                return position==0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                // number of views available
                // we will have 2 fragments:
                // DirectionsFragment
                // CheckBoxesFragment
                return 2;
            }
        });

        // get reference to TabLayout
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        // link it to our ViewPager
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        // reset title when fragment is no longer visible
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
