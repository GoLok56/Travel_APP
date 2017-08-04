package io.github.golok56.travel.model;

public class Notification {

    private String mDate;
    private String mDesc;
    private String mPrice;

    public Notification(String date, String desc, String price){
        mDate = date;
        mDesc = desc;
        mPrice = price;
    }

    public String getDate() {
        return mDate;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getDesc() {
        return mDesc;
    }
}
