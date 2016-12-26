package com.alexeymorozua.codesample.mvp.presenters;

import android.text.TextUtils;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.views.SignInView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by john on 25.11.2016.
 */

@InjectViewState public class SignInPresenter extends BasePresenter<SignInView> {

  @Inject DataManager mDataManager;

  public SignInPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public void signIn(String email, String password) {
    Integer emailError = null;
    Integer passwordError = null;

    getViewState().showError(null, null);

    if (TextUtils.isEmpty(email)) {
      emailError = R.string.error_field_required;
    }

    if (TextUtils.isEmpty(password)) {
      passwordError = R.string.error_invalid_password;
    }

    if (emailError != null || passwordError != null) {
      getViewState().showError(emailError, passwordError);
      return;
    }

    getViewState().showProgress();

    Subscription subscription = mDataManager.setToken(email, password)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(user -> {
          getViewState().hideProgress();
          getViewState().successSignIn();
        }, throwable -> {
          getViewState().hideProgress();
          getViewState().showError(throwable.getMessage());
        });
    unsubscribeOnDestroy(subscription);
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }
}
