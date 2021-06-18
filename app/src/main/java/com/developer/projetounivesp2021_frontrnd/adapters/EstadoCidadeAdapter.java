package com.developer.projetounivesp2021_frontrnd.adapters;

import android.view.View;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.holders.EstadoCidadeHolder;

import java.util.ArrayList;

public class EstadoCidadeAdapter extends BaseAdapter<String, EstadoCidadeHolder> {

    public EstadoCidadeAdapter(ArrayList<String> list) {
        super(list);
    }

    @Override
    public int getLayout() {
        return R.layout.list_simple_string;
    }

    @Override
    public boolean onSearch(String s, String search) {
        return s.contains(search);
    }

    @Override
    protected EstadoCidadeHolder getNewViewHolder(View v) {
        return new EstadoCidadeHolder(v);
    }
}
