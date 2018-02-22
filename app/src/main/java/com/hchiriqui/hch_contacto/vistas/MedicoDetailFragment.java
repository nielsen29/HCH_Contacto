package com.hchiriqui.hch_contacto.vistas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hchiriqui.hch_contacto.R;
import com.hchiriqui.hch_contacto.config.Configuracion;
import com.hchiriqui.hch_contacto.modelos.Medicos;
import com.hchiriqui.hch_contacto.vistas.dummy.DummyContent;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 *{@link MedicoListActivity}
 * {@link MedicoDetailActivity}
 *
 */
public class MedicoDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";


    private DummyContent.DummyItem mItem;
    private Realm realm;
    private RealmResults<Medicos> realmResults;
    private Medicos medico;
    private static int id;
    private AppCompatActivity activity;

    private Toolbar toolbar;

    private ViewInterface viewInterface;


    /*******
     *
     *  componentes de la vista
     *
     *************************************************************/

    private TextView especialidad;
    private TextView ceular;
    private TextView telefono;
    private TextView email;
    private TextView ubicacion;
    private TextView nivel;
    private TextView observacion;




    public MedicoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();

        medico = new Medicos();

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            id = getArguments().getInt(ARG_ITEM_ID);

            medico = realm.where(Medicos.class).equalTo("id",id).findFirst();
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));

            activity = (AppCompatActivity) this.getActivity();



            /*if(activity instanceof ViewInterface){
                viewInterface = (ViewInterface) activity;
                viewInterface.setToolbarTitle(medico.getPrimer_nombre() + " " + medico.getSegundo_nombre() + " " + medico.getApellido_paterno() + " " + medico.getApellido_materno());
            }*/

        }
    }

    public void showtoolbar(String titulo){
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        this.activity.setSupportActionBar(toolbar);
        this.activity.getSupportActionBar()  .setTitle(titulo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.medico_detalle_lay, container, false);



        // Show the dummy content as text in a TextView.
        if (medico != null) {

            if(activity.findViewById(R.id.nombre_detail) != null){
                ((CardView) activity.findViewById(R.id.card_detail)).setVisibility(View.VISIBLE);
                ((TextView) activity.findViewById(R.id.nombre_detail)).setText(
                        medico.getPrimer_nombre()
                        + " "
                        + medico.getSegundo_nombre()
                        + " "
                        + medico.getApellido_paterno()
                        + " "
                        + medico.getApellido_materno()
                );
                Picasso.with(activity).load(Configuracion.FOTO_URL + medico.getFoto()).into(((ImageView) activity.findViewById(R.id.avatar_detail)));
            }
            else
            {
                if(((Toolbar) activity.findViewById(R.id.toolbar) != null)){

                    showtoolbar(medico.getPrimer_nombre() + " " + medico.getSegundo_nombre() + " " + medico.getApellido_paterno() + " " + medico.getApellido_materno());

                }
                Picasso.with(activity).load(Configuracion.FOTO_URL + medico.getFoto()).into(((ImageView) activity.findViewById(R.id.avatar)));

            }


            ((TextView) rootView.findViewById(R.id.observacion)).setText(medico.getObservacion());
            ((TextView) rootView.findViewById(R.id.celular)).setText(medico.getCelular());
            ((Button) rootView.findViewById(R.id.btn_llamarCel)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+medico.getCelular()));
                    startActivity(i);
                }
            });
            ((Button) rootView.findViewById(R.id.btn_llamarTelefono)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:+507"+medico.getTelefono()));
                    startActivity(i);
                }
            });

            ((TextView) rootView.findViewById(R.id.email)).setText(medico.getEmail());
            ((TextView) rootView.findViewById(R.id.especialidad)).setText(medico.getEspecialidad().getDescripcion());
            if(!medico.getTelefono().equals("")){
                ((TextView) rootView.findViewById(R.id.telefono)).setText(medico.getTelefono());
                ((TextView) rootView.findViewById(R.id.extension)).setText(medico.getExtension());
                ((LinearLayout) rootView.findViewById(R.id.lay3)).setVisibility(View.VISIBLE);
                ((Button) rootView.findViewById(R.id.btn_llamarTelefono)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:+507"+medico.getTelefono()));
                        startActivity(i);
                    }
                });
            }else{
                ((LinearLayout) rootView.findViewById(R.id.lay3)).setVisibility(View.GONE);
            }

            if(medico.getCelular().equals("")){
                ((LinearLayout) rootView.findViewById(R.id.lay2)).setVisibility(View.GONE);
            }else
            {
                ((LinearLayout) rootView.findViewById(R.id.lay2)).setVisibility(View.VISIBLE);
            }
            ((TextView) rootView.findViewById(R.id.nivel)).setText(medico.getId_nivel());
            ((TextView) rootView.findViewById(R.id.ubicacion)).setText(medico.getId_ubicacion());

        }

        return rootView;
    }
}
