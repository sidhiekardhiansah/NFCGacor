
package com.example.nfc_gacor.model.topup;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("topup")
    @Expose
    private List<Topup> topup = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1216725937039278184L;

    protected Data(Parcel in) {
        in.readList(this.topup, (com.example.nfc_gacor.model.topup.Topup.class.getClassLoader()));
    }

    public Data() {
    }

    public List<Topup> getTopup() {
        return topup;
    }

    public void setTopup(List<Topup> topup) {
        this.topup = topup;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(topup);
    }

    public int describeContents() {
        return  0;
    }

}
