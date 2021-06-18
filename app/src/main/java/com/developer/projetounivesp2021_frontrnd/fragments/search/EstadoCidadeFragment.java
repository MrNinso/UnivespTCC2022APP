package com.developer.projetounivesp2021_frontrnd.fragments.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.SimpleSelectListActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

import org.jetbrains.annotations.NotNull;

public class EstadoCidadeFragment extends BaseFragment {

    private TextView mEstadoBtn, mCidadeBtn, mAvancarBtn;
    private ActivityResultLauncher<Intent> mListEstadoLaucher;
    private ActivityResultLauncher<Intent> mListCidadeLaucher;
    private String mEstado = "", mCidade = "";

    public EstadoCidadeFragment() {
        super();
    }

    public EstadoCidadeFragment(FragmentManager Mamanger, Bundle b) {
        super(Mamanger, b);
    }

    @Override
    public int getTitle() {
        return R.string.estado_cidade;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_estado_cidade;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mEstadoBtn = v.findViewById(R.id.sh_fg_btn_estado);
        this.mCidadeBtn = v.findViewById(R.id.sh_fg_btn_cidade);
        this.mAvancarBtn = v.findViewById(R.id.sh_fg_btn_avancar);

        this.mListCidadeLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onCidade
        );

        this.mListEstadoLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onEstado
        );

        this.mEstadoBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();
            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_ESTADOS
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_estado
            );

            i.putExtras(b);

            this.mListEstadoLaucher.launch(i);
        }));
        this.mCidadeBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();
            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_CIDADES
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_cidade
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_ESTADO,
                    this.mEstado
            );

            i.putExtras(b);

            this.mListCidadeLaucher.launch(i);
        }));
        this.mAvancarBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            viewTools.showSnackBar(v1, "TODO");
        }));
    }

    private void onEstado(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mEstado = (String) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);

            this.mEstadoBtn.setText(this.mEstado);

            this.mCidade = "";
            this.mCidadeBtn.setText(R.string.clique_aqui_para_selecionar_a_cidade);
            this.mCidadeBtn.setVisibility(View.VISIBLE);
            this.mAvancarBtn.setVisibility(View.GONE);
        }
    }

    private void onCidade(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mCidade = (String) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);;

            this.mAvancarBtn.setVisibility(View.VISIBLE);
            this.mCidadeBtn.setText(this.mCidade);
        }
    }
}
