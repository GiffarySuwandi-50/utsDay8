package com.example.utspb01;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspb01.data.response.GithubResponse;
import com.example.utspb01.data.response.GithubUser;
import com.example.utspb01.data.retrofit.ApiConfig;
import com.example.utspb01.data.retrofit.ApiService;
import com.example.utspb01.databinding.ActivityMainBinding;
import com.example.utspb01.ui.GithubUserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GithubUserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.utspb01.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.recycler_view);

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubResponse> call = apiService.searchUsers("giffary");

        call.enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(@NonNull Call<GithubResponse> call, @NonNull Response<GithubResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GithubUser> users = response.body().getUsers();
                    adapter = new GithubUserAdapter(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GithubResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}