package com.developer.projetounivesp2021_frontrnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.register.ClinicaFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.register.PlanoUsuarioFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.register.UserFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.search.EstadoCidadeFragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(new Bundle());
        setContentView(R.layout.activity_cadastro);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        byte fragmentID = getIntent().getExtras().getByte(Extras.EXTRA_FRAGMENT);
        BaseFragment fragment = getContent(fragmentID);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.cd_frag_content, fragment);

        setTitle(fragment.getTitle());

        ft.commit();
    }

    private BaseFragment getContent(byte fragment) {
        switch (fragment) {
            case Extras.FRAGMENT_USER:
                return new UserFragment(getSupportFragmentManager());
            case Extras.FRAGMENT_CLINICA:
                return new ClinicaFragment(getSupportFragmentManager());
            case Extras.FRAGMENT_USER_PLANO:
                return new PlanoUsuarioFragment(
                    getSupportFragmentManager(),
                    getIntent().getExtras()
                );
            case Extras.FRAGMENT_ESTADO_CIDADE:
                return new EstadoCidadeFragment(
                        getSupportFragmentManager(),
                        getIntent().getExtras()
                );
            default:
                return new BaseFragment(getSupportFragmentManager()) {
                    @Override
                    public int getTitle() {
                        return 0;
                    }

                    @Override
                    protected int getLayout() {
                        return 0;
                    }
                };
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public interface Extras {
        String EXTRA_FRAGMENT = "EXTRA_FRAGMENT";
        byte FRAGMENT_USER = 0x0;
        byte FRAGMENT_CLINICA = 0x1;
        byte FRAGMENT_USER_PLANO = 0x3;
        byte FRAGMENT_ESTADO_CIDADE = 0x4;
    }
}