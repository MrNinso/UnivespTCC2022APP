package com.developer.projetounivesp2021_frontrnd.fragments.main;

import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;

public class AgendamentosFragment extends BaseFragment {

    public AgendamentosFragment() {
        super();
    }

    public AgendamentosFragment(FragmentManager Mamanger) {
        super(Mamanger);
    }

    @Override
    public int getTitle() {
        return R.string.agendamentos;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_agendamentos;
    }
}