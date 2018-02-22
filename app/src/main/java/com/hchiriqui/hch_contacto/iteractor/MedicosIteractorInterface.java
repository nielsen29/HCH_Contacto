package com.hchiriqui.hch_contacto.iteractor;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public interface MedicosIteractorInterface {

    void getMedicos();
    void getMedicosBy(String especialidad , String nombre);
    void getEspecialidades();
}
