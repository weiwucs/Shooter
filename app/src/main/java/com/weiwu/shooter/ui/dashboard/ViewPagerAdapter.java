package com.weiwu.shooter.ui.dashboard;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments, List<String> titles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments != null ? fragments.get(position) : null;
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        Fragment fragment = (Fragment) object;
//        int index = fragments.indexOf(fragment);
//        if(index >= 0){
//            return index;
//        }
//        return POSITION_NONE;
//    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
