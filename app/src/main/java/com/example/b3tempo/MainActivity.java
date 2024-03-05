package com.example.b3tempo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    IEdfApi edfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofitClient = ApiClient.get();
        if (retrofitClient != null) {
            edfApi = retrofitClient.create(IEdfApi.class);
        } else {
            Log.e(LOG_TAG, "unable to initialize Retrofit client");
            finish();
        }

        Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft("TEMPO");
        Call<TempoDaysColor> call2 = edfApi.getTempoDaysColor("2024-03-04", "TEMPO");
        // Call<TempoDate> call3 = edfApi.getTempoDate("2023", "2024");

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysLeft> call, @NonNull Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d(LOG_TAG, "nb red days = " + tempoDaysLeft.getParamNbJRouge());
                    Log.d(LOG_TAG, "nb white days = " + tempoDaysLeft.getParamNbJBlanc());
                    Log.d(LOG_TAG, "nb blue days = " + tempoDaysLeft.getParamNbJBleu());
                }
            }

            @Override
            public void onFailure(Call<TempoDaysLeft> call, Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysLeft () failed ");
            }
        });

        call2.enqueue(new Callback<TempoDaysColor>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysColor> call, @NonNull Response<TempoDaysColor> response) {
                TempoDaysColor tempoDaysColor = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d(LOG_TAG, "color for today = " + tempoDaysColor.getCouleurJourJ());
                    Log.d(LOG_TAG, "color for tomorrow = " + tempoDaysColor.getCouleurJourJ1());
                }
            }

            @Override
            public void onFailure(Call<TempoDaysColor> call, Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysColor () failed ");
            }
        });

    }
}