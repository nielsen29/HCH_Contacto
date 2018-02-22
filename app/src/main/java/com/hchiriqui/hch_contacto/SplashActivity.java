package com.hchiriqui.hch_contacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hchiriqui.hch_contacto.vistas.MedicoListActivity;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_e);
        imageView = (ImageView) findViewById(R.id.img_ini);

        //Picasso.with(getApplicationContext()).load(R.drawable.ic_logo_hch_app_04).into(imageView);

        Intent i = new Intent(this, MedicoListActivity.class);
        Thread logoTiempo = new Thread(){
            @Override
            public void run() {
                try{
                    int tiempo = 0;
                    while (tiempo<3000){
                        sleep(100);
                        tiempo = tiempo + 100;
                        ((ProgressBar) findViewById(R.id.progress)).setProgress(((int)((tiempo * 100)/3000)));
                    }
                    startActivity(i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    finish();
                }
            }
        };
        logoTiempo.start();




    }
}
