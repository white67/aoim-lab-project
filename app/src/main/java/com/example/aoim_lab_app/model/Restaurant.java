package com.example.aoim_lab_app.model;
import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private String name;
    private String address;
    private double rating;
    private int imageResId; // Drawable resource ID

    // Constructor
    public Restaurant(String name, String address, double rating, int imageResId) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Parcelable methods
    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        rating = in.readDouble();
        imageResId = in.readInt(); // Read image resource ID
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(rating);
        dest.writeInt(imageResId); // Write image resource ID
    }

    @Override
    public int describeContents() {
        return 0;
    }
}