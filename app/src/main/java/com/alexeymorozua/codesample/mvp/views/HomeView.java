package com.alexeymorozua.codesample.mvp.views;

import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by john on 30.11.2016.
 */

public interface HomeView extends MvpView {
  @StateStrategyType(SkipStrategy.class) void showDetails(int position, Repository repository);

  void showDetailsContainer();

  void signOut();
}
