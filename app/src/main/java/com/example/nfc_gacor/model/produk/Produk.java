
package com.example.nfc_gacor.model.produk;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Produk implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("deskripsi_produk")
    @Expose
    private String deskripsiProduk;
    @SerializedName("harga")
    @Expose
    private String harga;
    public final static Creator<Produk> CREATOR = new Creator<Produk>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Produk createFromParcel(Parcel in) {
            return new Produk(in);
        }

        public Produk[] newArray(int size) {
            return (new Produk[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4480642579270510754L;

    protected Produk(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.foto = ((String) in.readValue((String.class.getClassLoader())));
        this.namaProduk = ((String) in.readValue((String.class.getClassLoader())));
        this.deskripsiProduk = ((String) in.readValue((String.class.getClassLoader())));
        this.harga = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Produk() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(foto);
        dest.writeValue(namaProduk);
        dest.writeValue(deskripsiProduk);
        dest.writeValue(harga);
    }

    public int describeContents() {
        return  0;
    }

}
