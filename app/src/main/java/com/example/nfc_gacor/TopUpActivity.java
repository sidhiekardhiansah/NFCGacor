package com.example.nfc_gacor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nfc_gacor.APIService.APIClient;
import com.example.nfc_gacor.APIService.APIInterfacesRest;
import com.example.nfc_gacor.adapter.TopUpAdapter;
import com.example.nfc_gacor.model.topup.ModelTopup;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpActivity extends AppCompatActivity {
    RecyclerView rcv;
    TopUpAdapter itemList2;
    ModelTopup modeltopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        rcv = findViewById(R.id.rcv);
        getTopup();
    }

    private void getTopup() {
        final APIInterfacesRest apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        final Call<ModelTopup> nikita = apiInterface.getTopup("5965E901D62F1F7913717CCF9347443B");

        nikita.enqueue(new Callback<ModelTopup>() {
            @Override
            public void onResponse(Call <ModelTopup> call, Response<ModelTopup> response) {
                modeltopup =  response.body();

                if (response.body() != null) {


                   /* List<Row> model = SQLite.select()
                            .from(Row.class)
                            .queryList(); */

                    itemList2 = new TopUpAdapter(modeltopup.getData().getTopup());
                    rcv.setLayoutManager(new LinearLayoutManager(TopUpActivity.this));
                    rcv.setItemAnimator(new DefaultItemAnimator());
                    rcv.setAdapter(itemList2);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TopUpActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TopUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call <ModelTopup> call, Throwable t) {

              /*  List<Player> model = SQLite.select()
                        .from(Player.class)
                        .queryList();

                itemList = new SurveyAdapter(model);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(itemList); */

                Toast.makeText(TopUpActivity.this, "Terjadi gangguan koneksi", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
