package com.developer.projetounivesp2021_frontrnd.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.FragmentActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseFragment {

    private TextView mClinicasBtn, mEspecialidadesBtn, mExamesBtn, mFavoritosBtn, mDependentesBtn;

    public HomeFragment() {
        super();
    }

    public HomeFragment(FragmentManager Mamanger) {
        super(Mamanger);
    }

    @Override
    public int getTitle() {
        return R.string.home;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(getContext());

        mClinicasBtn = v.findViewById(R.id.hm_fg_btn_clinicas);
        mEspecialidadesBtn = v.findViewById(R.id.hm_fg_btn_especialidade);
        mExamesBtn = v.findViewById(R.id.hm_fg_btn_exames);
        mFavoritosBtn = v.findViewById(R.id.hm_fg_btn_favoritos);
        mDependentesBtn = v.findViewById(R.id.hm_fg_btn_dependentes);

        mClinicasBtn.setOnClickListener(viewTools.btnClick(100, v1 -> {
            Intent i = new Intent(this.getContext(), FragmentActivity.class);

            i.putExtra(
                FragmentActivity.Extras.EXTRA_FRAGMENT,
                FragmentActivity.Extras.FRAGMENT_ESTADO_CIDADE
            );

            startActivity(i);
        }));
        mEspecialidadesBtn.setOnClickListener(viewTools.btnClick(100, v1 -> {}));
        mExamesBtn.setOnClickListener(viewTools.btnClick(100, v1 -> {}));
        mFavoritosBtn.setOnClickListener(viewTools.btnClick(100, v1 -> {}));
        mDependentesBtn.setOnClickListener(viewTools.btnClick(100, v1 -> {}));
    }
}