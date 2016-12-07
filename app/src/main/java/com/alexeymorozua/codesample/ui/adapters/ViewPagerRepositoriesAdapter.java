package com.alexeymorozua.codesample.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

/**
 * Created by john on 07.12.2016.
 */

public class ViewPagerRepositoriesAdapter extends FragmentStatePagerAdapter {

  private ArrayList<Fragment> mFragmentList = new ArrayList<>();
  private ArrayList<String> mFragmentTitleList = new ArrayList<>();

  public ViewPagerRepositoriesAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }
}
