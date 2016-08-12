package com.uv.smellslikebakin;

import android.util.Log;

public class ListAdapter extends RecyclerAdapter {

   private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        // adapter is set in ListFragment
        mListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        Log.d("ClickDebug","ListAdapter.ListViewHolder.onClick()");
        // call MainActivity's implementation of onRecipeSelected()
        mListener.onListRecipeSelected(index);
    }

}
