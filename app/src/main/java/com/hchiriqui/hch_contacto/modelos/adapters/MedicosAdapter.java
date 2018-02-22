package com.hchiriqui.hch_contacto.modelos.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hchiriqui.hch_contacto.R;
import com.hchiriqui.hch_contacto.config.Configuracion;
import com.hchiriqui.hch_contacto.modelos.Medicos;
import com.hchiriqui.hch_contacto.vistas.MedicoDetailActivity;
import com.hchiriqui.hch_contacto.vistas.MedicoDetailFragment;
import com.hchiriqui.hch_contacto.vistas.MedicoListActivity;
import com.hchiriqui.hch_contacto.vistas.dummy.DummyContent;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

/**
 * Created by amihealthmel on 02/16/18.
 */

public class MedicosAdapter extends RealmRecyclerViewAdapter<Medicos,MedicosAdapter.ViewHolder> {


    private final MedicoListActivity mParentActivity;
    private final boolean mTwoPane;


    public MedicosAdapter(@Nullable OrderedRealmCollection<Medicos> data, boolean autoUpdate, MedicoListActivity mParentActivity, boolean mTwoPane) {
        super(data, autoUpdate);
        this.mParentActivity = mParentActivity;
        this.mTwoPane = mTwoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medico_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Medicos medicos = (Medicos) getData().get(position);
        holder.nombre.setText(medicos.getPrimer_nombre() + " " + medicos.getApellido_paterno());
        holder.especialidad.setText(medicos.getEspecialidad().getDescripcion());
        Picasso.with(mParentActivity).load(Configuracion.FOTO_URL + medicos.getFoto()).into(holder.avatar);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(MedicoDetailFragment.ARG_ITEM_ID, medicos.getId());
                    MedicoDetailFragment fragment = new MedicoDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.to_replace, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MedicoDetailActivity.class);
                    intent.putExtra(MedicoDetailFragment.ARG_ITEM_ID, medicos.getId());

                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public long getItemId(int index) {
        return super.getItemId(index);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Nullable
    @Override
    public Medicos getItem(int index) {
        return super.getItem(index);
    }

    @Nullable
    @Override
    public OrderedRealmCollection<Medicos> getData() {
        return super.getData();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private TextView especialidad;
        private ImageView avatar;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            especialidad = (TextView) itemView.findViewById(R.id.especialidad);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }


}
