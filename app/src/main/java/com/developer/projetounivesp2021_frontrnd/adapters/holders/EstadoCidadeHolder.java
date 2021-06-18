package com.developer.projetounivesp2021_frontrnd.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;

import org.jetbrains.annotations.NotNull;

public class EstadoCidadeHolder extends BaseAdapter.BaseHolder<String> {

    private final TextView mItemNome;

    public EstadoCidadeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.mItemNome = itemView.findViewById(R.id.list_tvw_string);
    }

    @Override
    protected void bindUnselectViewHolder(String s) {
        this.mItemNome.setText(s);
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.black));
    }

    @Override
    protected void bindSelectViewHolder(String s) {
        this.mItemNome.setText(s);
        this.mItemNome.setTextColor(mItemNome.getResources().getColor(android.R.color.white));
    }
}
