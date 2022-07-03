package com.developer.projetounivesp2021_frontrnd.fragments.search;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.objects.Clinica;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;
import com.developer.projetounivesp2021_frontrnd.objects.Especialidade;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class AgendamentoConfirmadoFragment extends BaseFragment {
    private TextView mClinicaTvw, mConvenioTvw, mEspcialidadeTvw, mMedicoTvw, mDataTvw;

    public AgendamentoConfirmadoFragment(FragmentManager mMamanger, Bundle mExtras) {
        super(mMamanger, mExtras);
    }

    @Override
    public int getTitle() {
        return R.string.meu_agendamento;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_agendametoconfirmado;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        this.mClinicaTvw = v.findViewById(R.id.sh_fg_tvw_clinica);
        this.mConvenioTvw = v.findViewById(R.id.sh_fg_tvw_convenio);
        this.mEspcialidadeTvw = v.findViewById(R.id.sh_fg_tvw_espcialidade);
        this.mMedicoTvw = v.findViewById(R.id.sh_fg_tvw_medico);
        this.mDataTvw = v.findViewById(R.id.sh_fg_tvw_data);


        this.mClinicaTvw.setText(((Clinica)this.mExtras.getSerializable(Extras.CLINICA)).getNome());
        this.mConvenioTvw.setText(
                String.format("Convênio: %s", ((Convenio)this.mExtras.getSerializable(Extras.CONVENIO)).getNome())
        );
        this.mEspcialidadeTvw.setText(
                String.format("Especialidade: %s",((Especialidade)this.mExtras.getSerializable(Extras.ESPECIALIDADE)).getNome())
        );
        this.mMedicoTvw.setText("Dr. Exemplo");
        this.mDataTvw.setText("01/08/2022 - 15:30");
    }

    interface Extras {
        String CLINICA = "CLINICA";
        String CONVENIO = "CONVENIO";
        String ESPECIALIDADE = "ESPECIALIDADE";
    }
}
