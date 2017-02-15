package com.alexeymorozua.codesample.mvp.presenters;

import android.content.Context;
import android.widget.Toast;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.views.TestView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by John on 15.02.2017.
 */

@InjectViewState public class TestPresenter extends BasePresenter<TestView> {
  @Inject Context mContext;

  public TestPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Toast.makeText(mContext, "show", Toast.LENGTH_SHORT).show();
  }

  @Override public void onDestroy() {
    Timber.tag("tag").e("destroy test");
    super.onDestroy();
  }
}
