package com.developer.projetounivesp2021_frontrnd.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;
import com.developer.projetounivesp2021_frontrnd.objects.PlanoSaude;

public class PlanoHolder extends BaseAdapter.BaseHolder<PlanoSaude> {
    private final TextView mItemNome;

    public PlanoHolder(@NonNull View itemView) {
        super(itemView);
        mItemNome = itemView.findViewById(R.id.list_tvw_string);
    }

    @Override
    protected void bindUnselectViewHolder(PlanoSaude planoSaude) {
        mItemNome.setText(planoSaude.getNome());
        mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.black));
    }

    @Override
    protected void bindSelectViewHolder(PlanoSaude planoSaude) {
        mItemNome.setText(planoSaude.getNome());
        mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.white));
    }
}
