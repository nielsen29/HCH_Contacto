package com.hchiriqui.hch_contacto.repositorio.conection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class RequestMedico {

    @SerializedName("especialidad")
    @Expose
    @Nullable
    private int especialidad;

    @SerializedName("nombre")
    @Expose
    @Nullable
    private String nombre;

    public RequestMedico() {
    }

    public RequestMedico(int especialidad, String nombre) {
        this.especialidad = especialidad;
        this.nombre = nombre;
    }

    @Nullable
    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(@Nullable int especialidad) {
        this.especialidad = especialidad;
    }

    @Nullable
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@Nullable String nombre) {
        this.nombre = nombre;
    }
}
