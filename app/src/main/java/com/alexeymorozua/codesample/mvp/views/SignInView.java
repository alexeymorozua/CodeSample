package com.alexeymorozua.codesample.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by john on 25.11.2016.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface SignInView extends MvpView {

  void showProgress();

  void hideProgress();

  void showError(String message);

  void hideError();

  void showError(Integer emailError, Integer passwordError);

  @StateStrategyType(SkipStrategy.class) void successSignIn();
}
