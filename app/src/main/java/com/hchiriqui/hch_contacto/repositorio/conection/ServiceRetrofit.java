package com.hchiriqui.hch_contacto.repositorio.conection;

import com.hchiriqui.hch_contacto.config.Configuracion;
import com.hchiriqui.hch_contacto.modelos.Especialidad;
import com.hchiriqui.hch_contacto.modelos.Medicos;

import java.util.ArrayList;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by amihealthmel on 02/16/18.
 */

public interface ServiceRetrofit {

    @GET(Configuracion.MEDICOS)
    Observable<Response<ArrayList<Medicos>>> getMedicos();

    @GET(Configuracion.ESPECIALIDADES)
    Observable<Response<ArrayList<Especialidad>>> getEspecialidades();

    @POST(Configuracion.MEDICOS_BY)
    Observable<Response<ArrayList<Medicos>>> getMedicosby(@Query("especialidad") String especialidad, @Query("nombre") String nombre);


}
