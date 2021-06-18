package com.developer.projetounivesp2021_frontrnd.fragments.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;

import org.jetbrains.annotations.NotNull;

public class NotificacaoFragment extends BaseFragment {

    public NotificacaoFragment() {
        super();
    }

    public NotificacaoFragment(FragmentManager Mamanger) {
        super(Mamanger);
    }

    @Override
    public int getTitle() {
        return R.string.notificacao;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_agendamentos;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}