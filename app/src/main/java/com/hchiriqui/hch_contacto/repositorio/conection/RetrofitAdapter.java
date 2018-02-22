package com.hchiriqui.hch_contacto.repositorio.conection;

import com.google.gson.GsonBuilder;
import com.hchiriqui.hch_contacto.config.Configuracion;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class RetrofitAdapter {

    public ServiceRetrofit getClientService(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept","application/json")
                        .addHeader("Content-Type","multipart/form-data")
                        .build();
                return chain.proceed(newRequest);

            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuracion.SERVER)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                ))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return  retrofit.create(ServiceRetrofit.class);

    }
}
