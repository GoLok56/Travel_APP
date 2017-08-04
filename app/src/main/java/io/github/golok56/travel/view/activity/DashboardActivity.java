package io.github.golok56.travel.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.golok56.travel.R;
import io.github.golok56.travel.adapter.DashboardAdapter;
import io.github.golok56.travel.model.User;

public class DashboardActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setElevation(0);

        User user = getIntent().getParcelableExtra(LoginActivity.USER_EXTRA);

        ViewPager vp = (ViewPager) findViewById(R.id.vpager_activity_dashboard);
        TabLayout tab = (TabLayout) findViewById(R.id.tab_activity_dashboard);

        vp.setAdapter(new DashboardAdapter(getSupportFragmentManager(), user));
        tab.setupWithViewPager(vp);
    }

    static Intent getIntent(AppCompatActivity activity, User user){
        Intent intent = new Intent(activity, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(LoginActivity.USER_EXTRA, user);
        activity.finish();
        return intent;
    }

}
