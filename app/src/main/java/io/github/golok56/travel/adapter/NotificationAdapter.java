package io.github.golok56.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.golok56.travel.R;
import io.github.golok56.travel.model.Notification;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    public NotificationAdapter(Context context, ArrayList<Notification> notif) {
        super(context, 0, notif);
    }

    @NonNull
    @Override
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        // Get the data item for this pos
        Notification notif = getItem(pos);

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_notif, parent, false);
        }

        if (notif != null) {
            SparseArrayCompat<String> TEXTVIEW_VALUE = createTextViewMap(notif);
            createTextView(view, TEXTVIEW_VALUE);
        }

        return view;
    }

    private SparseArrayCompat<String> createTextViewMap(Notification notif){
        SparseArrayCompat<String> data = new SparseArrayCompat<>();
        data.put(R.id.tv_item_list_notif_date, notif.getDate());
        data.put(R.id.tv_item_list_notif_description, notif.getDesc());
        data.put(R.id.tv_item_list_notif_price, notif.getPrice());
        return data;
    }

    private  void createTextView(View view, SparseArrayCompat<String> data){
        for (int i = 0; i < data.size(); i++) {
            int id = data.keyAt(i);
            String value = data.get(id);
            TextView tv = (TextView) view.findViewById(id);
            tv.setText(value);
        }
    }

}
