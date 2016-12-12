package com.alexeymorozua.codesample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.alexeymorozua.codesample.mvp.presenters.HomePresenter;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.ui.adapters.ViewPagerRepositoriesAdapter;
import com.alexeymorozua.codesample.ui.fragments.RepositoriesFragment;
import com.alexeymorozua.codesample.ui.fragments.SaveRepositoriesFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by john on 24.11.2016.
 */

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter HomePresenter mHomePresenter;

    @BindView(R.id.toolbar_activity_home) Toolbar mToolbar;
    @BindView(R.id.tab_layout_activity_home) TabLayout mTabLayout;
    @BindView(R.id.view_pager_activity_home) ViewPager mViewPager;
    @BindView(R.id.bottom_sheet_activity_home_repository) View mRepositoryBottomSheet;
    @BindView(R.id.floating_button_activity_home) FloatingActionButton
        mLikeRepositoryFloatingActionButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerRepositoriesAdapter adapter =
            new ViewPagerRepositoriesAdapter(getSupportFragmentManager());
        adapter.addFragment(new RepositoriesFragment(),
            getResources().getString(R.string.repositories));
        adapter.addFragment(new SaveRepositoriesFragment(),
            getResources().getString(R.string.save_repositories));
        viewPager.setAdapter(adapter);
    }
}
