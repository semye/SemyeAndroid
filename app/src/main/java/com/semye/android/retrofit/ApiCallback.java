package com.semye.android.retrofit;



import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        T responseBody = response.body();
        if (response.isSuccessful() && responseBody != null) {
            onSuccess(responseBody);
        } else {
            onFailure(new Throwable(response.message()));
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailure(t);
    }

    public abstract void onSuccess(@NonNull T response);

    public abstract void onFailure(@NonNull Throwable t);
}
