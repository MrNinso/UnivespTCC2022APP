package com.developer.projetounivesp2021_frontrnd.fragments.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.SimpleSelectListActivity;
import com.developer.projetounivesp2021_frontrnd.LoginActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.objects.Convenio;
import com.developer.projetounivesp2021_frontrnd.objects.PlanoSaude;
import com.developer.projetounivesp2021_frontrnd.objects.Usuario;
import com.developer.projetounivesp2021_frontrnd.tools.AsyncTools;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class PlanoUsuarioFragment extends BaseFragment {

    private TextView mSelecionarConvenioBtn, mSelecionarPlanoBtn, mEnviarBtn;
    private ActivityResultLauncher<Intent> mListConvenioLaucher;
    private ActivityResultLauncher<Intent> mListPlanosLaucher;
    private int mCid = -1;
    private long mCpid = -1;

    public PlanoUsuarioFragment(){
        super();
    }

    public PlanoUsuarioFragment(FragmentManager mMamanger, Bundle mExtras) {
        super(mMamanger, mExtras);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mListConvenioLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onConvenio
        );

        this.mListPlanosLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onPlano
        );
    }

    @Override
    public int getTitle() {
        return R.string.selecione_plano;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cadastro_usuario_plano;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        this.mSelecionarConvenioBtn = v.findViewById(R.id.cd_fg_usr_pl_btn_convenio);
        this.mSelecionarPlanoBtn = v.findViewById(R.id.cd_fg_usr_pl_btn_plano);
        this.mEnviarBtn = v.findViewById(R.id.cd_fg_usr_pl_btn_enviar);

        this.mSelecionarConvenioBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
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

        this.mSelecionarPlanoBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
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

            b.putInt(
                    SimpleSelectListActivity.Search.EXTRA_SEARCH_CONVENIO,
                    this.mCid
            );

            i.putExtras(b);

            this.mListPlanosLaucher.launch(i);
        }));

        this.mEnviarBtn.setOnClickListener(viewTools.btnClick(500, v1 -> {
            Usuario u = (Usuario) getExtras().getSerializable(Extras.EXTRA_USER);
            u.cpid = this.mCpid;

            AlertDialog dialog = viewTools.loadDialog(getLayoutInflater());

            AsyncTools.Promise<Usuario> usuarioPromise = new AsyncTools.Promise<>(usuario -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
                //TODO SEND API
            });

            usuarioPromise.resolve(o -> {
                dialog.dismiss();
                startActivity(new Intent(this.getContext(), LoginActivity.class));

                assert getActivity() != null;
                getActivity().finishAffinity();
            });

            dialog.show();
        }));
    }

    private void onConvenio(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            Convenio c = (Convenio) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);
            this.mCid = c.getCid();

            this.mSelecionarConvenioBtn.setText(c.getNome());

            this.mCpid = -1;
            this.mSelecionarPlanoBtn.setText(R.string.clique_aqui_para_selecionar_o_plano_de_saude);
            this.mSelecionarPlanoBtn.setVisibility(View.VISIBLE);
            this.mEnviarBtn.setVisibility(View.GONE);
        }
    }

    private void onPlano(ActivityResult r) {
        if (r.getResultCode() == Activity.RESULT_OK) {
            assert r.getData() != null;
            Bundle b = r.getData().getExtras();

            PlanoSaude p = (PlanoSaude) b.getSerializable(SimpleSelectListActivity.Extras.EXTRA_RESULT);
            this.mCpid = p.getCid();

            this.mEnviarBtn.setVisibility(View.VISIBLE);
            this.mSelecionarPlanoBtn.setText(p.getNome());
        }
    }

    public interface Extras {
        String EXTRA_USER = "EXTRA_USER";
    }

}
