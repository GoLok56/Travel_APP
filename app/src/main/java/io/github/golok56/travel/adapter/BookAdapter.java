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

    private Bundle mBundle;

    public BookAdapter(FragmentManager fm, int userId) {
        super(fm);

        mBundle = new Bundle();
        mBundle.putInt(USERID_EXTRA, userId);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment flightBook = new FlightsBookFragment();
                flightBook.setArguments(mBundle);
                return flightBook;
            case 1:
                Fragment hotelBook = new HotelBookFragment();
                hotelBook.setArguments(mBundle);
                return hotelBook;
            case 2:
                Fragment trainBook = new TrainBookFragment();
                trainBook.setArguments(mBundle);
                return trainBook;
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FlightsBookFragment.TITLE;
            case 1:
                return HotelBookFragment.TITLE;
            case 2:
                return TrainBookFragment.TITLE;
        }
        return null;
    }
}
