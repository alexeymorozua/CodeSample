package com.alexeymorozua.codesample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.presenters.HomePresenter;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.ui.adapters.ViewPagerRepositoriesAdapter;
import com.alexeymorozua.codesample.ui.fragments.RepositoriesFragment;
import com.alexeymorozua.codesample.ui.fragments.RepositoriesSaveFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by john on 24.11.2016.
 */

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

  @InjectPresenter HomePresenter mHomePresenter;

  @BindView(R.id.toolbar_activity_home) Toolbar mToolbar;
  @BindView(R.id.tab_layout_activity_home) TabLayout mTabLayout;
  @BindView(R.id.view_pager_activity_home) ViewPager mViewPager;
  @BindView(R.id.text_bottom_sheet_name_repository) TextView mNameRepositoryTextView;
  @BindView(R.id.text_bottom_sheet_description) TextView mDescriptionTextView;
  @BindView(R.id.image_bottom_sheet_avatar) ImageView mAvatarImageView;
  @BindView(R.id.text_bottom_sheet_owner_login) TextView mOwnerLoginTextView;
  @BindView(R.id.text_bottom_sheet_url) TextView mUrlTextView;
  @BindView(R.id.bottom_sheet_activity_home_repository) View mRepositoryBottomSheet;
  @BindView(R.id.floating_button_activity_home) FloatingActionButton mLikeRepositoryFloatingButton;
  @BindView(R.id.search_view) SearchView mSearchView;

  private BottomSheetBehavior mBottomSheetBehavior;
  private SearchAdapter mSearchAdapter;
  private RepositoryDetail mRepositoryDetail;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    setupViewPager(mViewPager);
    mTabLayout.setupWithViewPager(mViewPager);

    mSearchView.setArrowOnly(false);
    mSearchView.setVoice(false);
    mSearchView.setVersion(SearchView.VERSION_MENU_ITEM);
    mSearchView.setVersionMargins(SearchView.VERSION_MARGINS_MENU_ITEM);
    mSearchView.setTheme(SearchView.THEME_LIGHT, true);
    mSearchView.setHint(R.string.search);

    //hide FloatingActionButton after start activity
    mLikeRepositoryFloatingButton.animate().scaleX(0).scaleY(0).setDuration(0).start();

    mBottomSheetBehavior = BottomSheetBehavior.from(mRepositoryBottomSheet);
    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

    mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {

        switch (newState) {
          case BottomSheetBehavior.STATE_HIDDEN:
            mHomePresenter.hideRepositoryDetail();
            break;
          case BottomSheetBehavior.STATE_COLLAPSED:
            mHomePresenter.closeRepositoryDetail();
            break;
          case BottomSheetBehavior.STATE_EXPANDED:
            mHomePresenter.openRepositoryDetail();
            break;
        }
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
      }
    });

    mLikeRepositoryFloatingButton.setOnClickListener(
        view -> mHomePresenter.saveRepository(mRepositoryDetail));

    mRepositoryBottomSheet.setOnClickListener(view -> {

      switch (mBottomSheetBehavior.getState()) {
        case BottomSheetBehavior.STATE_COLLAPSED:
          mHomePresenter.openRepositoryDetail();
          break;
        case BottomSheetBehavior.STATE_EXPANDED:
          mHomePresenter.closeRepositoryDetail();
          break;
      }
    });

        mSearchAdapter = new SearchAdapter(this);
        mSearchAdapter.addOnItemClickListener((view, position) -> {
            TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
            String query = textView.getText().toString();
            mHomePresenter.startDownloadRepositories(query);
            mSearchView.close(true);
        });
        mSearchView.setAdapter(mSearchAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                mHomePresenter.startDownloadRepositories(query);
                mSearchView.close(true);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
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
      case R.id.menu_search:
        mSearchView.open(true, item);
        return true;
      case R.id.menu_clear_history_search:
        mHomePresenter.clearHistoryDatabase();
        return true;
      default:
        return super.onOptionsItemSelected(item);
        }
  }

  @Override public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mRepositoryDetail = repositoryDetail;
    mNameRepositoryTextView.setText(mRepositoryDetail.getName());
    mDescriptionTextView.setText(mRepositoryDetail.getDescription());
    Picasso.with(this).load(mRepositoryDetail.getAvatarUrl()).into(mAvatarImageView);
    mOwnerLoginTextView.setText(mRepositoryDetail.getLogin());
    mUrlTextView.setText(mRepositoryDetail.getHtmlUrl());

    if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
      mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      mLikeRepositoryFloatingButton.animate().scaleX(1).scaleY(1).setDuration(100).start();
    }
  }

  @Override public void hideRepositoryDetail() {
    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    mLikeRepositoryFloatingButton.animate().scaleX(0).scaleY(0).setDuration(100).start();
  }

  @Override public void openRepositoryDetail() {
    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  @Override public void saveRepository() {
    mLikeRepositoryFloatingButton.setImageDrawable(
        ContextCompat.getDrawable(this, R.drawable.bookmark));
  }

  @Override public void closeRepositoryDetail() {
    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
  }

  @Override public void selectTab() {
    TabLayout.Tab tab = mTabLayout.getTabAt(0);
    if (tab != null) {
      tab.select();
    }
  }

  @Override public void signOut() {
    startActivity(new Intent(this, SignInActivity.class));
    finishAffinity();
  }

  @Override public void setEmptyListSearchHistory() {
    mSearchAdapter.setData(new ArrayList<>());
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerRepositoriesAdapter adapter =
        new ViewPagerRepositoriesAdapter(getSupportFragmentManager());
    adapter.addFragment(new RepositoriesFragment(),
        getResources().getString(R.string.repositories));
    adapter.addFragment(new RepositoriesSaveFragment(),
        getResources().getString(R.string.save_repositories));
    viewPager.setAdapter(adapter);
  }
}
