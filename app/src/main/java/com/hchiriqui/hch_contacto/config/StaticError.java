package com.hchiriqui.hch_contacto.config;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.hchiriqui.hch_contacto.R;
import com.hchiriqui.hch_contacto.vistas.OnDialogResponse;

/**
 * Created by amihealthmel on 02/20/18.
 */

public class StaticError {

    public static final String CONEXION = "1";
    public static final String VACIO = "2";
    public static final String COINCIDENCIAS = "3";
    public static final String CAMPO_VACIO = "4";

    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;

    private OnDialogResponse dialogResponse;

    public StaticError() {
    }

    public AlertDialog getError(Activity activity, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if(activity instanceof OnDialogResponse) {
            dialogResponse = (OnDialogResponse) activity;

            switch (error) {
                case CONEXION:

                    builder.setTitle("Error de Conexion");
                    builder.setMessage("Ocurrio un error de conexion Asegurese de estar conectado a una red wifi o en su defecto que cuente con un plan de datos activos");
                    builder.setPositiveButton(R.string.reintentar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    return builder.create();
                case VACIO:

                    builder.setTitle("Lista vacia");
                    builder.setMessage("No se encontro coincidencias en su busqueda");
                    builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogResponse.declineBusqueda();
                        }
                    });
                    builder.setNegativeButton(R.string.reintentar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogResponse.retryBusqueda();
                        }
                    });
                    return builder.create();
                case COINCIDENCIAS:

                    builder.setTitle("Error de Conexion");
                    builder.setMessage("Ocurrio un error de conexion Asegurese de estar conectado a una red wifi o en su defecto que cuente con un plan de datos activos");
                    return builder.create();
                case CAMPO_VACIO:

                default:
                    return null;

            }
        }
        return  null;

    }
}
