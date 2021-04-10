package com.weiwu.shooter.network;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {
    OkHttpClient okHttpClient = new OkHttpClient();
    public static void main(String[] args) throws IOException {
        //String requesturl = "https://raw.github.com/square/okhttp/master/README.md";
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

    String run(String url) throws IOException{
        Request request = new Request.Builder().url(url).build(); //装饰模式
        try(Response response = okHttpClient.newCall(request).execute()){
            return response.body().string();
        }
    }
}
