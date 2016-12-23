package com.alexeymorozua.codesample.mvp.views;

import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by john on 30.11.2016.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {

  void showRepositoryDetail(RepositoryDetail repositoryDetail);

  void hideRepositoryDetail();

  void openRepositoryDetail();

  void closeRepositoryDetail();

  @StateStrategyType(SkipStrategy.class) void selectTab();

  @StateStrategyType(SkipStrategy.class) void signOut();

  @StateStrategyType(SkipStrategy.class) void setEmptyListSearchHistory();
}
