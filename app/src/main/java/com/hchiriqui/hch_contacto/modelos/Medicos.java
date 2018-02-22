package com.hchiriqui.hch_contacto.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class Medicos extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("cedula")
    @Expose
    private String cedula;

    @SerializedName("primer_nombre")
    @Expose
    private String primer_nombre;

    @SerializedName("segundo_nombre")
    @Expose
    private String segundo_nombre;

    @SerializedName("apellido_paterno")
    @Expose
    private String apellido_paterno;

    @SerializedName("apellido_materno")
    @Expose
    private String apellido_materno;

    @SerializedName("sexo")
    @Expose
    private int sexo;

    @SerializedName("id_especialidades_medicas")
    @Expose
    private Especialidad especialidad;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("celular")
    @Expose
    private String celular;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("extension")
    @Expose
    private String extension;

    @SerializedName("id_nivel")
    @Expose
    private String id_nivel;

    @SerializedName("id_ubicacion")
    @Expose
    private String id_ubicacion;

    @SerializedName("observacion")
    @Expose
    private String observacion;


    public Medicos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public static Especialidad parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        Especialidad especialidad = gson.fromJson(response,Especialidad.class);
        return especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(String id_nivel) {
        this.id_nivel = id_nivel;
    }

    public String getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(String id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
