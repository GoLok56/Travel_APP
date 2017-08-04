package io.github.golok56.travel.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.golok56.travel.model.User;
import io.github.golok56.travel.view.activity.LoginActivity;
import io.github.golok56.travel.view.fragment.BookFragment;
import io.github.golok56.travel.view.fragment.NotifFragment;
import io.github.golok56.travel.view.fragment.ProfileFragment;

public class DashboardAdapter extends FragmentStatePagerAdapter {

    private Fragment mProfileFragment;

    public DashboardAdapter(FragmentManager fm, User user) {
        super(fm);

        Bundle bundle = new Bundle();
        bundle.putParcelable(LoginActivity.USER_EXTRA, user);

        mProfileFragment = ProfileFragment.getInstance();
        mProfileFragment.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mProfileFragment;
            case 1:
                return BookFragment.getInstance();
            case 2:
                return NotifFragment.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ProfileFragment.TITLE;
            case 1:
                return BookFragment.TITLE;
            case 2:
                return NotifFragment.TITLE;
        }
        return null;
    }
}
