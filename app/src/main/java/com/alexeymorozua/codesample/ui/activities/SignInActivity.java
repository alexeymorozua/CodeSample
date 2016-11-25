package com.alexeymorozua.codesample.ui.activities;

import android.os.Bundle;
import com.alexeymorozua.codesample.mvp.views.SignInView;
import com.arellomobile.mvp.MvpAppCompatActivity;

/**
 * Created by john on 24.11.2016.
 */

public class SignInActivity extends MvpAppCompatActivity implements SignInView {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void showProgress() {

  }

  @Override public void hideProgress() {

  }

  @Override public void showError(String message) {

  }

  @Override public void hideError() {

  }

  @Override public void showError(Integer emailError, Integer passwordError) {

  }

  @Override public void successSignIn() {

  }
}
