package com.dietz.chris.aliihealthtest.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 *
 */
public class ServicesCreator {

    public static AliiServices getServices() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://staging.bondintelligentcare.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AliiServices.class);
    }
}
