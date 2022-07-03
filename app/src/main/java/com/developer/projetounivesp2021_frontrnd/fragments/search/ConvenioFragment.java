package com.developer.projetounivesp2021_frontrnd.fragments.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;
import com.developer.projetounivesp2021_frontrnd.objects.Especialidade;
import com.developer.projetounivesp2021_frontrnd.objects.PlanoSaude;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class ConvenioFragment extends BaseFragment {
    private Clinica mClinica;
    private Especialidade mEspecialidade;
    private Convenio mConvenio;
    private PlanoSaude mPlano;
    private TextView mConvenioBtn, mPlanoBtn, mAvancarBtn;
    private EditText mCarterinhaEdt;
    private ActivityResultLauncher<Intent> mListConvenioLaucher;
    private ActivityResultLauncher<Intent> mListPlanoLaucher;

    public ConvenioFragment(FragmentManager supportFragmentManager, Bundle extras) {
        super(supportFragmentManager, extras);

        this.mEspecialidade = (Especialidade) extras.get(Extras.ESPECIALIDADE);
        this.mClinica = (Clinica) extras.get(Extras.CLINICA);
    }

    @Override
    public int getTitle() {
        return R.string.convenio;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_convenio;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mConvenioBtn = v.findViewById(R.id.sh_fg_btn_convenio);
        this.mPlanoBtn = v.findViewById(R.id.sh_fg_btn_plano);
        this.mAvancarBtn = v.findViewById(R.id.sh_fg_btn_avancar);
        this.mCarterinhaEdt = v.findViewById(R.id.sh_fg_edt_carterinha);

        this.mListConvenioLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onConvenio
        );

        this.mListPlanoLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onPlano
        );

        this.mConvenioBtn.setOnClickListener(viewTools.btnClick(500, view -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();

            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_CONVENIOS
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_convenio
            );

            i.putExtras(b);

            this.mListConvenioLaucher.launch(i);
        }));

        this.mPlanoBtn.setOnClickListener(viewTools.btnClick(500, view -> {
            Intent i = new Intent(this.getContext(), SimpleSelectListActivity.class);
            Bundle b = new Bundle();

            b.putByte(
                    SimpleSelectListActivity.Extras.EXTRA_LIST,
                    SimpleSelectListActivity.Extras.LIST_PLANOS
            );

            b.putInt(
                    SimpleSelectListActivity.Extras.EXTRA_TITLE,
                    R.string.selecione_plano
            );

            b.putString(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_CONVENIO,
                    this.mConvenio.getNome()
            );

            i.putExtras(b);

            this.mListPlanoLaucher.launch(i);
        }));

        this.mAvancarBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), FragmentActivity.class);

            i.putExtra(
                    FragmentActivity.Extras.EXTRA_FRAGMENT,
                    FragmentActivity.Extras.FRAGMENT_MEDICO
            );

            i.putExtra(MedicoFragment.Extras.CLINICA, this.mClinica);
            i.putExtra(MedicoFragment.Extras.CONVENIO, this.mConvenio);
            i.putExtra(MedicoFragment.Extras.ESPECIALIDADE, this.mEspecialidade);

            startActivity(i);
        }));
    }

    private void onConvenio(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mConvenio = (Convenio) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);

            this.mConvenioBtn.setText(this.mConvenio.getNome());
            this.mPlanoBtn.setVisibility(View.VISIBLE);
        }
    }

    private void onPlano(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            this.mPlano = (PlanoSaude) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);

            this.mPlanoBtn.setText(this.mPlano.getNome());
            this.mCarterinhaEdt.setVisibility(View.VISIBLE);
            this.mAvancarBtn.setVisibility(View.VISIBLE);
        }
    }

    public interface Extras {
        String ESPECIALIDADE = "ESPECIALIDADE";
        String CLINICA = "CLINICA";
    }
}
