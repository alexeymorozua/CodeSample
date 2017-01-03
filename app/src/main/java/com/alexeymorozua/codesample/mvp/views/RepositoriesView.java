package com.alexeymorozua.codesample.mvp.views;

import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by john on 02.12.2016.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface RepositoriesView extends MvpView {

  void showError(String message);

  void hideError();

  @StateStrategyType(SingleStateStrategy.class) void onStartLoading();

  void onFinishLoading();

  void setRepositories(List<RepositoryDetail> repositories, boolean maybeMore);

  @StateStrategyType(AddToEndStrategy.class) void addRepositories(
      List<RepositoryDetail> repositories, boolean maybeMore);
}
