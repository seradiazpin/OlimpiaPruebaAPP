package com.pruebamoviles.sergio.pruebaolimpiasergio;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Status extends AppCompatActivity {
    private User user;
    private boolean blue , wifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        user = (User)getIntent().getSerializableExtra("User");
        wifiStatus();
        bluetoothStatus();
        TextView status = (TextView) findViewById(R.id.user);
        status.setText(user.toString());
    }
    private void wifiStatus(){
        TextView mytb = (TextView) findViewById(R.id.wifi);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        wifi = mWifi.isConnected();
        if(wifi){
            mytb.setBackgroundColor(Color.GREEN);
        }else{
            mytb.setBackgroundColor(Color.RED);
        }
    }
    private void bluetoothStatus(){
        TextView mytb = (TextView) findViewById(R.id.bluet);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        blue = mBluetoothAdapter.isEnabled();
        if (blue) {
            mytb.setBackgroundColor(Color.GREEN);
        }else {
            mytb.setBackgroundColor(Color.RED);
        }

    }

    public void save(View v){
        Intent i = new Intent(this, MainActivity.class);
        user.setWifi(wifi);
        user.setBlue(blue);
        new API().execute(user);
        startActivity(i);
    }



    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            try {
                makeRequest((User) arg0[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public static void makeRequest(User user) throws Exception
    {

    }
}
