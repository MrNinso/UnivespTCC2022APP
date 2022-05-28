package com.developer.projetounivesp2021_frontrnd.fragments.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.adapters.ClinicaAdapter;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

import java.util.ArrayList;
import java.util.Objects;

public class ClinicaFragment extends BaseFragment {

    private ClinicaAdapter mAdapter;
    private RecyclerView mListRW;
    private EditText mPesquisa;

    public ClinicaFragment(FragmentManager Mamanger, Bundle b) {
        super(Mamanger, b);

        ArrayList<Clinica> clinicas = (ArrayList<Clinica>) super.mExtras.get(Extras.CLINICAS_ARRAY);
        mAdapter = new ClinicaAdapter(clinicas);
    }

    @Override
    public int getTitle() {
        return R.string.clinicas;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_clinicas;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mListRW = view.findViewById(R.id.cl_rw_clinicas);
        this.mPesquisa = view.findViewById(R.id.cl_edt_search);

        this.mAdapter.setOnItemSelected((v, pos, oldPos) ->
        {}//mConfirmarBtn.setEnabled(true)
        );

        this.mListRW.setAdapter(this.mAdapter);
        this.mListRW.setLayoutManager(new LinearLayoutManager(this.getContext()));

        this.mPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mAdapter.search(editable.toString());
            }
        });
    }

    public interface Extras {
        String CLINICAS_ARRAY = "CLINICAS_ARRAY";
    }
}
