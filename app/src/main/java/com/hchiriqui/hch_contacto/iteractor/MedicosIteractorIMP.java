package com.hchiriqui.hch_contacto.iteractor;

import android.content.Context;

import com.hchiriqui.hch_contacto.presentador.MedicosPresenrerInterface;
import com.hchiriqui.hch_contacto.presentador.MedicosPresenterIMP;
import com.hchiriqui.hch_contacto.repositorio.MedicosRepositorioIMP;
import com.hchiriqui.hch_contacto.repositorio.MedicosRepositorioInterface;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class MedicosIteractorIMP implements MedicosIteractorInterface {
    private MedicosRepositorioInterface medicosRepositorioInterface;
    public MedicosIteractorIMP(Context context, MedicosPresenrerInterface medicosPresenrerInterface) {
        medicosRepositorioInterface = new MedicosRepositorioIMP(context, medicosPresenrerInterface);
    }

    @Override
    public void getMedicos() {
        medicosRepositorioInterface.getMedicos();
    }

    @Override
    public void getMedicosBy(String especialidad, String nombre) {
    medicosRepositorioInterface.getMedicosBy(especialidad,nombre);
    }

    @Override
    public void getEspecialidades() {
        medicosRepositorioInterface.getEspecialidades();
    }
}
