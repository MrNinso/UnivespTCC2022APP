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

import com.developer.projetounivesp2021_frontrnd.FragmentActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.SimpleSelectListActivity;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;
import com.developer.projetounivesp2021_frontrnd.objects.Especialidade;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class ClinicaFragment extends BaseFragment {

    private TextView mEspecialidadeBtn, mClinicaBtn, mAvancarBtn;
    private ActivityResultLauncher<Intent> mListEspecialidadeLaucher;
    private ActivityResultLauncher<Intent> mListClinicaLaucher;
    private Especialidade mEspecialidade;
    private Clinica mClinica;

    public ClinicaFragment(FragmentManager Mamanger, Bundle b) {
        super(Mamanger, b);
    }

    @Override
    public int getTitle() {
        return R.string.especialidades_clinicas;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_clinicas;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mEspecialidadeBtn = v.findViewById(R.id.sh_fg_btn_especialidade);
        this.mClinicaBtn = v.findViewById(R.id.sh_fg_btn_clinica);
        this.mAvancarBtn = v.findViewById(R.id.sh_fg_btn_avancar);

        this.mListEspecialidadeLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onEspecialidade
        );

        this.mListClinicaLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onClinica
        );

        this.mEspecialidadeBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();
            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_ESPECIALIDADES
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_especialidade
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_ESTADO,
                    this.getExtras().getString(Extras.ESTADO)
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_CIDADE,
                    this.getExtras().getString(Extras.CIDADE)
            );

            i.putExtras(b);

            this.mListEspecialidadeLaucher.launch(i);
        }));

        this.mClinicaBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();
            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_CLINICAS
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_clinica_marcar
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_ESTADO,
                    this.getExtras().getString(Extras.ESTADO)
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_CIDADE,
                    this.getExtras().getString(Extras.CIDADE)
            );

            b.putInt(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_ESPECIALIDADE,
                    this.mEspecialidade.getEid()
            );

            i.putExtras(b);

            this.mListClinicaLaucher.launch(i);
        }));

        this.mAvancarBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), FragmentActivity.class);

            i.putExtra(
                    FragmentActivity.Extras.EXTRA_FRAGMENT,
                    FragmentActivity.Extras.FRAGMENT_CONVENIO
            );

            i.putExtra(ConvenioFragment.Extras.ESPECIALIDADE, this.mEspecialidade);
            i.putExtra(ConvenioFragment.Extras.CLINICA, this.mClinica);

            startActivity(i);
        }));
    }

    private void onEspecialidade(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mEspecialidade = (Especialidade) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);

            this.mEspecialidadeBtn.setText(this.mEspecialidade.getNome());
            this.mClinicaBtn.setVisibility(View.VISIBLE);
        }
    }

    private void onClinica(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mClinica = (Clinica) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);;

            this.mAvancarBtn.setVisibility(View.VISIBLE);
            this.mClinicaBtn.setText(this.mClinica.getNome());
        }
    }

    interface Extras {
        String ESTADO = "ESTADO";
        String CIDADE = "CIDADE";
    }
}
