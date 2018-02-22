package com.hchiriqui.hch_contacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hchiriqui.hch_contacto.config.Configuracion;
import com.hchiriqui.hch_contacto.vistas.MedicoListActivity;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout btn_medicos;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_medicos = (LinearLayout) findViewById(R.id.lay_btn_medico);
        imageView = (ImageView) findViewById(R.id.img_btn_med);
        Picasso.with(this).load(Configuracion.FOTO_URL + "/default1.png").into(imageView);
        btn_medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MedicoListActivity.class);
                startActivity(i);
            }
        });

    }
}
