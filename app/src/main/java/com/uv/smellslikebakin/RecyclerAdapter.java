package com.uv.smellslikebakin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class RecyclerAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // we need to inflate new View
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return new ListViewHolder(view);
    }

    // every class that extends RecyclerAdapter must provide layout id
    protected abstract int getLayoutId();

    protected abstract void onRecipeSelected(int index);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // holder Views must be updated
        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

    // Provide a reference to the views for each data item
    private class ListViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTextView;
        private ImageView mImageView;
        private int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.itemText);
            mImageView= (ImageView) itemView.findViewById(R.id.itemImage);
            // register this class as clickListener
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            // update mImageView and mTextView to display the right recipe
            mIndex=position;
            mTextView.setText(Recipes.names[position]);
            mImageView.setImageResource(Recipes.resourceIds[position]);
        }

        @Override
        public void onClick(View view) {
            onRecipeSelected(mIndex);
        }
    }
}
