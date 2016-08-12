package com.uv.smellslikebakin;

import android.util.Log;

public class GridAdapter extends RecyclerAdapter {

    private final GridFragment.OnRecipeSelectedInterface mListener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener) {
        // adapter is set in ListFragment
        // Will hold reference to fragment's host (MainActivity)
        mListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grid_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        Log.d("ClickDebug","ListAdapter.ListViewHolder.onClick()");
        // call MainActivity's implementation of onRecipeSelected()
        mListener.onGridRecipeSelected(index);
    }

}