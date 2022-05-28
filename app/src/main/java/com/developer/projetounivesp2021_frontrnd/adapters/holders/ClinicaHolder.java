package com.developer.projetounivesp2021_frontrnd.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;

public class ClinicaHolder extends BaseAdapter.BaseHolder<Clinica>{
    private final TextView mItemNome;

    public ClinicaHolder(@NonNull View itemView) {
        super(itemView);

        this.mItemNome = itemView.findViewById(R.id.list_tvw_string);

    }

    @Override
    protected void bindUnselectViewHolder(Clinica clinica) {
        this.mItemNome.setText(clinica.getNome());
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.black));
    }

    @Override
    protected void bindSelectViewHolder(Clinica clinica) {
        this.mItemNome.setText(clinica.getNome());
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.white));
    }
}
