package com.hchiriqui.hch_contacto.vistas;

import com.hchiriqui.hch_contacto.modelos.Medicos;

import java.util.ArrayList;

/**
 * Created by amihealthmel on 02/16/18.
 */

public interface MedicosViewInterface {

    void OnResponse_getall();
    void OnResponse_Error(String error);
}
