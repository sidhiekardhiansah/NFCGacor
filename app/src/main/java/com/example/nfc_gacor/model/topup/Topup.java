
package com.example.nfc_gacor.model.topup;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topup implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("harga")
    @Expose
    private String harga;
    public final static Creator<Topup> CREATOR = new Creator<Topup>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Topup createFromParcel(Parcel in) {
            return new Topup(in);
        }

        public Topup[] newArray(int size) {
            return (new Topup[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1521036079855722701L;

    protected Topup(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.nominal = ((String) in.readValue((String.class.getClassLoader())));
        this.harga = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Topup() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nominal);
        dest.writeValue(harga);
    }

    public int describeContents() {
        return  0;
    }

}
