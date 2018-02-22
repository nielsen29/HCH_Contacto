package com.hchiriqui.hch_contacto.repositorio;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public interface MedicosRepositorioInterface {

    void getMedicos();
    void getEspecialidades();
    void getMedicosBy(String especialidad , String nombre);
}
