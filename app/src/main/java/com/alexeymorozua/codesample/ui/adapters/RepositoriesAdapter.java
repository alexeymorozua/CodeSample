package com.alexeymorozua.codesample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 06.12.2016.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

  private List<Repository> mRepositories = new ArrayList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Repository repository = mRepositories.get(position);
    holder.mFullNameRepositoryTextView.setText(repository.getFullName());
    holder.mDescriptionRepositoryTextView.setText(repository.getDescription());
    holder.mLanguageRepositoryTextView.setText(repository.getLanguage());
    holder.mStargazersRepositoryTextView.setText(String.valueOf(repository.getStargazersCount()));

    Format formatter = new SimpleDateFormat("MM.dd.yyyy", java.util.Locale.getDefault());
    String date = holder.mUpdateRepositoryTextView.getContext().getString(R.string.update_on)
        + " "
        + formatter.format(repository.getUpdatedAt());
    holder.mUpdateRepositoryTextView.setText(date);
  }

  @Override public int getItemCount() {
    return mRepositories.size();
  }

  public Repository getItem(int position) {
    return mRepositories.get(position);
  }

  public void setRepositories(List<Repository> repositories) {
    mRepositories = new ArrayList<>(repositories);
    notifyDataSetChanged();
  }

  public void addRepositories(List<Repository> repositories) {
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
