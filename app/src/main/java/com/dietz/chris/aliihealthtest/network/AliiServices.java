package com.dietz.chris.aliihealthtest.network;

import com.dietz.chris.aliihealthtest.models.FullTransaction;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 *
 */
public interface AliiServices {

    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded; charset=utf-8",
            "Accept-Language: en, en-us"
    })
    @POST("/api/customer_transactions")
    Call<FullTransaction> getDoctor(@Header("Authorization") String authorization, @FieldMap Map<String, String> map);
}
