package com.dm.bomber.worker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AuthableProxy extends Proxy implements Authenticator {

    private final String credential;

    public AuthableProxy(Proxy.Type type, InetSocketAddress sa, String credential) {
        super(type, sa);

        this.credential = credential;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) {
        if (credential != null)
            return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();

        return null;
    }
}
