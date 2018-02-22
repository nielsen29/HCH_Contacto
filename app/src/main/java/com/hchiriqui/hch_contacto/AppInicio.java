package com.hchiriqui.hch_contacto;

import android.app.Application;
import android.os.SystemClock;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class AppInicio extends MultiDexApplication {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    public void onCreate() {

        Realm.init(getApplicationContext());

        //metodo para configurar la base de datos x defecto

        SetUpRealm();

        //instancia de Realm por defecto

        Realm realm     = Realm.getDefaultInstance();

        //metodo para generar IDS se envia el objeto de Realm y la clase que extiende
        //--RealObj.

        realm           .close();
        super.onCreate();


    }
    private void SetUpRealm(){

        RealmConfiguration realmConfiguration;


        //se configura Realm x defecto

        realmConfiguration      = new RealmConfiguration.Builder()
                .name("HCH_contact")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);


    }
}
