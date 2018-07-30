package com.example.administrator.retrofitsample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    Button btnsend;
    EditText txtsend;
    TextView txtreceive;
    Button btnscan;
    Button btnhttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsend = (Button) findViewById(R.id.btn_send);
        btnscan = (Button) findViewById(R.id.btn_scan);
        txtsend = (EditText) findViewById(R.id.txt_send) ;
        txtreceive = (TextView) findViewById(R.id.txt_receive);
        btnhttp = (Button) findViewById(R.id.btn_httpsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtsend.getText().length() > 0){
                    request(txtsend.getText().toString());
                }

            }
        });

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);

            }
        });

        btnhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request_net("");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            String result = data.getExtras().getString("ResultQRCode");
            txtreceive.append(result);
        }
    }

    public void request(final String txt) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<Translation> call = request.getCall(txt);
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {

                txtreceive.append(txt + "--->" + response.body().getContent().getOut().toString());
                txtreceive.append("\r\n");

                txtsend.setText("");
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    public void request_net(final String txt) {
        // 服务请求url
        final String API_URL = "http://192.168.103.2:8000/DemoService/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<Product> call = request.getTest1();
        call.enqueue(new Callback<Product>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                //Product lp = response.body();
                //txtreceive.append(txt + "--->" + response.body().string());
                Product xit = response.body();
                //String testjson = "{\"ProductId\": 10, \"name\": \"李四\",\"CategoryName\": \"王五\", \"Price\": 30}";
                //Gson gson = new Gson();
                //Product p = gson.fromJson(testjson, Product.class);
                Log.d(TAG, "onResponse: " + xit.getName());
                txtreceive.append(txt + "--->" + xit.getName());
                txtreceive.append("\r\n");

                txtsend.setText("");
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Product> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }



}
