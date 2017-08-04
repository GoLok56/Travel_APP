package io.github.golok56.travel.view.fragment.nested;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.github.golok56.travel.R;
import io.github.golok56.travel.adapter.BookAdapter;
import io.github.golok56.travel.database.DBHelper;
import io.github.golok56.travel.database.DBSchema;
import io.github.golok56.travel.util.Formatter;
import io.github.golok56.travel.util.JSONUtil;

public class FlightsBookFragment extends Fragment {

    public static final String TITLE = "Penerbangan";

    private Context mContext;

    private DBHelper mDb;

    private Spinner mSpinnerOrigin;
    private Spinner mSpinnerDestination;
    private Spinner mSpinnerClass;

    private EditText mEtDate;

    private NumberPicker mNpAdult;
    private NumberPicker mNpKid;

    public FlightsBookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flights_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSpinnerOrigin = (Spinner) view.findViewById(R.id.spinner_fragment_flight_book_origin);
        mSpinnerDestination = (Spinner) view.findViewById(R.id.spinner_fragment_flight_book_destination);
        mSpinnerClass = (Spinner) view.findViewById(R.id.spinner_fragment_flight_book_seat_class);
        mEtDate = (EditText) view.findViewById(R.id.et_fragment_flight_book_dates_picker);
        mNpAdult = (NumberPicker) view.findViewById(R.id.np_fragment_flight_book_adults_total);
        mNpKid = (NumberPicker) view.findViewById(R.id.np_fragment_flight_book_kids_total);

        mContext = getContext();

        mDb = new DBHelper(mContext);

        ArrayList<String> cityList = JSONUtil.extractCities(mContext);
        ArrayList<String> classList = JSONUtil.extractClass(mContext);

        if (cityList != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, cityList);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            mSpinnerDestination.setAdapter(adapter);
            mSpinnerOrigin.setAdapter(adapter);
        }

        if (classList != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, classList);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            mSpinnerClass.setAdapter(adapter);
        }

        mNpAdult.setMinValue(1);
        mNpAdult.setMaxValue(10);

        mNpKid.setMinValue(0);
        mNpKid.setMaxValue(10);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                mEtDate.setText(Formatter.getString(myCalendar));
            }
        };

        mEtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        mContext,
                        date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        view.findViewById(R.id.btn_fragment_flight_book_do_book).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showConfirmDialog();
                    }
                }
        );
    }

    private void showConfirmDialog(){
        final String origin = mSpinnerOrigin.getSelectedItem().toString();
        final String destination = mSpinnerDestination.getSelectedItem().toString();
        final String seatClass = mSpinnerClass.getSelectedItem().toString();
        final String date = mEtDate.getText().toString();
        final int adult = mNpAdult.getValue();
        final int kid = mNpKid.getValue();

        if (origin.equals(destination)) {
            showToast("Pilih tujuan anda!");
            return;
        }

        String dateNow = Formatter.getString(new Date());
        if (date.compareTo(dateNow) < 0) {
            showToast("Tanggal tidak valid, silakan pilih ulang!");
            return;
        }

        final String price = JSONUtil.getPrice(
                mContext,
                JSONUtil.FLIGHT_TYPE,
                seatClass,
                origin,
                destination,
                adult,
                kid
        );

        Bundle bundle = getArguments();
        final int userid = bundle.getInt(BookAdapter.USERID_EXTRA);

        new AlertDialog.Builder(mContext)
                .setTitle("Konfirmasi Pembookingan")
                .setMessage("Total yang harus dibayar: " + price + ".\nTekan OK untuk menyetujui!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        JSONObject book = new JSONObject();
                        try {
                            book.put("asal", origin);
                            book.put("tujuan", destination);
                            book.put("kelas", seatClass);
                            book.put("tanggal", date);
                            book.put("jumlah_dewasa", adult);
                            book.put("jumlah_anak", kid);
                            book.put("jumlah_harga", price);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                        ContentValues values = new ContentValues();
                        values.put(DBSchema.TableFlightBook.USERID_COLUMN, userid);
                        values.put(DBSchema.TableFlightBook.INFO_COLUMN, book.toString());
                        SQLiteDatabase wdb = mDb.getWritableDatabase();

                        ContentValues notifValues = new ContentValues();
                        String notif;
                        if (wdb.insert(DBSchema.TableFlightBook.TABLE_NAME, null, values) != -1) {
                            notif = "Berhasil melakukan book untuk melakukan penerbangan dari " +
                                    origin + " menuju " + destination + " pada tanggal " +
                                    date + ". Dengan kelas " + seatClass + ". Jumlah pembelian tiket " +
                                    "dewasa sejumlah " + adult + " tiket dan tiket anak sejumlah " +
                                    kid + ".";
                            showToast("Berhasil melakukan book.");
                        } else {
                            notif = "Gagal melakukan book untuk melakukan penerbangan.";
                            showToast("Terjadi kesalahan saat melakukan book.");
                        }
                        notifValues.put(DBSchema.TableNotifBook.INFO_COLUMN, notif);
                        notifValues.put(DBSchema.TableNotifBook.USERID_COLUMN, userid);
                        notifValues.put(DBSchema.TableNotifBook.PRICE_COLUMN, price);
                        wdb.insert(DBSchema.TableNotifBook.TABLE_NAME, null, notifValues);
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("Batal melakukan pembookingan!");
                    }
                })
                .create().show();
    }

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
