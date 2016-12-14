package com.alexeymorozua.codesample.ui.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alexeymorozua.codesample.R;
import com.paginate.recycler.LoadingListItemCreator;

/**
 * Created by john on 14.12.2016.
 */

public class CustomLoadingListItemCreator implements LoadingListItemCreator {
  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_custom_loading, parent, false);
    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
