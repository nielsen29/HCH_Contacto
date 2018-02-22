package com.hchiriqui.hch_contacto.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class Especialidad extends RealmObject {

    @PrimaryKey
    @SerializedName("id_especialidades_medicas")
    @Expose
    private int id_especialidades_medicas;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Especialidad() {
    }

    public int getId_especialidades_medicas() {
        return id_especialidades_medicas;
    }

    public void setId_especialidades_medicas(int id_especialidades_medicas) {
        this.id_especialidades_medicas = id_especialidades_medicas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
