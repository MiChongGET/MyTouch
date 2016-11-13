package com.example.administrator.mytouch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowImageView extends AppCompatActivity {

    private ZoomeImageView mZoomeImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_view);
        mZoomeImageView = (ZoomeImageView) findViewById(R.id.iamge);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        mZoomeImageView.setImage(bitmap);
    }
}
