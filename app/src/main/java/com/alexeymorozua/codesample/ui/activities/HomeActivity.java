package com.alexeymorozua.codesample.ui.activities;

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
import com.alexeymorozua.codesample.mvp.data.model.Repository;
import com.alexeymorozua.codesample.mvp.presenters.HomePresenter;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by john on 24.11.2016.
 */

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter HomePresenter mHomePresenter;

    @BindView(R.id.toolbar_activity_home) Toolbar mToolbar;
    @BindView(R.id.text_activity_home_no_repositories) TextView mNoRepositoriesTextView;
    @BindView(R.id.progress_bar_activity_home_repositories) ProgressBar mRepositoriesProgressBar;
    @BindView(R.id.recycler_view_activity_home_repositories) RecyclerView mRepositoriesRecyclerView;
    @BindView(R.id.bottom_sheet_activity_home_repository) View mRepositoryBottomSheet;
    @BindView(R.id.floating_button_activity_home) FloatingActionButton
        mLikeRepositoryFloatingActionButton;


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

    @Override public void showDetails(int position, Repository repository) {

    }

    @Override public void showDetailsContainer() {

    }

    @Override public void signOut() {
        startActivity(new Intent(this, SignInActivity.class));
        finishAffinity();
    }
}
