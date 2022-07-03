package com.developer.projetounivesp2021_frontrnd.fragments.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.FragmentActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class MedicoFragment extends BaseFragment {

    private TextView mAvancarBtn;

    public MedicoFragment(FragmentManager Mamanger, Bundle b) {
        super(Mamanger, b);
    }

    @Override
    public int getTitle() {
        return R.string.especialidades_clinicas;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_medico;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mAvancarBtn = v.findViewById(R.id.sh_fg_btn_avancar);

        this.mAvancarBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Intent i = new Intent(this.getContext(), FragmentActivity.class);

            i.putExtra(
                    FragmentActivity.Extras.EXTRA_FRAGMENT,
                    FragmentActivity.Extras.FRAGMENT_AGENDAMENTO_CONFIRMADO
            );

            i.putExtra(AgendamentoConfirmadoFragment.Extras.CLINICA, this.mExtras.getSerializable(Extras.CLINICA));
            i.putExtra(AgendamentoConfirmadoFragment.Extras.CONVENIO, this.mExtras.getSerializable(Extras.CONVENIO));
            i.putExtra(AgendamentoConfirmadoFragment.Extras.ESPECIALIDADE, this.mExtras.getSerializable(Extras.ESPECIALIDADE));

            startActivity(i);
        }));
    }

    interface Extras {
        String CONVENIO = "CONVENIO";
        String ESPECIALIDADE = "ESPECIALIDADE";
        String CLINICA = "CLINICA";
    }
}
