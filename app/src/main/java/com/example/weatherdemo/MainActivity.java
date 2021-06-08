package com.example.weatherdemo;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    InputStream is = null;
    ByteArrayOutputStream message = null;
    Info info;

    TextView city,phrase,tem,tem_max_min,wea_descr,aqi,sunrise,sunset,moonrise,moonset,moonphrase;
    TextView uvIndex,visibility,pressure,humidity,rain,update_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(() -> {
            try {
                Get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bindviews();
        city.setText(info.getCity());
        phrase.setText(info.getData().get(0).get("phrase"));
        tem.setText(info.getData().get(0).get("tem"));
        tem_max_min.setText("最高" + info.getData().get(0).get("tem1") + " 最低" + info.getData().get(0).get("tem2"));
        wea_descr.setText("今天: " + info.getData().get(0).get("narrative"));
        aqi.setText(info.getData().get(0).get("air") + " 一 " + info.getData().get(0).get("air_level"));
        sunrise.setText(info.getData().get(0).get("sunrise"));
        sunset.setText(info.getData().get(0).get("sunset"));
        rain.setText(info.getData().get(0).get("rain") + "%");
        humidity.setText(info.getData().get(0).get("humidity"));
        moonrise.setText(info.getData().get(0).get("moonrise"));
        moonset.setText(info.getData().get(0).get("moonset"));
        moonphrase.setText(info.getData().get(0).get("moonPhrase"));
        pressure.setText(info.getData().get(0).get("pressure") + "百帕");
        visibility.setText(info.getData().get(0).get("visibility"));
        uvIndex.setText(info.getData().get(0).get("uvIndex"));
        update_time.setText("更新时间:" + info.getUpdate_time());
    }

    void bindviews(){
        city = findViewById(R.id.city);
        phrase = findViewById(R.id.phrase);
        tem = findViewById(R.id.tem);
        tem_max_min = findViewById(R.id.tem_max_min);
        wea_descr = findViewById(R.id.wea_descr);
        aqi = findViewById(R.id.aqi);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        uvIndex = findViewById(R.id.uvIndex);
        visibility = findViewById(R.id.visibility);
        pressure = findViewById(R.id.pressure);
        moonrise = findViewById(R.id.moonrise);
        moonset = findViewById(R.id.moonset);
        moonphrase = findViewById(R.id.moonphrase);
        humidity = findViewById(R.id.humidity);
        rain = findViewById(R.id.rain);
        update_time = findViewById(R.id.update_time);
    }
    private void Get() throws IOException {
        String url = "https://v0.yiketianqi.com/api?version=v91&appid=59355953&appsecret=CCy5ixGs&ext=hours&cityid=";
        URL uri = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();


        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("accept", "*/*");

        if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
            message = new ByteArrayOutputStream();
            int len = 0;
            byte buffer[] = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                message.write(buffer, 0, len);
            }
        }
        String result = new String(message.toByteArray());
        info = JSON.parseObject(result, Info.class);
    }
}