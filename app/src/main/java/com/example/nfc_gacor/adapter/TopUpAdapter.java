package com.example.nfc_gacor.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfc_gacor.R;
import com.example.nfc_gacor.model.topup.Topup;


import java.util.List;

public class TopUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Topup> dataItemList;
    //utk membedakan xml

    public TopUpAdapter(List<Topup> dataItemList) {
        this.dataItemList = dataItemList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topup, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((Penampung)holder).txtnama.setText("Name       : " + dataItemList.get(position).getNama());
        ((Penampung)holder).txtnominal.setText("Namanya   : " + dataItemList.get(position).getNominal());
        ((Penampung)holder).txtharga.setText("Alamatnya   : " + dataItemList.get(position).getHarga());
       // ((Penampung)holder).txtumur.setText("Umurnya   : " + (String.valueOf(dataItemList.get(position).getUmur())));
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    static class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtharga, txtnominal;

        public Penampung(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtharga = (TextView) itemView.findViewById(R.id.txtharga);
            txtnominal = (TextView) itemView.findViewById(R.id.txtnominal);


        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " ");
        }
    }

}
