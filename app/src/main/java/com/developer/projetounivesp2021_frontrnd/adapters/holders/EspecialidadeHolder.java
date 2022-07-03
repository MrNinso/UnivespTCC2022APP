package com.developer.projetounivesp2021_frontrnd.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;
import com.developer.projetounivesp2021_frontrnd.objects.Especialidade;

public class EspecialidadeHolder extends BaseAdapter.BaseHolder<Especialidade>{
    private final TextView mItemNome;

    public EspecialidadeHolder(@NonNull View itemView) {
        super(itemView);

        this.mItemNome = itemView.findViewById(R.id.list_tvw_string);

    }

    @Override
    protected void bindUnselectViewHolder(Especialidade clinica) {
        this.mItemNome.setText(clinica.getNome());
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.black));
    }

    @Override
    protected void bindSelectViewHolder(Especialidade e) {
        this.mItemNome.setText(e.getNome());
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.white));
    }
}
