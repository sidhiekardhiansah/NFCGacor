package com.example.nfc_gacor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nfc_gacor.APIService.APIClient;
import com.example.nfc_gacor.APIService.APIInterfacesRest;
import com.example.nfc_gacor.adapter.TopUpAdapter;
import com.example.nfc_gacor.model.topup.ModelTopup;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpActivity extends AppCompatActivity implements Listener{
    RecyclerView rcv;
    TopUpAdapter itemList2;
    ModelTopup modeltopup;
    private Button mBtRead;

    private HasilTopUpActivity mNfcReadFragment = (HasilTopUpActivity) getSupportFragmentManager().findFragmentByTag(HasilTopUpActivity.TAG);//(HasilTopUpActivity) getFragmentManager().findFragmentByTag(HasilTopUpActivity.TAG);

    private boolean isDialogDisplayed = false;
    private boolean isRead = false;

    private NfcAdapter mNfcAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        initViews();
        initNFC();


        getTopup();
    }

    private void initViews() {

        rcv = findViewById(R.id.rcv);
        mBtRead = findViewById(R.id.btn_read);

        mBtRead.setOnClickListener(view -> showReadFragment());
    }

    private void initNFC(){

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    private void showReadFragment() {

        mNfcReadFragment = (HasilTopUpActivity) getSupportFragmentManager().findFragmentByTag(HasilTopUpActivity.TAG);

        if (mNfcReadFragment == null) {

            mNfcReadFragment = HasilTopUpActivity.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(),HasilTopUpActivity.TAG);

    }

    @Override
    public void onDialogDisplayed() {
        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {
        isDialogDisplayed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(mNfcAdapter!= null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

//        Log.d(TAG, "onNewIntent: "+intent.getAction());

        if(tag != null) {
            Toast.makeText(this, "NFC Tag Detected", Toast.LENGTH_SHORT).show();
            Ndef ndef = Ndef.get(tag);

            if (isDialogDisplayed) {

                if (isRead) {

                    mNfcReadFragment = (HasilTopUpActivity) getSupportFragmentManager().findFragmentByTag(HasilTopUpActivity.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);

                } else {


                }
            }
        }
    }




    private void getTopup() {
        final APIInterfacesRest apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        final Call<ModelTopup> topup = apiInterface.getTopup("5965E901D62F1F7913717CCF9347443B");

        topup.enqueue(new Callback<ModelTopup>() {
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
