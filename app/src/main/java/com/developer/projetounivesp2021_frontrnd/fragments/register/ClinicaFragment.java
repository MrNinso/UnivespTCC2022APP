package com.developer.projetounivesp2021_frontrnd.fragments.register;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;

public class ClinicaFragment extends BaseFragment {

    public ClinicaFragment() {
        super();
    }

    public ClinicaFragment(FragmentManager Mamanger, Bundle b) {
        super(Mamanger);
    }

    @Override
    public int getTitle() {
        return R.string.clinicas;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_clinicas;
    }
    //TODO
}
