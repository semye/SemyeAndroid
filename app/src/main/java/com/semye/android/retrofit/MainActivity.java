package com.semye.android.retrofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.semye.android.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        doGet();

    }

    private void doGet() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.baidu.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiServices apiService = retrofit.create(ApiServices.class);
            Call<ResponseBody> service = apiService.getService("com", "222");
            service.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
