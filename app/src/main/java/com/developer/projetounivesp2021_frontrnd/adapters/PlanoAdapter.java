package com.developer.projetounivesp2021_frontrnd.adapters;

import android.view.View;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.holders.PlanoHolder;
import com.developer.projetounivesp2021_frontrnd.objects.PlanoSaude;

import java.util.ArrayList;

public class PlanoAdapter extends BaseAdapter<PlanoSaude, PlanoHolder>{

    public PlanoAdapter(ArrayList<PlanoSaude> list) {
        super(list);
    }

    @Override
    public int getLayout() {
        return R.layout.list_simple_string;
    }

    @Override
    public boolean onSearch(PlanoSaude planoSaude, String search) {
        return planoSaude.getNome().contains(search);
    }

    @Override
    protected PlanoHolder getNewViewHolder(View v) {
        return new PlanoHolder(v);
    }
}
