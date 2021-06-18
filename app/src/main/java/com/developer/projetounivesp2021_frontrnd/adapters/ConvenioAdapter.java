package com.developer.projetounivesp2021_frontrnd.adapters;

import android.view.View;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.holders.ConvenioHolder;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;

import java.util.ArrayList;

public class ConvenioAdapter extends BaseAdapter<Convenio, ConvenioHolder> {

    public ConvenioAdapter(ArrayList<Convenio> list) {
        super(list);
    }

    @Override
    public int getLayout() {
        return R.layout.list_simple_string;
    }

    @Override
    public boolean onSearch(Convenio convenio, String search) {
        return convenio.getNome().contains(search);
    }

    @Override
    protected ConvenioHolder getNewViewHolder(View v) {
        return new ConvenioHolder(v);
    }
}
