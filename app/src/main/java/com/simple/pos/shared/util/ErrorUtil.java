package com.simple.pos.shared.util;
import com.simple.pos.shared.apiresponse.APIResponse;
import com.simple.pos.shared.retrofit.ServiceGenerator;

import java.io.IOException;
import java.lang.annotation.Annotation;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {
    public static APIResponse parseError(Response<?> response) {
        Converter<ResponseBody, APIResponse> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(APIResponse.class, new Annotation[0]);

        APIResponse error;

        try {
            if (response.errorBody() != null) {
                error = converter.convert(response.errorBody());
            } else {
                error = new APIResponse();
            }
        } catch (IOException | NullPointerException e) {
            return new APIResponse();
        }

        return error;
    }
}
