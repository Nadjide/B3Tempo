package com.example.b3tempo;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import static com.example.b3tempo.MainActivity.edfApi;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.b3tempo.databinding.ActivityHistoryBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private final static String LOG_TAG = HistoryActivity.class.getSimpleName();
    private ActivityHistoryBinding binding;

    List<TempoDate> tempoDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true);
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        binding.tempoHistoryRv.setAdapter(new TempoDateAdapter(tempoDates, this));

        updateTempoHistory();

    }

    private void updateTempoHistory() {
        Call<TempoHistory> call = edfApi.getTempoHistory("2023","2024");
        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                tempoDates.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body()!= null) {
                    tempoDates.addAll(response.body().getTempoDates());
                    binding.tempoHistoryRv.getAdapter().notifyDataSetChanged();
                } else {
                    Log.e(LOG_TAG," call to getTempoHistory() failed with error code "+ response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                Log.e(LOG_TAG," call to getTempoHistory() failed");

            }
        });
    }

}