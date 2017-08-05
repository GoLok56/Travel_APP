package io.github.golok56.travel.view.fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.github.golok56.travel.R;
import io.github.golok56.travel.adapter.NotificationAdapter;
import io.github.golok56.travel.database.DBHelper;
import io.github.golok56.travel.database.DBSchema;
import io.github.golok56.travel.model.Notification;
import io.github.golok56.travel.model.User;
import io.github.golok56.travel.util.Formatter;
import io.github.golok56.travel.view.activity.LoginActivity;

public class NotifFragment extends Fragment {

    public static final String TITLE = "Notification";

    private ListView mLvNotif;
    private TextView mTvNotFound;

    private DBHelper mDb;

    private Context mContext;

    private int mUserId;

    public NotifFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notif, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getContext();
        mDb = new DBHelper(mContext);

        Bundle bundle = getArguments();
        User user = bundle.getParcelable(LoginActivity.USER_EXTRA);
        mUserId = 0;
        if (user != null) {
            mUserId = user.getId();
        }

        mLvNotif = (ListView) view.findViewById(R.id.lv_fragment_notif_list_notif);
        mTvNotFound = (TextView) view.findViewById(R.id.tv_fragment_notif_not_found_desc);

        refresh();
    }

    public void refresh() {
        ArrayList<Notification> notifs = getNotifs(mUserId);
        Collections.sort(notifs, new Comparator<Notification>() {
            @Override
            public int compare(Notification notif1, Notification notif2) {
                return notif1.getDesc().compareTo(notif2.getDesc());
            }
        });

        if (notifs.size() == 0) {
            mTvNotFound.setVisibility(View.VISIBLE);
            mLvNotif.setVisibility(View.GONE);
        } else {
            NotificationAdapter adapter = new NotificationAdapter(mContext, notifs);
            mLvNotif.setVisibility(View.VISIBLE);
            mTvNotFound.setVisibility(View.GONE);
            mLvNotif.setAdapter(adapter);
        }
    }

    private ArrayList<Notification> getNotifs(int userid) {
        SQLiteDatabase wdb = mDb.getWritableDatabase();
        String[] column = {
                DBSchema.TableNotifBook.DATE_COLUMN,
                DBSchema.TableNotifBook.INFO_COLUMN,
                DBSchema.TableNotifBook.PRICE_COLUMN
        };
        String selection = DBSchema.TableNotifBook.USERID_COLUMN + "=?";
        String[] selArgs = {String.valueOf(userid)};
        Cursor cursor = wdb.query(
                DBSchema.TableNotifBook.TABLE_NAME,
                column,
                selection,
                selArgs,
                null,
                null,
                null
        );
        ArrayList<Notification> notifs = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()) {
            String desc = cursor.getString(cursor.getColumnIndex(DBSchema.TableNotifBook.INFO_COLUMN));
            String date = cursor.getString(cursor.getColumnIndex(DBSchema.TableNotifBook.DATE_COLUMN));
            String price = cursor.getString(cursor.getColumnIndex(DBSchema.TableNotifBook.PRICE_COLUMN));
            date = Formatter.getString(date);
            notifs.add(new Notification(date, desc, price));
        }

        cursor.close();
        return notifs;
    }

}
