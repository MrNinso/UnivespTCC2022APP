package com.developer.projetounivesp2021_frontrnd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.projetounivesp2021_frontrnd.adapters.BaseAdapter;
import com.developer.projetounivesp2021_frontrnd.adapters.ConvenioAdapter;
import com.developer.projetounivesp2021_frontrnd.adapters.EstadoCidadeAdapter;
import com.developer.projetounivesp2021_frontrnd.adapters.PlanoAdapter;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;
import com.developer.projetounivesp2021_frontrnd.objects.PlanoSaude;
import com.developer.projetounivesp2021_frontrnd.tools.AsyncTools;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleSelectListActivity
        extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mListRW;
    private TextView mConfirmarBtn;
    private LinearLayout mLoadingLnl, mListLnl;
    private MenuItem mSerachMenu;
    private BaseAdapter<?, ?> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mListRW = findViewById(R.id.ls_rw_list);
        mConfirmarBtn = findViewById(R.id.ls_btn_confirm);
        mListLnl = findViewById(R.id.ls_lnl_list);
        mLoadingLnl = findViewById(R.id.ls_lnl_loading);

        mConfirmarBtn.setEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle b = getIntent().getExtras();

        setTitle(b.getInt(Extras.EXTRA_TITLE));
        getAdapter(b.getByte(Extras.EXTRA_LIST), b);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        this.mSerachMenu = menu.findItem(R.id.menu_search);

        SearchView s = (SearchView) this.mSerachMenu.getActionView();
        s.setOnQueryTextListener(this);

        this.mSerachMenu.setVisible(false);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        this.mAdapter.search(newText);
        return false;
    }

    private void getAdapter(byte a, Bundle b) {
        switch (a) {
            case Extras.LIST_CONVENIOS:
                AsyncTools.Promise<Object> c = new AsyncTools.Promise<>(this::loadConvenios);
                c.resolve(this::loadList);
                break;
            case Extras.LIST_PLANOS:
                AsyncTools.Promise<Integer> p = new AsyncTools.Promise<>(this::loadPlanos);
                p.Input = b.getInt(Search.EXTRA_SEARCH_CONVENIO);;

                p.resolve(this::loadList);
                break;
            case Extras.LIST_ESTADOS:
                AsyncTools.Promise<Object> e = new AsyncTools.Promise<>(this::loadEstados);

                e.resolve(this::loadList);
                break;

            case Extras.LIST_CIDADES:
                AsyncTools.Promise<String> ci = new AsyncTools.Promise<>(this::loadCidades);
                ci.Input =  b.getString(Search.EXTRA_SEARCH_ESTADO);

                ci.resolve(this::loadList);
                break;
        }
    }

    private EstadoCidadeAdapter loadCidades(String estado) {
        ArrayList<String> c = new ArrayList<>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            c.add(String.format("Cidade %s do estado %s", i, estado));
        }

        return new EstadoCidadeAdapter(c);
    }

    private EstadoCidadeAdapter loadEstados(Object o) {
        ArrayList<String> c = new ArrayList<>();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 20; i++) {
            c.add(String.format("Estado %s", i));
        }

        return new EstadoCidadeAdapter(c);
    }

    private ConvenioAdapter loadConvenios(Object o) {
        ArrayList<Convenio> c = new ArrayList<>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 20; i++) {
            c.add(new Convenio(i, String.format("%s Saúde", i)));
        }

        return new ConvenioAdapter(c);
    }

    private PlanoAdapter loadPlanos(Integer i) {
        ArrayList<PlanoSaude> p = new ArrayList<>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (long j = 0; j < 10; j++) {
            p.add(new PlanoSaude(i, j, String.format("%s Saúde Plano %s", i, j)));
        }

        return new PlanoAdapter(p);
    }

    private void loadList(BaseAdapter<?, ?> cva) {
        ViewTools viewTools = new ViewTools(this);

        this.mAdapter = cva;

        this.mAdapter.setOnItemSelected((v, pos, oldPos) ->
                mConfirmarBtn.setEnabled(true)
        );

        this.mAdapter.setOnItemUnselected((pos) ->
                mConfirmarBtn.setEnabled(false)
        );

        mListRW.setAdapter(this.mAdapter);
        mListRW.setLayoutManager(new LinearLayoutManager(this));

        mConfirmarBtn.setOnClickListener(viewTools.btnClick(100, v -> {
            Intent i = new Intent();

            i.putExtra(Extras.EXTRA_RESULT, (Serializable) cva.getSelectedItem());
            setResult(Activity.RESULT_OK, i);
            finish();
        }));

        this.mLoadingLnl.setVisibility(View.GONE);
        this.mListLnl.setVisibility(View.VISIBLE);
        this.mSerachMenu.setVisible(true);
    }

    public interface Extras {
        String EXTRA_LIST = "EXTRA_LIST";
        String EXTRA_TITLE = "EXTRA_TITLE";
        String EXTRA_RESULT = "EXTRA_RESULT";
        byte LIST_CONVENIOS = 0x0;
        byte LIST_PLANOS = 0x1;
        byte LIST_ESTADOS = 0x2;
        byte LIST_CIDADES = 0x3;
    }

    public interface Search {
        String EXTRA_SEARCH_CONVENIO = "EXTRA_SEARCH_CONVENIO";
        String EXTRA_SEARCH_ESTADO = "EXTRA_SEARCH_ESTADO";
    }
}