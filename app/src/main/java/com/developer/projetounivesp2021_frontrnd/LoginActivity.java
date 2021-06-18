package com.developer.projetounivesp2021_frontrnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.projetounivesp2021_frontrnd.objects.Login;
import com.developer.projetounivesp2021_frontrnd.tools.AsyncTools;
import com.developer.projetounivesp2021_frontrnd.tools.DataTools;
import com.developer.projetounivesp2021_frontrnd.tools.ViewTools;

public class LoginActivity extends AppCompatActivity {

    private EditText mLoginEdt, mPasswordEdt;
    private TextView mLoginBtn, mCreateBtn;
    private ImageView mSairBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewTools viewTools = new ViewTools(this);

        mLoginEdt = findViewById(R.id.lg_edt_login);
        mPasswordEdt = findViewById(R.id.lg_edt_password);
        mLoginBtn = findViewById(R.id.lg_btn_login);
        mCreateBtn = findViewById(R.id.lg_btn_create);
        mSairBtn = findViewById(R.id.lg_btn_sair);

        mLoginEdt.setOnFocusChangeListener((v, b) -> viewTools.hideHint(v, b, R.color.white));
        mPasswordEdt.setOnFocusChangeListener((v, b) -> viewTools.hideHint(v, b, R.color.white));

        mLoginBtn.setOnClickListener(viewTools.btnClick(1000, this::onLoginClick));
        mCreateBtn.setOnClickListener(viewTools.btnClick(1000, this::onCreateClick));
        mSairBtn.setOnClickListener(viewTools.btnClick(100, v -> onBackPressed()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableFullscreen(null);
    }

    private void onLoginClick(View view) {
        ViewTools v = new ViewTools(this);
        String email = mLoginEdt.getText().toString();
        if (!DataTools.EMAIL_REGEX.matcher(email).find()) {
            v.showSnackBar(view, R.string.email_invalido);
            return;
        }

        String senha = mPasswordEdt.getText().toString();

        if (senha.length() < 8) {
            v.showSnackBar(view, R.string.senha_8_caracteres);
            return;
        }

        senha = String.format(
                getResources().getString(R.string.password_saut),
                DataTools.SHA1(senha)
        );

        AlertDialog dialog = v.loadDialog(getLayoutInflater());

        AsyncTools.Promise<Login> loginPromisse = new AsyncTools.Promise<>(login -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return login.getEmail().equals("email@email.com.br");
        });

        loginPromisse.Input = new Login(email, senha);

        loginPromisse.resolve(o -> {
            dialog.dismiss();
            enableFullscreen(null);
            if ((Boolean) o) {
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
            } else {
                v.showSnackBar(view, R.string.email_senha_invalido);
            }
        });

        dialog.show();
    }

    private void onCreateClick(View view) {
        Intent i = new Intent(this, FragmentActivity.class);

        i.putExtra(
                FragmentActivity.Extras.EXTRA_FRAGMENT,
                FragmentActivity.Extras.FRAGMENT_USER
        );

        startActivity(i);
    }

    public void enableFullscreen(View view) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}