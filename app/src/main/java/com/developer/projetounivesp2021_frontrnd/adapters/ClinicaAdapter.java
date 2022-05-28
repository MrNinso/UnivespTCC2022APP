package com.developer.projetounivesp2021_frontrnd.adapters;

import android.view.View;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.holders.ClinicaHolder;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;

import java.util.ArrayList;

public class ClinicaAdapter extends BaseAdapter<Clinica, ClinicaHolder>{
    public ClinicaAdapter(ArrayList<Clinica> list) {
        super(list);
    }

    @Override
    public int getLayout() {
        return R.layout.list_simple_string;
    }

    @Override
    public boolean onSearch(Clinica clinica, String search) {
        return clinica.getNome().contains(search);
    }

    @Override
    protected ClinicaHolder getNewViewHolder(View v) {
        return new ClinicaHolder(v);
    }
}
