package com.developer.projetounivesp2021_frontrnd;

import android.os.Bundle;

import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.main.AgendamentosFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.main.HomeFragment;
import com.developer.projetounivesp2021_frontrnd.fragments.main.NotificacaoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNavView;

    private BaseFragment mHomeFragment, mAgendamentosFragment, mNotificacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mNavView = findViewById(R.id.mn_nav_view);

        this.mNavView.getMenu().clear();
        this.mNavView.inflateMenu(R.menu.bottom_nav_menu);

        this.mNavView.setOnNavigationItemSelectedListener(item -> {
            updateFragment(item.getNumericShortcut());
            return true;
        });

        this.updateFragment('0');
    }

    private void updateFragment(char pos) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment fragment = getFragment(pos);
        assert fragment != null;
        ft.replace(R.id.mn_host_fragment, fragment);
        setTitle(fragment.getTitle());

        ft.commit();
    }

    private BaseFragment getFragment(char pos) {
        switch (pos) {
            case '0':
                if (this.mHomeFragment == null) {
                  this.mHomeFragment = new HomeFragment(getSupportFragmentManager());
                }

                return this.mHomeFragment;
            case '1':
                if (this.mAgendamentosFragment == null) {
                    this.mAgendamentosFragment = new AgendamentosFragment(getSupportFragmentManager());
                }

                return this.mAgendamentosFragment;
            case '2':
                if (this.mNotificacoes == null) {
                    this.mNotificacoes = new NotificacaoFragment(getSupportFragmentManager());
                }

                return this.mNotificacoes;
        }

        return null;
    }

}