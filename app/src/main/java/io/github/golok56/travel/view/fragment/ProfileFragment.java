package io.github.golok56.travel.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.golok56.travel.R;
import io.github.golok56.travel.model.User;
import io.github.golok56.travel.util.PreferenceManager;
import io.github.golok56.travel.view.activity.LoginActivity;

public class ProfileFragment extends Fragment {

    public static final String TITLE = "Profile";

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final User user = getArguments().getParcelable(LoginActivity.USER_EXTRA);

        if (user != null) {
            TextView etName = (TextView) view.findViewById(R.id.tv_fragment_profile_user_full_name);
            etName.setText(user.getName());

            TextView etEmail = (TextView) view.findViewById(R.id.tv_fragment_profile_user_email);
            etEmail.setText(user.getEmail());
        }

        view.findViewById(R.id.tv_fragment_profile_logout)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences pref = getActivity()
                                .getSharedPreferences(PreferenceManager.PREF_NAME, Context.MODE_PRIVATE);
                        pref.edit().putBoolean(PreferenceManager.IS_LOGIN, false).apply();
                        startActivity(LoginActivity.getIntent(getActivity()));
                    }
                });

        return view;
    }

}
