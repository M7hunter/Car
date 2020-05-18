package com.m7awas.car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.m7awas.car.rest.ParseResponse;
import com.m7awas.car.rest.RetrofitClient;

import java.util.List;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCars;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCars = findViewById(R.id.rv_cars);

        getCarsFromServer();
    }

    private void getCarsFromServer() {
        RetrofitClient.getInstance().getAPI().getCars(++page).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful())
                {
                    if (response.body() != null)
                    {
                        try
                        {
                            initCarsRV(ParseResponse.parseCars(response.body()));
                            addScrollListener();
                        } catch (UnsupportedOperationException e)
                        {
                            Toast.makeText(MainActivity.this, "response returns a null value", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "request is not successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "request failed", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void initCarsRV(List<Car> carList) {
        if (carList.isEmpty())
        {
            return;
        }
        rvCars.setHasFixedSize(true);
        rvCars.setLayoutManager(new LinearLayoutManager(this));
        rvCars.setAdapter(new CarsAdapter(carList));
    }

    private void addScrollListener() {
        rvCars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (((LinearLayoutManager) rvCars.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                        == rvCars.getAdapter().getItemCount() - 1)
                {
                    getCarsFromServer();
                    rvCars.removeOnScrollListener(this);
                }
            }
        });
    }
}
