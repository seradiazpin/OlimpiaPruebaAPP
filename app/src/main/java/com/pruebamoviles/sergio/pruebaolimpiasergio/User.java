package com.pruebamoviles.sergio.pruebaolimpiasergio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by seradiazpin on 11/08/2017.
 */

public class User implements Serializable{
    private String name;
    private String address;
    private String id;
    private String city;
    private String country;
    private String phone;
    private double lo;
    private double la;
    private byte [] img;
    private boolean wifi;
    private boolean blue;

    public User(String name, String address, String id, String city, String country, String phone) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLo() {
        return lo;
    }

    public void setLo(double lo) {
        this.lo = lo;
    }

    public double getLa() {
        return la;
    }

    public void setLa(double la) {
        this.la = la;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone=" + phone +
                ", lo=" + lo +
                ", la=" + la +
                ", img=" + Arrays.toString(img) +
                ", wifi=" + wifi +
                ", blue=" + blue +
                '}';
    }

    public JSONObject toJSON() {

        JSONObject jo = new JSONObject();
        try {
            jo.put("name", this.name);
            jo.put("id", this.id);
            jo.put("address", this.address);
            jo.put("city", this.city);
            jo.put("country", this.country);
            jo.put("phone", this.phone);
            jo.put("img", this.getImg());
            jo.put("lo", this.lo);
            jo.put("la", this.la);
            jo.put("wifi", this.wifi);
            jo.put("blue", this.blue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
