package com.developer.projetounivesp2021_frontrnd.fragments.register;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.FragmentActivity;
import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.fragments.BaseFragment;
import com.developer.projetounivesp2021_frontrnd.objects.Usuario;
import com.developer.projetounivesp2021_frontrnd.tools.DataTools;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

import java.util.Date;

public class UserFragment extends BaseFragment {
    private EditText mNomeEdt, mCPFEdt, mNascimentoEdt, mSexoEdt, mEmailEdt,
            mFoneEdt, mSenhaEdt, mSenha2Edt;
    private TextView mOkayBtn;

    public UserFragment() {
        super();
    }

    public UserFragment(FragmentManager mMamanger) {
        super(mMamanger);
    }

    @Override
    public int getTitle() {
        return R.string.cadastro_usuario;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cadastro_usuario;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewTools viewTools = new ViewTools(this.getContext());

        mNomeEdt = view.findViewById(R.id.cd_fg_usr_edt_nome);
        mCPFEdt = view.findViewById(R.id.cd_fg_usr_edt_cpf);
        mNascimentoEdt = view.findViewById(R.id.cd_fg_usr_edt_nascimento);
        mSexoEdt = view.findViewById(R.id.cd_fg_usr_edt_sexo);
        mEmailEdt = view.findViewById(R.id.cd_fg_usr_edt_email);
        mFoneEdt = view.findViewById(R.id.cd_fg_usr_edt_fone);
        mSenhaEdt = view.findViewById(R.id.cd_fg_usr_edt_password);
        mSenha2Edt = view.findViewById(R.id.cd_fg_usr_edt_password_2);
        mOkayBtn = view.findViewById(R.id.cd_fg_usr_btn_okay);

        mNomeEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mCPFEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mNascimentoEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mEmailEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mFoneEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mSenhaEdt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );
        mSenha2Edt.setOnFocusChangeListener((v, b) ->
                viewTools.hideHint(v, b, android.R.color.darker_gray)
        );

        mOkayBtn.setOnClickListener(viewTools.btnClick(100, this::onOkayClick));

        mCPFEdt.addTextChangedListener(viewTools.edtMask("###.###.###-##", mCPFEdt));
        mNascimentoEdt.addTextChangedListener(viewTools.edtMask("##/##/####", mNascimentoEdt));
        mFoneEdt.addTextChangedListener(viewTools.edtMask("(##) ####-#####", mFoneEdt));

        mSexoEdt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                onSexoClick(v);
            }
        });
        mSexoEdt.setOnClickListener(this::onSexoClick);
    }

    private void onSexoClick(View edt) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        @SuppressLint("InflateParams")
        View v = getLayoutInflater().inflate(
                R.layout.dialog_sexo,
                null
        );

        builder.setView(v);

        AlertDialog dialog = builder.create();

        ListView l = v.findViewById(R.id.dialog_sexo);

        String[] s = new String[] {
                getResources().getString(R.string.masculino),
                getResources().getString(R.string.feminino),
        };

        l.setAdapter(new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_list_item_1,
                s
        ));

        l.setOnItemClickListener((parent, view, position, id) -> {
            ((EditText) edt).setText(s[position]);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void onOkayClick(View view) {
        ViewTools v = new ViewTools(this.getContext());
        String nome = mNomeEdt.getText().toString();
        if (nome.length() <= 2) {
            v.showSnackBar(view, R.string.nome_invalido);
            return;
        }

        String cpf = ViewTools.umask(mCPFEdt.getText().toString());
        if (!DataTools.isValidCPF(cpf)) {
            v.showSnackBar(view, R.string.cpf_invalido);
            return;
        }
        String nascimento = mNascimentoEdt.getText().toString();
        if (nascimento.length() < 10) {
            v.showSnackBar(view, R.string.data_invalida);
            return;
        }

        Date date;
        try {
            date = DataTools.DATE_FORMAT.parse(nascimento);

            assert date != null;
            if (!date.before(new Date())) { //TODO 18 anos
                v.showSnackBar(view, R.string.data_invalida);
                return;
            }

        } catch (Exception e) {
            v.showSnackBar(view, R.string.data_invalida);
            return;
        }

        String sexo = mSexoEdt.getText().toString();
        if (sexo.length() <= 0) {
            v.showSnackBar(view, R.string.sexo_nao_selecionado);
            return;
        }

        String email = mEmailEdt.getText().toString();
        if (email.length() == 0) {
            v.showSnackBar(view, R.string.email_invalido);
            return;
        }

        if (!DataTools.EMAIL_REGEX.matcher(email).find()) {
            v.showSnackBar(view, R.string.email_invalido);
            return;
        }

        String telefone = ViewTools.umask(mFoneEdt.getText().toString());
        if (telefone.length() < 11) {
            v.showSnackBar(view, R.string.telefone_invalido);
            return;
        }

        String senha = mSenhaEdt.getText().toString();

        if (senha.length() < 8) {
            v.showSnackBar(view, R.string.senha_8_caracteres);
            return;
        }

        String senha2 = mSenha2Edt.getText().toString();
        if (!senha.equals(senha2)) {
            v.showSnackBar(view, R.string.senhas_nao_batem);
            return;
        }

        senha = String.format(
                getResources().getString(R.string.password_saut),
                DataTools.SHA1(senha)
        );

        Usuario usuario = new Usuario(
                nome.toLowerCase(), email.toLowerCase(),senha,
                cpf, date.getTime(), Long.parseLong(telefone),
                (sexo.equals(getString(R.string.masculino)) ? SEXO.Masculino : SEXO.Feminino )
        );

        getExtras().putSerializable(
                PlanoUsuarioFragment.Extras.EXTRA_USER,
                usuario
        );

        getExtras().putByte(
                FragmentActivity.Extras.EXTRA_FRAGMENT,
                FragmentActivity.Extras.FRAGMENT_USER_PLANO
        );

        Intent i = new Intent(this.getContext(), FragmentActivity.class);
        i.putExtras(getExtras());

        startActivity(i);
    }
}
