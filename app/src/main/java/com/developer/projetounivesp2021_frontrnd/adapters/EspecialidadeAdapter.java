package com.developer.projetounivesp2021_frontrnd.adapters;

import android.view.View;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.holders.EspecialidadeHolder;
import com.developer.projetounivesp2021_frontrnd.objects.Especialidade;

import java.util.ArrayList;

public class EspecialidadeAdapter extends BaseAdapter<Especialidade, EspecialidadeHolder> {

    public EspecialidadeAdapter(ArrayList<Especialidade> list) {
        super(list);
    }

    @Override
    public int getLayout() {
        return R.layout.list_simple_string;
    }

    @Override
    public boolean onSearch(Especialidade especialidade, String search) {
        return especialidade.getNome().contains(search) || String.valueOf(especialidade.getEid()).contains(search);
    }

    @Override
    protected EspecialidadeHolder getNewViewHolder(View v) {
        return new EspecialidadeHolder(v);
    }
}
