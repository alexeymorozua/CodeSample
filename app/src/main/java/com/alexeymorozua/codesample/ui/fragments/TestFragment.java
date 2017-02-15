package com.alexeymorozua.codesample.ui.fragments;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.presenters.TestPresenter;
import com.alexeymorozua.codesample.mvp.views.TestView;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.ArrayList;
import timber.log.Timber;

/**
 * Created by John on 15.02.2017.
 */

public class TestFragment extends BaseFragment implements TestView {

  @InjectPresenter TestPresenter mTestPresenter;

  private ArrayList mArrayList;

  public TestFragment() {
    super(R.layout.fragment_reposotories);
  }

  @Override public void onDetach() {
    Timber.tag("tag").e("onDetach");
    super.onDetach();
  }

  @Override public void onAttach(Context context) {
    Timber.tag("tag").e("onAttach");
    super.onAttach(context);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Timber.tag("tag").e("onCreateView");
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    Timber.tag("tag").e("onViewCreated");
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    Timber.tag("tag").e("onCreate");
    if (mArrayList != null) {
      Timber.tag("tag").e("not null");
    }
    mArrayList = new ArrayList();
    super.onCreate(savedInstanceState);
  }

  @Override public void onStart() {
    Timber.tag("tag").e("onStart");
    super.onStart();
  }

  @Override public void onResume() {
    Timber.tag("tag").e("onResume");
    super.onResume();
  }

  @Override public void onStop() {
    Timber.tag("tag").e("onStop");
    super.onStop();
  }

  @Override public void onDestroyView() {
    Timber.tag("tag").e("onDestroyView");
    super.onDestroyView();
  }

  @Override public void onDestroy() {
    Timber.tag("tag").e("destroy");
    super.onDestroy();
  }
}
