package com.simple.pos.shared.callback;

import android.util.Log;

import androidx.annotation.NonNull;

import com.simple.pos.shared.apiresponse.APIResponse;
import com.simple.pos.shared.apiresponse.APIResponseCollection;
import com.simple.pos.shared.util.ErrorUtil;

import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallback<T> implements Callback<T> {
    private final String tag;
    private final String methodName;
    private final RequestCallback callback;

    public RetrofitCallback(RequestCallback callback, String tag, String methodName) {
        this.callback = callback;
        this.tag = tag;
        this.methodName = methodName;
    }

    @Override
    public void onResponse(@NotNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            Log.d(tag, methodName + ": onResponse: success");
            if (response.body() instanceof APIResponseCollection) {
                callback.requestSuccess(processData(((APIResponseCollection) response.body()).getData()));
            } else {
                callback.requestSuccess(processData(response.body()));
            }
        } else {
            APIResponse apiResponse = ErrorUtil.parseError(response);
            Log.d(tag, methodName + ": onResponse: failed: " + apiResponse.getDescription());
            callback.requestError(apiResponse.getDescription());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Log.d(tag, methodName + ": onFailure: failure: " + t.getMessage());
        callback.requestError(t.getMessage());
    }

    public Object processData(Object data) {
        return data;
    }
}
