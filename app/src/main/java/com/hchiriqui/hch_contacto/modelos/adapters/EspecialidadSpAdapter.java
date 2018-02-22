package com.hchiriqui.hch_contacto.modelos.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hchiriqui.hch_contacto.R;
import com.hchiriqui.hch_contacto.modelos.Especialidad;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmModel;

/**
 * Created by amihealthmel on 02/19/18.
 */

public class EspecialidadSpAdapter extends RealmBaseAdapter {
    LayoutInflater inflater;

    public EspecialidadSpAdapter(@Nullable OrderedRealmCollection data) {
        super(data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.especialidad_filter_adapter,null);
        ((TextView) view.findViewById(R.id.especialidad_descrip)).setText(((Especialidad)getItem(i)).getDescripcion());
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public RealmModel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void updateData(@Nullable OrderedRealmCollection data) {
        super.updateData(data);
    }

    private class ViewHolder{
        private TextView descrip;
    }

}
