package io.github.golok56.travel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.golok56.travel.view.fragment.nested.FlightsBookFragment;
import io.github.golok56.travel.view.fragment.nested.HotelBookFragment;
import io.github.golok56.travel.view.fragment.nested.TrainBookFragment;

public class BookAdapter extends FragmentStatePagerAdapter {

    public FlightsBookFragment mFlightBook;

    public BookAdapter(FragmentManager fm) {
        super(fm);
        mFlightBook = new FlightsBookFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return mFlightBook;
            case 1: return HotelBookFragment.getInstance();
            case 2: return TrainBookFragment.getInstance();
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return FlightsBookFragment.TITLE;
            case 1: return HotelBookFragment.TITLE;
            case 2: return TrainBookFragment.TITLE;
        }
        return null;
    }
}
