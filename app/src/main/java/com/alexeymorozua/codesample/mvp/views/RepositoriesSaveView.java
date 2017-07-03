package com.alexeymorozua.codesample.mvp.views;

import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by john on 26.12.2016.
 */

public interface RepositoriesSaveView extends MvpView {

  void addRepository(RepositoryDetail repositoryDetail);

  void deleteRepository(RepositoryDetail repositoryDetail);

  void addAllRepositories(List<RepositoryDetail> repositories);

  @StateStrategyType(AddToEndSingleStrategy.class) void deleteAllSaveRepositories();
}
