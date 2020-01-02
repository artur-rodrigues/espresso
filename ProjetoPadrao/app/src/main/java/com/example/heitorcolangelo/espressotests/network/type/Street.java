package com.example.heitorcolangelo.espressotests.network.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Artur on 31/12/2019.
 */
public class Street implements Parcelable {

    @Expose
    private Integer number;

    @Expose
    private String name;

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    protected Street(Parcel in) {
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        name = in.readString();
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number);
        }
        dest.writeString(name);
    }
}
