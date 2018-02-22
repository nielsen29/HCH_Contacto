package com.hchiriqui.hch_contacto.vistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hchiriqui.hch_contacto.R;
import com.hchiriqui.hch_contacto.modelos.Especialidad;
import com.hchiriqui.hch_contacto.modelos.adapters.EspecialidadSpAdapter;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by amihealthmel on 02/19/18.
 */

public class FilterDialog extends DialogFragment {

    public FilterDialog() {
        super();
    }
    private Realm realm;

    private int id_especialidad = 0;
    private String nombre;
    private Onfilter onfilter;


    public AlertDialog crearfilterDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_layout,null);
        ((Spinner) view.findViewById(R.id.sp_especialidad)).setVisibility(View.GONE);
        ((CheckBox) view.findViewById(R.id.chk_especialidad)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if( ((CheckBox) view.findViewById(R.id.chk_especialidad)).isChecked()){
                    ((Spinner) view.findViewById(R.id.sp_especialidad)).setVisibility(View.VISIBLE);
                    realm = Realm.getDefaultInstance();
                    ((Spinner) view.findViewById(R.id.sp_especialidad)).setAdapter(new EspecialidadSpAdapter(realm.where(Especialidad.class).findAll().sort("descripcion", Sort.ASCENDING)));
                    ((Spinner) view.findViewById(R.id.sp_especialidad)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.i("CORRECTO", "ID, "+ String.valueOf(((Especialidad)adapterView.getItemAtPosition(i)).getId_especialidades_medicas()));
                            id_especialidad = ((Especialidad)adapterView.getItemAtPosition(i)).getId_especialidades_medicas();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Log.i("CORRECTO", "ID, "+ String.valueOf(adapterView.getSelectedItem().toString()));
                            id_especialidad = ((Especialidad)adapterView.getSelectedItem()).getId_especialidades_medicas();
                        }
                    });
                }else{
                    ((Spinner) view.findViewById(R.id.sp_especialidad)).setVisibility(View.GONE);
                    id_especialidad = 0;
                }
            }
        });

        ((EditText) view.findViewById(R.id.filter_nombre_txt)).setVisibility(View.GONE);
        ((EditText) view.findViewById(R.id.filter_nombre_txt)).setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        ((CheckBox) view.findViewById(R.id.chk_nombre)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(((CheckBox) view.findViewById(R.id.chk_nombre)).isChecked()){
                    ((EditText) view.findViewById(R.id.filter_nombre_txt)).setVisibility(View.VISIBLE);
                }else{
                    ((EditText) view.findViewById(R.id.filter_nombre_txt)).setVisibility(View.GONE);
                    nombre = "";
                }
            }
        });

        builder.setView(view);
        builder.setTitle(getString(R.string.filter_title));
        builder.setMessage(getString(R.string.filter_msj));
        builder.setPositiveButton(R.string.buscar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(((CheckBox) view.findViewById(R.id.chk_nombre)).isChecked()){
                    nombre = ((EditText) view.findViewById(R.id.filter_nombre_txt)).getText().toString();
                }else{
                    nombre = "";
                }
                if(!((CheckBox) view.findViewById(R.id.chk_especialidad)).isChecked()){
                    id_especialidad = 0;
                }

                if(nombre == ""){
                    nombre = null;
                }
                if(id_especialidad == 0){
                    onfilter.OnFilterBusqueda(null,nombre);
                }else{
                    onfilter.OnFilterBusqueda(String.valueOf(id_especialidad),nombre);
                }

            }
        });

       return builder.create();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        return  crearfilterDialog();
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if(context instanceof Onfilter ){
            onfilter = (Onfilter) getActivity();
        }
    }



    public interface Onfilter{
        void OnFilterBusqueda(@android.support.annotation.Nullable  String especialidad , @android.support.annotation.Nullable String nombre);
    }
}
