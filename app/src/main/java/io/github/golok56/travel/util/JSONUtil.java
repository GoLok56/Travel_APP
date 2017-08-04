package io.github.golok56.travel.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONUtil {

    private static final String CITIES_FILE = "cities.json";

    private static final String SEAT_CLASS_FILE = "seat_class.json";

    private static String read(Context context, String fileName) throws IOException {
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static ArrayList<String> extractCities(Context context){
        ArrayList<String> cityList = null;
        try {
            String json = read(context, CITIES_FILE);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray cities = jsonObject.getJSONArray("cities");

            int totalCities = cities.length();
            cityList = new ArrayList<>(totalCities);
            for (int i = 0;i < totalCities; ++i) {
                cityList.add(cities.getString(i));
            }
        } catch (IOException | JSONException ex){
            ex.printStackTrace();
        }
        return cityList;
    }

    public static ArrayList<String> extractClass(Context context){
        ArrayList<String> classList = null;
        try {
            String json = read(context, SEAT_CLASS_FILE);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray cities = jsonObject.getJSONArray("class");

            int totalCities = cities.length();
            classList = new ArrayList<>(totalCities);
            for (int i = 0;i < totalCities; ++i) {
                classList.add(cities.getString(i));
            }
        } catch (IOException | JSONException ex){
            ex.printStackTrace();
        }
        return classList;
    }

}
