package com.hchiriqui.hch_contacto.modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by amihealthmel on 02/19/18.
 */

public class Favoritos extends RealmObject {

    @PrimaryKey
    private int id;
    private Medicos medico;
    private boolean favorito;

    public Favoritos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicos getMedico() {
        return medico;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
