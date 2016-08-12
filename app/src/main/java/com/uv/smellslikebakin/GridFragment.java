package com.uv.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// used by Tablets
public class GridFragment extends Fragment{

    // Активность, которая хочет обрабатывать клики на этом фрагменте должна реализовать данный интерфейс
    // и внести свою имплементацию нажатия
    public interface OnRecipeSelectedInterface{
        void onGridRecipeSelected(int index);
    }

    private static final String TAG = ListFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GridFragment","onCreateView");

        if(container == null)
            return null;

        View view = inflater.inflate(R.layout.fragment_recyclerview, container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // get OnRecipeSelectedInterface implementation, the fragment's host activity
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        // pass listener activity as constructor to GridAdapter
        GridAdapter gridAdapter = new GridAdapter(listener);

        recyclerView.setAdapter(gridAdapter);

        // each grid_item is 200dp square. calculate how many columns will be depending on device width
        // information about display
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // calculate width dp
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int) (dpWidth / 200);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColumns);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
