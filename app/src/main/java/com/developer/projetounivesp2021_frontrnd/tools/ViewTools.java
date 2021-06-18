package com.developer.projetounivesp2021_frontrnd.tools;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

import com.developer.projetounivesp2021_frontrnd.R;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;

import java.util.regex.Pattern;

public class ViewTools {
    private final Context mContext;
    private final static Pattern umask = Pattern.compile("[.]|[-]|[/]|[(]|[ ]|[:]|[)]");

    public ViewTools(Context mContext) {
        this.mContext = mContext;
    }

    // Edt Hide hint onFocos
    public void hideHint(View view, boolean b, @ColorRes int color) {
        if (view instanceof EditText) {
            ((EditText) view).setHintTextColor(
                    this.mContext.getResources().getColor(b ? R.color.none : color)
            );
        }
    }

    public View.OnClickListener btnClick(long delay, View.OnClickListener listener) {
        return v -> {
            v.setEnabled(false);
            new Handler().postDelayed(() -> v.setEnabled(true), delay);
            listener.onClick(v);
        };
    }

    public TextWatcher edtMask(String mask, EditText editText) {
        return new TextWatcher() {
            boolean update = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!update) {
                    update = true;
                    return;
                }
                String str = ViewTools.umask(s.toString());
                String m = mask;
                for (int i = 0; i < str.length(); i++) {
                    m = m.replaceFirst("[#]", String.valueOf(str.charAt(i)));
                }

                m = m.replaceAll("#.*", "");

                if (m.length()!= 0)
                    if (umask.matcher(String.valueOf(m.charAt(m.length() - 1))).find()) {
                        m = m.substring(0, m.length() - 1);
                    }

                update = false;
                editText.setText(m);
                editText.setSelection(editText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    public AlertDialog loadDialog(LayoutInflater layoutInflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        @SuppressLint("InflateParams")
        View v2 = layoutInflater.inflate(
                R.layout.dialog_loading,
                null
        );

        builder.setView(v2);

        builder.setCancelable(false);

        return builder.create();
    }

    public void showSnackBar(View v, @StringRes int res) {
        Snackbar.make(v, res, Snackbar.LENGTH_LONG).show();
    }

    public void showSnackBar(View v, String text) {
        Snackbar.make(v, text, Snackbar.LENGTH_LONG).show();
    }

    public static String umask(String e) {
        return umask.matcher(e).replaceAll("");
    }
}
