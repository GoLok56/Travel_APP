package io.github.golok56.travel.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.golok56.travel.R;

public class NotifFragment extends Fragment {

    public static final String TITLE = "Notification";

    private static NotifFragment sInstance;

    public NotifFragment() {}

    public static NotifFragment getInstance(){
        if(sInstance == null){
            sInstance = new NotifFragment();
        }
        return sInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

}
