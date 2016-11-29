package com.alexeymorozua.codesample.mvp.presenters;

import android.text.TextUtils;
import android.util.Base64;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.data.model.User;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import com.alexeymorozua.codesample.mvp.views.SignInView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by john on 25.11.2016.
 */

@InjectViewState public class SignInPresenter extends BasePresenter<SignInView> {

  @Inject GithubService mGithubService;
  @Inject PreferencesHelper mPreferencesHelper;

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

    String credentials = String.format("%s:%s", email, password);
    final String token = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

    Observable<User> userObservable =
        mGithubService.signIn(token).doOnNext(user -> mPreferencesHelper.setToken(token));

    Subscription subscription =
        userObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(user -> {
          getViewState().hideProgress();
          getViewState().successSignIn();
        }, exception -> {
          getViewState().hideProgress();
          getViewState().showError(exception.getMessage());
        });
    unsubscribeOnDestroy(subscription);
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }
}
