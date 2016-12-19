package com.alexeymorozua.codesample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.repository.RepositoryDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 06.12.2016.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

  private List<RepositoryDetail> mRepositories = new ArrayList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    RepositoryDetail repositoryDetail = mRepositories.get(position);
    holder.mFullNameRepositoryTextView.setText(repositoryDetail.getFullName());
    holder.mDescriptionRepositoryTextView.setText(repositoryDetail.getDescription());
    holder.mLanguageRepositoryTextView.setText(repositoryDetail.getLanguage());
    holder.mStargazersRepositoryTextView.setText(
        String.valueOf(repositoryDetail.getStargazersCount()));

    String date = holder.mUpdateRepositoryTextView.getContext().getString(R.string.update_on)
        + " "
        + repositoryDetail.getUpdatedAt();
    holder.mUpdateRepositoryTextView.setText(date);
  }

  @Override public int getItemCount() {
    return mRepositories.size();
  }

  public RepositoryDetail getItem(int position) {
    return mRepositories.get(position);
  }

  public void setRepositories(List<RepositoryDetail> repositories) {
    mRepositories = new ArrayList<>(repositories);
    notifyDataSetChanged();
  }

  public void addRepositories(List<RepositoryDetail> repositories) {
    mRepositories.addAll(repositories);
    notifyDataSetChanged();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_item_repository_full_name) TextView mFullNameRepositoryTextView;
    @BindView(R.id.text_item_repository_description) TextView mDescriptionRepositoryTextView;
    @BindView(R.id.text_item_repository_language) TextView mLanguageRepositoryTextView;
    @BindView(R.id.text_item_repository_stargazers_count) TextView mStargazersRepositoryTextView;
    @BindView(R.id.text_item_repository_updated_at) TextView mUpdateRepositoryTextView;

    public ViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
