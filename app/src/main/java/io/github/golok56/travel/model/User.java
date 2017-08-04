package io.github.golok56.travel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private int mId;

    private String mEmail;

    private String mUsername;

    private String mName;

    private String mPassword;

    public User(int id, String email, String username, String name, String password){
        mId = id;
        mEmail = email;
        mUsername = username;
        mName = name;
        mPassword = password;
    }

    public User(String email, String username, String name, String password){
        mEmail = email;
        mUsername = username;
        mName = name;
        mPassword = password;
    }

    private User(Parcel in){
        mId = in.readInt();
        mEmail = in.readString();
        mUsername = in.readString();
        mName = in.readString();
        mPassword = in.readString();
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getUsername() {
        return mUsername;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mEmail);
        parcel.writeString(mUsername);
        parcel.writeString(mName);
        parcel.writeString(mPassword);
    }
}
