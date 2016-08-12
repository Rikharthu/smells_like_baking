package com.uv.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public abstract class CheckBoxesFragment extends Fragment {

    private static final String KEY_CHECKED_BOXES ="key_checked_boxes" ;
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CheckBoxesFragment","onCreateView");

        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        View view = inflater.inflate(R.layout.fragment_checkboxes,container,false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBoxesLayout);

        // get contents for the checkboxes (depends on implementation of getContents())
        String[] contents = getContents(index);


        mCheckBoxes= new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        // check if there are saved state of CheckBoxes values
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null)
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);

        setupCheckBoxes(contents,linearLayout,checkedBoxes);
        return view;
    }

    /**
     *  Get this fragment's contents depending on the index
     * @param index
     * @return
     */
    public abstract String[] getContents(int index);

    private void setupCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes){
        int i = 0;
        for(String content : contents){
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            // default boolean valuse is "false"
            mCheckBoxes[i].setChecked(checkedBoxes[i]);
            container.addView(mCheckBoxes[i]);
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("CheckBoxesFragment","onSaveInstanceState");
        // store the state of each checkbox
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i=0;
        for(CheckBox checkBox : mCheckBoxes)
            stateOfCheckBoxes[i++]=checkBox.isChecked();
        outState.putBooleanArray(KEY_CHECKED_BOXES,stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
