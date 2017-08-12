package com.pruebamoviles.sergio.pruebaolimpiasergio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getUserData(){
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String id = ((EditText)findViewById(R.id.id)).getText().toString();
        String address = ((EditText)findViewById(R.id.address)).getText().toString();
        String city = ((EditText)findViewById(R.id.City)).getText().toString();
        String county = ((EditText)findViewById(R.id.country)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();

        user = new User(name, address, id, city, county, phone);

    }

    public boolean validate (){
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String id = ((EditText)findViewById(R.id.id)).getText().toString();
        String address = ((EditText)findViewById(R.id.address)).getText().toString();
        String city = ((EditText)findViewById(R.id.City)).getText().toString();
        String county = ((EditText)findViewById(R.id.country)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        if (name.matches("") || id.matches("") || address.matches("") ||city.matches("") ||county.matches("") || phone.matches("")) {

            return false;
        }else{
            return true;
        }
    }

    public void next(View v){
        if(validate()){
            Intent i = new Intent(this, Photo.class);
            getUserData();
            i.putExtra("User", user);
            startActivity(i);
        }else {
            Toast.makeText(this, "Llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }



}
