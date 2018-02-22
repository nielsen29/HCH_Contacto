package com.hchiriqui.hch_contacto.repositorio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hchiriqui.hch_contacto.config.StaticError;
import com.hchiriqui.hch_contacto.modelos.Especialidad;
import com.hchiriqui.hch_contacto.modelos.Medicos;
import com.hchiriqui.hch_contacto.presentador.MedicosPresenrerInterface;
import com.hchiriqui.hch_contacto.repositorio.conection.RequestMedico;
import com.hchiriqui.hch_contacto.repositorio.conection.RetrofitAdapter;

import java.util.ArrayList;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class MedicosRepositorioIMP implements MedicosRepositorioInterface {

    private RetrofitAdapter retrofitAdapter;
    private MedicosPresenrerInterface medicosPresenrerInterface;
    private Realm realm;
    private Context context;

    public MedicosRepositorioIMP(Context context, MedicosPresenrerInterface medicosPresenrerInterface) {
        this.medicosPresenrerInterface = medicosPresenrerInterface;
        retrofitAdapter = new RetrofitAdapter();
        this.context = context;
    }

    @Override
    public void getMedicos() {
        Observable<Response<ArrayList<Medicos>>> observable = retrofitAdapter.getClientService().getMedicos();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(arrayListResponse -> {
                    if(arrayListResponse.isSuccessful()){
                        Log.i("CORRECTO", "RxJava2, HTTP CORRECTOOOOOO");
                        insertar_REALM(arrayListResponse.body());
                    }else{
                        Log.i("ERROR", "RxJava2, HTTP ERROR"+ arrayListResponse.errorBody().string());
                        medicosPresenrerInterface.OnResponse_Error(StaticError.CONEXION);
                    }
                }, throwable -> {
                    medicosPresenrerInterface.OnResponse_Error(StaticError.CONEXION);
                    Log.i("ERROR", "RxJava2, HTTP Error: " + throwable.getMessage());
                    //cinturaPresenter.OnErrorResponse(throwable.getMessage());

                });

    }

    @Override
    public void getEspecialidades() {

        Observable<Response<ArrayList<Especialidad>>> observable = retrofitAdapter.getClientService().getEspecialidades();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(arrayListResponse -> {
                    if(arrayListResponse.isSuccessful()){
                        Log.i("CORRECTO", "RxJava2, HTTP CORRECTOOOOOO");
                        insertar_Realm_Especialidad(arrayListResponse.body());
                    }else{
                        Log.i("ERROR", "RxJava2, HTTP ERROR"+ arrayListResponse.errorBody().string());
                        Toast.makeText(context,"Error : "+ arrayListResponse.errorBody().string(),Toast.LENGTH_LONG).show();
                        //cinturaPresenter.OnErrorResponse(arrayListResponse.errorBody().string());
                    }
                }, throwable -> {
                    Log.i("ERROR", "RxJava2, HTTP Error: " + throwable.getMessage());
                    //cinturaPresenter.OnErrorResponse(throwable.getMessage());

                });

    }

    @Override
    public void getMedicosBy(String especialidad, String nombre) {

        Observable<Response<ArrayList<Medicos>>> observable = retrofitAdapter.getClientService().getMedicosby(especialidad,nombre);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(arrayListResponse -> {
                    if(arrayListResponse.isSuccessful()){
                        if (arrayListResponse.body().size() == 0) {
                            medicosPresenrerInterface.OnResponse_Error(StaticError.VACIO);
                        }else {
                            Log.i("CORRECTO", "RxJava2, HTTP CORRECTOOOOOO");
                            realm = Realm.getDefaultInstance();
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.where(Medicos.class).findAll().deleteAllFromRealm();
                                }
                            });
                            realm.close();
                            insertar_REALM(arrayListResponse.body());
                        }
                    }else{
                        Log.i("ERROR", "RxJava2, HTTP ERROR"+ arrayListResponse.errorBody().string());
                        medicosPresenrerInterface.OnResponse_Error(StaticError.CONEXION);
                    }
                }, throwable -> {
                    medicosPresenrerInterface.OnResponse_Error(StaticError.CONEXION);
                    Log.i("ERROR", "RxJava2, HTTP Error: " + throwable.getMessage());
                    //cinturaPresenter.OnErrorResponse(throwable.getMessage());
                });


    }

    public void insertar_Realm_Especialidad(ArrayList<Especialidad> especialidades){
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(especialidades);
            }
        });
        realm.close();
    }


    public void insertar_REALM(final ArrayList<Medicos> medidas){
        int x = 0;
        final ArrayList<Integer> id = new ArrayList<>();
        final boolean encontrado = false;

        this.realm = Realm.getDefaultInstance();
        RealmResults<Medicos> realmResults = realm.where(Medicos.class).findAll();
        if(realmResults.isEmpty()){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(medidas);
                }
            });
            realm.close();


            medicosPresenrerInterface.OnResponse_getall();
        }else{

            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(medidas);
                }
            });
            realm.close();
            Realm realmTo = Realm.getDefaultInstance();
            RealmResults<Medicos> result = realmTo.where(Medicos.class).findAll();


            while (x == 0){
                for (int i = 0; i < result.size(); i++) {
                    int busco = result.get(i).getId();

                    for (int j = 0; j < medidas.size() ; j++) {
                        if(medidas.get(j).getId() == busco){
                            x = 1;
                            //Toast.makeText(context,result.get(i).getId(),Toast.LENGTH_LONG).show();
                        }

                    }
                    if(x == 0){
                        id.add(busco);
                    }
                    x = 0;

                }
                x =1;
            }

            if(id.isEmpty()){

                //COMUNICAR SE CARGARON LOS DATOS


            }else{

                //presenterHta.response("Sincronizando datos...!");

                for (int i = 0; i < id.size() ; i++) {
                    Realm realmR = Realm.getDefaultInstance();
                    final int finalI = i;
                    realmR.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.where(Medicos.class).equalTo("id",id.get(finalI)).findFirst().deleteFromRealm();
                        }
                    });
                    realmR.close();
                }
            }
            medicosPresenrerInterface.OnResponse_getall();

        }


    }
}
