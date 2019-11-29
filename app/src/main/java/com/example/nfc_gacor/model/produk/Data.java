
package com.example.nfc_gacor.model.produk;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("produk")
    @Expose
    private List<Produk> produk = null;
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
    private final static long serialVersionUID = 3503440331559462414L;

    protected Data(Parcel in) {
        in.readList(this.produk, (com.example.nfc_gacor.model.produk.Produk.class.getClassLoader()));
    }

    public Data() {
    }

    public List<Produk> getProduk() {
        return produk;
    }

    public void setProduk(List<Produk> produk) {
        this.produk = produk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(produk);
    }

    public int describeContents() {
        return  0;
    }

}
