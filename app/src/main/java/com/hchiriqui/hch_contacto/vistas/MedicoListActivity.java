package com.hchiriqui.hch_contacto.vistas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.hchiriqui.hch_contacto.R;

import com.hchiriqui.hch_contacto.config.StaticError;
import com.hchiriqui.hch_contacto.modelos.Medicos;
import com.hchiriqui.hch_contacto.modelos.adapters.MedicosAdapter;
import com.hchiriqui.hch_contacto.presentador.MedicosPresenrerInterface;
import com.hchiriqui.hch_contacto.presentador.MedicosPresenterIMP;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * An activity representing a list of Medicos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MedicoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MedicoListActivity extends AppCompatActivity implements MedicosViewInterface, FilterDialog.Onfilter, OnDialogResponse{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private MedicosPresenrerInterface medicosPresenrerInterface;
    private Realm realm;
    private RealmResults<Medicos> realmResults;
    private RecyclerView recyclerView;
    private int bandera = 0;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_list);
        realm = Realm.getDefaultInstance();
        realmResults = realm.where(Medicos.class).findAll();
        medicosPresenrerInterface = new MedicosPresenterIMP(this, getApplicationContext());
        medicosPresenrerInterface.getEspecialidades();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bandera == 0){
                    bandera = 1;
                    showDialogFilter();
                    fab.setImageResource(R.drawable.ic_clear_black_24dp);
                    if(mTwoPane){
                        ((CardView) findViewById(R.id.card_detail)).setVisibility(View.GONE);
                    }
                }else{
                    bandera = 0;
                    medicosPresenrerInterface.getMedicos();
                    fab.setImageResource(R.drawable.ic_search_black_24dp);
                    if(mTwoPane){
                        ((CardView) findViewById(R.id.card_detail)).setVisibility(View.GONE);
                    }
                }
            }
        });

        if (findViewById(R.id.medico_detail_container) != null) {

            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.medico_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
        medicosPresenrerInterface.getMedicos();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MedicosAdapter(realmResults.sort("primer_nombre", Sort.ASCENDING), true,this, mTwoPane));
    }
    private void showDialogFilter(){
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.show(getFragmentManager(),"lol");
    }

    @Override
    public void OnResponse_getall() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void OnResponse_Error(String error) {
        StaticError staticError = new StaticError();
        staticError.getError(this,error).show();
    }


    @Override
    public void OnFilterBusqueda(String especialidad , String nombre) {
        medicosPresenrerInterface.getMedicosBy(especialidad,nombre);
    }

    @Override
    public void retryConection() {
        medicosPresenrerInterface.getMedicos();
    }

    @Override
    public void retryBusqueda() {
        showDialogFilter();
    }

    @Override
    public void declineBusqueda() {
        fab.callOnClick();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
