package com.alexeymorozua.codesample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;

/**
 * Created by john on 07.12.2016.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

  private final int layoutId;

  public BaseFragment(int layoutId) {
    this.layoutId = layoutId;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(layoutId, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }
}
