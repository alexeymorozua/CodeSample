package com.alexeymorozua.codesample.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.repository.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.presenters.RepositoriesPresenter;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.ui.adapters.RepositoriesAdapter;
import com.alexeymorozua.codesample.ui.views.CustomLoadingListItemCreator;
import com.alexeymorozua.codesample.util.DialogFactory;
import com.alexeymorozua.codesample.util.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.paginate.Paginate;
import java.util.List;

/**
 * Created by john on 07.12.2016.
 */

public class RepositoriesFragment extends BaseFragment
    implements RepositoriesView, DialogInterface.OnCancelListener, Paginate.Callbacks {

  @InjectPresenter RepositoriesPresenter mRepositoriesPresenter;

  @BindView(R.id.text_fragment_repositories) TextView mNoRepositoriesTextView;
  @BindView(R.id.progress_fragment_repositories) ProgressBar mRepositoriesProgressBar;
  @BindView(R.id.recycler_view_fragment_repositories) RecyclerView mRepositoriesRecyclerView;

  private Dialog mErrorDialog;
  private RepositoriesAdapter mRepositoriesAdapter;

  private boolean mLoading;
  private int mPage;
  private int mTotalPages;

  public RepositoriesFragment() {
    super(R.layout.fragment_reposotories);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("page", mPage);
    outState.putInt("totalPages", mTotalPages);

  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (savedInstanceState != null) {
      mPage = savedInstanceState.getInt("page");
      mTotalPages = savedInstanceState.getInt("totalPages");
    }

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    mRepositoriesRecyclerView.setLayoutManager(mLayoutManager);
    mRepositoriesRecyclerView.setItemAnimator(new DefaultItemAnimator());

    ItemClickSupport.addTo(mRepositoriesRecyclerView)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mRepositoriesPresenter.showRepositoryDetail(
                mRepositoriesAdapter.getItem(position)));
  }

  @Override public void showError(String message) {
    mErrorDialog = DialogFactory.createGenericErrorDialog(getActivity(), message);
    mErrorDialog.setOnCancelListener(this);
    mErrorDialog.show();
  }

  @Override public void hideError() {
    if (mErrorDialog != null) {
      mErrorDialog.cancel();
    }
  }

  @Override public void setTotalPages(int pages) {
    mTotalPages = pages;
    mPage = 1;
  }

  @Override public void onStartLoading() {
    mNoRepositoriesTextView.setVisibility(View.GONE);
    mRepositoriesRecyclerView.setVisibility(View.GONE);
    mRepositoriesProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void onFinishLoading() {
    mRepositoriesProgressBar.setVisibility(View.GONE);
  }

  @Override public void setRepositories(List<RepositoryDetail> repositories) {
    if (!repositories.isEmpty()) {
      mNoRepositoriesTextView.setVisibility(View.GONE);
      mRepositoriesRecyclerView.setVisibility(View.VISIBLE);

      mRepositoriesAdapter = new RepositoriesAdapter();
      mRepositoriesRecyclerView.setAdapter(mRepositoriesAdapter);
      mRepositoriesAdapter.setRepositories(repositories);

      if (mTotalPages > 1) {
        Paginate.with(mRepositoriesRecyclerView, this)
          .setLoadingListItemCreator(new CustomLoadingListItemCreator())
          .build();
      }

    } else {
      mNoRepositoriesTextView.setVisibility(View.VISIBLE);
    }

  }

  @Override public void addRepositories(List<RepositoryDetail> repositories) {
    mRepositoriesAdapter.addRepositories(repositories);
    mLoading = false;
  }

  @Override public void onCancel(DialogInterface dialogInterface) {
    mRepositoriesPresenter.onErrorCancel();
  }

  @Override public void onDestroyView() {
    if (mErrorDialog != null) {
      mErrorDialog.dismiss();
    }
    super.onDestroyView();
  }

  @Override public void onLoadMore() {
    mLoading = true;
    mPage++;
    mRepositoriesPresenter.loadNextRepositories(mPage);
  }

  @Override public boolean isLoading() {
    return mLoading;
  }

  @Override public boolean hasLoadedAllItems() {
    return mPage == mTotalPages;
  }
}
