package com.uv.smellslikebakin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends LoggingFragment {

    // an interface activity must extend in order to handle fragment clicks
    public interface OnRecipeSelectedInterface{
        void onListRecipeSelected(int index);
    }

    private static final String TAG = ListFragment.class.getSimpleName();

    @Nullable // return value can be null.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LoggingFragment.TAG,"onCreateView");
        // ViewGroup - where a new view will be added
        // ACHTUNG! DO NOT ADD MANUALLY. IT WILL BE DONE AUTOMATICALLY

        // Capture interface implementation (activity extends our interface)
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        // можно также использовать context в методе onAttach()

        // If container is null, then this Fragment won't be attached anywhere and viewed. return null
        if(container == null)
            return null;

        // inflater and container are passed to inflate our new view and assign it to container as child
        // however we do not want to assign a parent for it, thus use false as 3rd parameter
        View view = inflater.inflate(R.layout.fragment_recyclerview, container,false);

        // retreive recyclerView from fragment_recyclerview
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // pass host activity as a listener to listAdapter
        ListAdapter listAdapter = new ListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        // fragments can access the activity they are atteched to by getActivity()
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
