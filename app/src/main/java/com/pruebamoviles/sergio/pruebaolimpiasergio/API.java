package com.pruebamoviles.sergio.pruebaolimpiasergio;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by serad on 12/08/2017.
 */

public class API extends AsyncTask<User, String, String> {
    HttpsURLConnection urlConnection;
    private Context mContext;
    public API(Context context){
        this.mContext = context;
    }
    @Override
    protected String doInBackground(User... args) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://olimpia-prueba-ws.herokuapp.com/users/new");
            JSONObject msg = args[0].toJSON();  //passed in as a parameter to this method
            Log.e("POST", msg.toString());
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty( "Content-Type", "application/json" );
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            OutputStream os = urlConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(msg.toString());
            osw.flush();
            osw.close();
            Log.e("STATUSC","DATA: "+urlConnection.getResponseCode());
        }
        catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, "Datos Guardados", Toast.LENGTH_SHORT).show();
    }


}
