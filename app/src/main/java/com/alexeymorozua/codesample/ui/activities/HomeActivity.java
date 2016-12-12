package com.alexeymorozua.codesample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
    @BindView(R.id.text_activity_home_name_repository) TextView mNameRepository;
    @BindView(R.id.bottom_sheet_activity_home_repository) View mRepositoryBottomSheet;
    @BindView(R.id.floating_button_activity_home) FloatingActionButton
        mLikeRepositoryFloatingButton;

    private BottomSheetBehavior mBottomSheetBehavior;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        mLikeRepositoryFloatingButton.setVisibility(View.GONE);

        mBottomSheetBehavior = BottomSheetBehavior.from(mRepositoryBottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mHomePresenter.hideRepositoryDetail();
                }
            }

            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

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

    @Override public void showRepositoryDetail(Repository repository) {
        mNameRepository.setText(repository.getFullName());
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mLikeRepositoryFloatingButton.setVisibility(View.VISIBLE);
    }

    @Override public void hideRepositoryDetail() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mLikeRepositoryFloatingButton.setVisibility(View.GONE);
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
