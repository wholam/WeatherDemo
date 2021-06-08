package com.example.weatherdemo;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class Info {
    private String cityid;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private String update_time;
    private List<HashMap<String, String>> data;
//    private List<HashMap<String, String>> hours;
//
//    public List<HashMap<String, String>> getHours() {
//        return hours;
//    }
//
//    public void setHours(List<HashMap<String, String>> hours) {
//        this.hours = hours;
//    }

    public String getCityid(){
        return cityid;
    }

    public String getCity() {
        return city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setData(List<HashMap<String, String>> data) {
        this.data = data;
    }

    public List<HashMap<String, String>> getData() {
        return data;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

//    public void parasehours(){
//        hours = JSON.parseObject(data.get(0).get("hours"), List.class);
//    }
}
