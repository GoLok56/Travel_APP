package io.github.golok56.travel.view.fragment.nested;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.golok56.travel.R;
import io.github.golok56.travel.util.JSONUtil;

public class HotelBookFragment extends Fragment {

    public static final String TITLE = "Hotel";

    public HotelBookFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hotel_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getContext();

        ArrayList<String> cityList = JSONUtil.extractCities(context);

        if(cityList != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, cityList);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            Spinner destinationSpinner = (Spinner) view.findViewById(R.id.spinner_fragment_hotel_book_destination);
            destinationSpinner.setAdapter(adapter);
        }

        NumberPicker npDuration = (NumberPicker) view.findViewById(R.id.np_fragment_hotel_book_duration);
        npDuration.setMinValue(1);
        npDuration.setMaxValue(10);

        NumberPicker npRoom = (NumberPicker) view.findViewById(R.id.np_fragment_hotel_book_total_room);
        npRoom.setMinValue(1);
        npRoom.setMaxValue(5);

        view.findViewById(R.id.btn_fragment_hotel_book_do_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
