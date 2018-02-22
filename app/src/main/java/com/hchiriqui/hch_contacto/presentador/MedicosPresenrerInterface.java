package com.hchiriqui.hch_contacto.presentador;

import com.hchiriqui.hch_contacto.modelos.Medicos;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public interface MedicosPresenrerInterface {
    //SERVER WAY
    void getMedicos();
    void getMedicosBy(String especialidad , String nombre);
    void getEspecialidades();

    //USER WAY
    void OnResponse_getall();
    void OnResponse_Error(String error);
}
