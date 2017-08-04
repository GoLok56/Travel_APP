package io.github.golok56.travel.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.golok56.travel.R;
import io.github.golok56.travel.adapter.BookAdapter;
import io.github.golok56.travel.model.User;
import io.github.golok56.travel.view.activity.LoginActivity;

public class BookFragment extends Fragment {

    public static final String TITLE = "Book";

    private static BookFragment sInstance;

    public BookFragment() {}

    public static BookFragment getInstance(){
        if(sInstance == null){
            sInstance = new BookFragment();
        }
        return sInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        Bundle bundle = getArguments();
        int userid = 0;
        if(bundle != null){
            User user = bundle.getParcelable(LoginActivity.USER_EXTRA);
            if(user != null) {
                userid = user.getId();
            }
        }

        ViewPager vp = (ViewPager) view.findViewById(R.id.vpager_fragment_book);
        vp.setAdapter(new BookAdapter(getFragmentManager(), userid));

        TabLayout tab = (TabLayout) view.findViewById(R.id.tab_fragment_book);
        tab.setupWithViewPager(vp);

        return view;
    }

}
