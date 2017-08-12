package com.pruebamoviles.sergio.pruebaolimpiasergio;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Photo extends AppCompatActivity {
    private User user;
    private ImageView mImageView;
    private Bitmap img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);
        user = (User)getIntent().getSerializableExtra("User");
        mImageView = (ImageView) findViewById(R.id.mImageView);
        if (savedInstanceState != null){
            img = savedInstanceState.getParcelable("bitmap");
            mImageView.setImageBitmap(img);
        }
        dispatchTakePictureIntent();
        dispatchGaleryIntent();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap",img);
    }

    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int REQUEST_IMAGE_GALERY = 200;
    public void dispatchTakePictureIntent() {
        Button photoButton = (Button) this.findViewById(R.id.photoAction);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            img = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(img);
        }

        if (requestCode == REQUEST_IMAGE_GALERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            try {
                img = decodeUri(this, uri,100);
                mImageView.setImageBitmap(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

    public void dispatchGaleryIntent() {
        Button photoButton = (Button) this.findViewById(R.id.galery);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent galeryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galeryIntent.setType("image/*");
                galeryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(galeryIntent, REQUEST_IMAGE_GALERY);
            }
        });

    }
    private byte[] imgTobyte(Bitmap b){
        int bytes = b.getByteCount();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }

    public void next(View v){
        Intent i = new Intent(this, LocationMap.class);
        user.setImg(imgTobyte(img));
        i.putExtra("User", user);
        startActivity(i);
    }

}
