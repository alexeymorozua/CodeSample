package com.alexeymorozua.codesample.ui.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.alexeymorozua.codesample.mvp.presenters.HomePresenter;
import com.alexeymorozua.codesample.mvp.presenters.RepositoriesPresenter;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.util.DialogFactory;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by john on 24.11.2016.
 */

public class HomeActivity extends MvpAppCompatActivity
    implements HomeView, RepositoriesView, DialogInterface.OnCancelListener {

    @InjectPresenter HomePresenter mHomePresenter;
    @InjectPresenter RepositoriesPresenter mRepositoriesPresenter;

    @BindView(R.id.toolbar_activity_home) Toolbar mToolbar;
    @BindView(R.id.text_activity_home_no_repositories) TextView mNoRepositoriesTextView;
    @BindView(R.id.progress_bar_activity_home_repositories) ProgressBar mRepositoriesProgressBar;
    @BindView(R.id.recycler_view_activity_home_repositories) RecyclerView mRepositoriesRecyclerView;
    @BindView(R.id.bottom_sheet_activity_home_repository) View mRepositoryBottomSheet;
    @BindView(R.id.floating_button_activity_home) FloatingActionButton
        mLikeRepositoryFloatingActionButton;

    private Dialog mErrorDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_sign_out:
                mHomePresenter.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void showError(String message) {
        mErrorDialog = DialogFactory.createGenericErrorDialog(this, message);
        mErrorDialog.setOnCancelListener(this);
        mErrorDialog.show();
    }

    @Override public void hideError() {
        if (mErrorDialog != null) {
            mErrorDialog.cancel();
        }
    }

    @Override public void onStartLoading() {
        mNoRepositoriesTextView.setVisibility(View.GONE);
        mRepositoriesProgressBar.setVisibility(View.VISIBLE);
    }

    @Override public void onFinishLoading() {
        mRepositoriesProgressBar.setVisibility(View.GONE);
    }

    @Override public void setRepositories(List<Repository> repositories, boolean maybeMore) {

    }

    @Override public void addRepositories(List<Repository> repositories, boolean maybeMore) {

    }

    @Override public void showDetails(int position, Repository repository) {

    }

    @Override public void showDetailsContainer() {

    }

    @Override public void signOut() {
        startActivity(new Intent(this, SignInActivity.class));
        finishAffinity();
    }

    @Override public void onCancel(DialogInterface dialogInterface) {
        mRepositoriesPresenter.onErrorCancel();
    }

    @Override protected void onDestroy() {
        if (mErrorDialog != null) {
            mErrorDialog.dismiss();
        }
        super.onDestroy();
    }
}
