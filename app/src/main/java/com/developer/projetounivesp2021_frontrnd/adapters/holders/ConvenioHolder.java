package com.developer.projetounivesp2021_frontrnd.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;

public class ConvenioHolder extends BaseAdapter.BaseHolder<Convenio> {
    private final TextView mItemNome;

    public ConvenioHolder(@NonNull View itemView) {
        super(itemView);
        mItemNome = itemView.findViewById(R.id.list_tvw_string);
    }

    @Override
    protected void bindUnselectViewHolder(Convenio convenio) {
        mItemNome.setText(convenio.getNome());
        mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.black));
    }

    @Override
    protected void bindSelectViewHolder(Convenio convenio) {
        mItemNome.setText(convenio.getNome());
        mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.white));
    }
}
