package com.hchiriqui.hch_contacto.presentador;

import android.content.Context;

import com.hchiriqui.hch_contacto.iteractor.MedicosIteractorIMP;
import com.hchiriqui.hch_contacto.iteractor.MedicosIteractorInterface;
import com.hchiriqui.hch_contacto.modelos.Medicos;
import com.hchiriqui.hch_contacto.vistas.MedicosViewInterface;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class MedicosPresenterIMP implements MedicosPresenrerInterface {

    private MedicosIteractorInterface medicosIteractorInterface;
    private MedicosViewInterface medicosViewInterface;
    private Context context;

    public MedicosPresenterIMP(MedicosViewInterface medicosViewInterface, Context context) {
        this.medicosIteractorInterface = new MedicosIteractorIMP(context, this);

        this.medicosViewInterface = medicosViewInterface;
        this.context = context;
    }

    @Override
    public void getMedicos() {
        medicosIteractorInterface.getMedicos();
    }

    @Override
    public void getMedicosBy(String especialidad, String nombre) {
        medicosIteractorInterface.getMedicosBy(especialidad,nombre);
    }

    @Override
    public void getEspecialidades() {
        medicosIteractorInterface.getEspecialidades();
    }

    @Override
    public void OnResponse_getall() {
        medicosViewInterface.OnResponse_getall();
    }

    @Override
    public void OnResponse_Error(String error) {
        medicosViewInterface.OnResponse_Error(error);
    }

}
