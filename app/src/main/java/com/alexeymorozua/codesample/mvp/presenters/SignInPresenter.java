package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.views.SignInView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by john on 25.11.2016.
 */

@InjectViewState public class SignInPresenter extends BasePresenter<SignInView> {

  public SignInPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }
}
