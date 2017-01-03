package com.alexeymorozua.codesample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.util.RxBus;
import com.alexeymorozua.codesample.util.RxBusHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by john on 06.12.2016.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  @Inject RxBus mRxBus;

  private List<RepositoryDetail> mRepositories;
  private boolean mMaybeMore;

  private static final int ITEM_LIST = 0;
  private static final int ITEM_LOADING = 1;

  public RepositoriesAdapter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case ITEM_LOADING:
        return ItemLoadingViewHolder.create(parent);
      default:
        return ItemListViewHolder.create(parent);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case ITEM_LIST:
        ItemListViewHolder holderList = ((ItemListViewHolder) holder);
        RepositoryDetail repositoryDetail = mRepositories.get(position);
        holderList.mFullNameRepositoryTextView.setText(repositoryDetail.getFullName());
        holderList.mDescriptionRepositoryTextView.setText(repositoryDetail.getDescription());
        holderList.mLanguageRepositoryTextView.setText(repositoryDetail.getLanguage());
        holderList.mStargazersRepositoryTextView.setText(
            String.valueOf(repositoryDetail.getStargazersCount()));
        String date =
            holderList.mUpdateRepositoryTextView.getContext().getString(R.string.update_on)
                + " "
                + repositoryDetail.getUpdatedAt();
        holderList.mUpdateRepositoryTextView.setText(date);
        return;
      case ITEM_LOADING:
        mRxBus.post(new RxBusHelper.PageRepositories());
    }
  }

  @Override public int getItemCount() {
    return mRepositories.size() + (mMaybeMore ? 1 : 0);
  }

  @Override public int getItemViewType(int position) {
    if (position == mRepositories.size()) {
      return ITEM_LOADING;
    }
    return ITEM_LIST;
  }

  public RepositoryDetail getItem(int position) {
    return mRepositories.get(position);
  }

  public void setRepositories(List<RepositoryDetail> repositories, boolean maybeMore) {
    mMaybeMore = maybeMore;
    mRepositories = new ArrayList<>(repositories);
    notifyDataSetChanged();
  }

  public void addRepositories(List<RepositoryDetail> repositories, boolean maybeMore) {
    mMaybeMore = maybeMore;
    mRepositories.addAll(repositories);
    notifyDataSetChanged();

  }

  public static class ItemListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_item_repository_full_name) TextView mFullNameRepositoryTextView;
    @BindView(R.id.text_item_repository_description) TextView mDescriptionRepositoryTextView;
    @BindView(R.id.text_item_repository_language) TextView mLanguageRepositoryTextView;
    @BindView(R.id.text_item_repository_stargazers_count) TextView mStargazersRepositoryTextView;
    @BindView(R.id.text_item_repository_updated_at) TextView mUpdateRepositoryTextView;

    ItemListViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    static ItemListViewHolder create(ViewGroup parent) {
      return new ItemListViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_repository, parent, false));
    }
  }

  public static class ItemLoadingViewHolder extends RecyclerView.ViewHolder {

    ItemLoadingViewHolder(View itemView) {
      super(itemView);
    }

    static ItemLoadingViewHolder create(ViewGroup parent) {
      return new ItemLoadingViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_custom_loading, parent, false));
    }
  }
}
