package com.alexeymorozua.codesample.ui.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.presenters.SignInPresenter;
import com.alexeymorozua.codesample.mvp.views.SignInView;
import com.alexeymorozua.codesample.util.DialogFactory;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by john on 24.11.2016.
 */

public class SignInActivity extends MvpAppCompatActivity
    implements SignInView, DialogInterface.OnCancelListener {

  @InjectPresenter SignInPresenter mSignInPresenter;

  @BindView(R.id.edit_email) EditText mEmailEditText;
  @BindView(R.id.edit_password) EditText mPasswordEditText;
  @BindView(R.id.button_sign_in) Button mSignInButton;
  @BindView(R.id.progress_sign_in) ProgressBar mSignInProgressBar;
  @BindView(R.id.toolbar_activity_sign_in) Toolbar mToolbar;

  private Dialog mErrorDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    mPasswordEditText.setOnEditorActionListener((textView, id, keyEvent) -> {
      if (id == R.id.login || id == EditorInfo.IME_NULL) {
        attemptLogin();
        return true;
      }
      return false;
    });

    mSignInButton.setOnClickListener(view -> attemptLogin());
  }

  private void attemptLogin() {
    mSignInPresenter.signIn(mEmailEditText.getText().toString(),
        mPasswordEditText.getText().toString());
  }

  private void toggleProgressVisibility(boolean show) {
    mSignInProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showProgress() {
    toggleProgressVisibility(true);
  }

  @Override public void hideProgress() {
    toggleProgressVisibility(false);
  }

  @Override public void showError(String message) {
    mErrorDialog = DialogFactory.createGenericErrorDialog(this, message);
    mErrorDialog.show();
  }

  @Override public void hideError() {
    mErrorDialog.cancel();
  }

  @Override public void showError(Integer emailError, Integer passwordError) {
    if (emailError != null) {
      mEmailEditText.setError(getString(emailError));
    }
    if (passwordError != null) {
      mPasswordEditText.setError(getString(passwordError));
    }
  }

  @Override public void successSignIn() {
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
    this.finishAffinity();
  }

  @Override public void onCancel(DialogInterface dialogInterface) {
    mSignInPresenter.onErrorCancel();
  }
}
