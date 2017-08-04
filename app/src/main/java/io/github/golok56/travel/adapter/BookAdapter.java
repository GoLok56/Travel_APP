package io.github.golok56.travel.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.golok56.travel.view.fragment.nested.FlightsBookFragment;
import io.github.golok56.travel.view.fragment.nested.HotelBookFragment;
import io.github.golok56.travel.view.fragment.nested.TrainBookFragment;

public class BookAdapter extends FragmentStatePagerAdapter {

    public static final String USERID_EXTRA = "userid";

    private Fragment mFlightBook;
    private Fragment mHotelBook;
    private Fragment mTrainBook;

    public BookAdapter(FragmentManager fm, int userId) {
        super(fm);

        Bundle bundle = new Bundle();
        bundle.putInt(USERID_EXTRA, userId);

        mFlightBook = new FlightsBookFragment();
        mFlightBook.setArguments(bundle);
        mHotelBook = new HotelBookFragment();
        mHotelBook.setArguments(bundle);
        mTrainBook = new TrainBookFragment();
        mTrainBook.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return mFlightBook;
            case 1: return mHotelBook;
            case 2: return mTrainBook;
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
