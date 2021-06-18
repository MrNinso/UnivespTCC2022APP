package com.developer.projetounivesp2021_frontrnd.tools;

import android.annotation.SuppressLint;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class DataTools {

    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );
    @SuppressLint("SimpleDateFormat")
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean isValidCPF(String cpf) {
        if (cpf.length() != 11)
            return false;

        switch (cpf) {
            case "00000000000":
            case "11111111111":
            case "22222222222":
            case "33333333333":
            case "44444444444":
            case "55555555555":
            case "66666666666":
            case "77777777777":
            case "88888888888":
            case "99999999999":
                return false;
        }

        char dig10, dig11;
        int sm = 0 , r, num, peso = 10;

        for (int i = 0; i < 9; i++) {
            num = cpf.charAt(i) - 48;
            sm = sm + (num * peso);

            peso--;
        }

        r = 11 - (sm % 11);

        dig10 = ((r == 10) || (r == 11)) ? '0' : (char)(r +48);

        sm = 0;
        peso = 11;


        for (int i = 0; i < 10; i++) {
            num = cpf.charAt(i) - 48;
            sm = sm + (num * peso);
            peso--;
        }

        r = 11 - (sm % 11);

        dig11 = ((r == 10) || (r == 11)) ? '0' : (char)(r +48);

        return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);
    }

    public static String SHA1(String Password) {
        return DigestUtils.sha1Hex(Password.getBytes());
    }
}
