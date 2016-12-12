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
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.alexeymorozua.codesample.mvp.presenters.RepositoriesPresenter;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.ui.adapters.RepositoriesAdapter;
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
  private int mPage = 1;
  private int mTotalPages;

  public RepositoriesFragment() {
    super(R.layout.fragment_reposotories);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("page", mPage);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (savedInstanceState != null) {
      mPage = savedInstanceState.getInt("page");
    }

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    mRepositoriesRecyclerView.setLayoutManager(mLayoutManager);
    mRepositoriesRecyclerView.setItemAnimator(new DefaultItemAnimator());

    ItemClickSupport.addTo(mRepositoriesRecyclerView)
        .setOnItemClickListener((recyclerView, position, v) -> {

        });
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
  }

  @Override public void onStartLoading() {
    mNoRepositoriesTextView.setVisibility(View.GONE);
    mRepositoriesProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void onFinishLoading() {
    mRepositoriesProgressBar.setVisibility(View.GONE);
  }

  @Override public void setRepositories(List<Repository> repositories) {
    if (!repositories.isEmpty()) {
      mRepositoriesAdapter = new RepositoriesAdapter();
      mRepositoriesRecyclerView.setAdapter(mRepositoriesAdapter);
      mRepositoriesAdapter.setRepositories(repositories);
      Paginate.with(mRepositoriesRecyclerView, this).addLoadingListItem(true).build();
    } else {
      mNoRepositoriesTextView.setVisibility(repositories.isEmpty() ? View.VISIBLE : View.GONE);
    }

  }

  @Override public void addRepositories(List<Repository> repositories) {
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