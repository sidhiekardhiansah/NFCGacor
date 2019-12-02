package com.example.nfc_gacor.adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfc_gacor.HasilTopUpActivity;
import com.example.nfc_gacor.MainActivity;
import com.example.nfc_gacor.ProdukActivity;
import com.example.nfc_gacor.R;
import com.example.nfc_gacor.TapProdukActivity;
import com.example.nfc_gacor.TopUpActivity;
import com.example.nfc_gacor.model.produk.Produk;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Produk> dataItemList;
    //utk membedakan xml

    public ProdukAdapter(List<Produk> dataItemList) {
        this.dataItemList = dataItemList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((Penampung)holder).txtnama.setText("Name       : " + dataItemList.get(position).getNama());
        ((Penampung)holder).txtnama.setText("Nama Menu   : " + dataItemList.get(position).getNamaProduk());
        ((Penampung)holder).txtdeskripsi.setText("Deskripsi  : " + dataItemList.get(position).getDeskripsiProduk());
        ((Penampung)holder).txtharga.setText("Harga   : " + (String.valueOf(dataItemList.get(position).getHarga())));
        ImageView imagerole = ((Penampung) holder).imgproduk;
        Picasso.get().load(dataItemList.get(position).getFoto()).into(imagerole);
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    static class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtnama, txtdeskripsi, txtharga;
        public ImageView imgproduk;
        private Context context;
        Produk produk;
//        Produk produk = new Produk();

        public Penampung(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
            txtnama = (TextView) itemView.findViewById(R.id.txtnama);
            txtdeskripsi = (TextView) itemView.findViewById(R.id.txtdeskripsi);
            txtharga = (TextView) itemView.findViewById(R.id.txtharga);
            imgproduk = (ImageView) itemView.findViewById(R.id.imageproduk);

            produk = createNewProduk();

            imgproduk.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v) {
                    if (v.getId() == R.id.imageproduk) {
                        Intent detailIntent = new Intent(context, TapProdukActivity.class);
                        detailIntent.putExtra(TapProdukActivity.EXTRA_KARYAWAN, (Parcelable) produk);
                        context.startActivity(detailIntent);
                    }
                }
                //Intent intent = new Intent(context, TopUpActivity.class);

            });


            }

        private Produk createNewProduk() {
            Produk produkBaru = new Produk();

            produkBaru.setNamaProduk(txtnama.getText().toString());
            produkBaru.setDeskripsiProduk(txtdeskripsi.getText().toString());
            produkBaru.setHarga(txtharga.getText().toString());

            return produkBaru;
        }




        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " ");
        }
        }




    }


