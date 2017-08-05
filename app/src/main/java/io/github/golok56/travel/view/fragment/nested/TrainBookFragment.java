package io.github.golok56.travel.view.fragment.nested;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.golok56.travel.R;

public class TrainBookFragment extends Fragment {

    public static final String TITLE = "Kereta";

    public TrainBookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_train_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        final Context context = getContext();
//
//        ArrayList<String> cityList = JSONUtil.extractCities(context);
//        ArrayList<String> classList = JSONUtil.extractClass(context);
//
//        if (cityList != null) {
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, cityList);
//            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//
//            ((Spinner) view.findViewById(R.id.spinner_fragment_train_book_destination))
//                    .setAdapter(adapter);
//
//            ((Spinner) view.findViewById(R.id.spinner_fragment_train_book_origin))
//                    .setAdapter(adapter);
//        }
//
//        if (classList != null) {
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, classList);
//            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//
//            ((Spinner) view.findViewById(R.id.spinner_fragment_train_book_seat_class))
//                    .setAdapter(adapter);
//        }
//
//        NumberPicker npAdult = (NumberPicker) view.findViewById(R.id.np_fragment_train_book_adults_total);
//        npAdult.setMinValue(1);
//        npAdult.setMaxValue(10);
//
//        NumberPicker npKid = (NumberPicker) view.findViewById(R.id.np_fragment_train_book_kids_total);
//        npKid.setMinValue(0);
//        npKid.setMaxValue(10);
//
//        view.findViewById(R.id.btn_fragment_train_book_do_book).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
