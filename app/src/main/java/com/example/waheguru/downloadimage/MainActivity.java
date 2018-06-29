package com.example.waheguru.downloadimage;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void imageTapped(View view)
    {

        Log.i("Image tapped","Button tapped");
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try
        {
            myImage=task.execute("https://i.ytimg.com/vi/63e9P3ycK2s/maxresdefault.jpg").get();
            imageView.setImageBitmap(myImage);
        }
        catch(Exception exception)
        {

        }

    }
    public class ImageDownloader extends AsyncTask<String,Void ,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try
            {
                URL url= new URL(urls[0]);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            }
            catch(Exception exception)
            {

            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView) findViewById(R.id.lisa_imageView);
    }
}
