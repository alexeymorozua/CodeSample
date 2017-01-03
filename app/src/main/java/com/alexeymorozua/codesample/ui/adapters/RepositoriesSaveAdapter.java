package com.alexeymorozua.codesample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 02.01.2017.
 */

public class RepositoriesSaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<RepositoryDetail> mRepositories = new ArrayList<>();

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return ItemListViewHolder.create(parent);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ItemListViewHolder holderList = ((ItemListViewHolder) holder);
    RepositoryDetail repositoryDetail = mRepositories.get(position);
    holderList.mFullNameRepositoryTextView.setText(repositoryDetail.getFullName());
    holderList.mDescriptionRepositoryTextView.setText(repositoryDetail.getDescription());
    holderList.mLanguageRepositoryTextView.setText(repositoryDetail.getLanguage());
    holderList.mStargazersRepositoryTextView.setText(
        String.valueOf(repositoryDetail.getStargazersCount()));

    String date = holderList.mUpdateRepositoryTextView.getContext().getString(R.string.update_on)
        + " "
        + repositoryDetail.getUpdatedAt();
    holderList.mUpdateRepositoryTextView.setText(date);
  }

  @Override public int getItemCount() {
    return mRepositories.size();
  }

  public RepositoryDetail getItem(int position) {
    return mRepositories.get(position);
  }

  public void addRepositories(List<RepositoryDetail> repositories) {
    mRepositories.addAll(repositories);
    notifyDataSetChanged();
  }

  public void addRepository(RepositoryDetail repositoryDetail) {
    mRepositories.add(repositoryDetail);
    notifyDataSetChanged();
  }

  public void deleteRepository(RepositoryDetail repositoryDetail) {
    mRepositories.remove(repositoryDetail);
    notifyDataSetChanged();
  }

  public void deleteAllRepositories() {
    mRepositories.clear();
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
}
