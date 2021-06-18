package com.developer.projetounivesp2021_frontrnd.interfaces;

public interface Constants {

    enum SEXO {
        Masculino("M"), Feminino("F");

        SEXO(String sexo) {
            this.sexo = sexo;
        }

        private final String sexo;

        public String getSexo() {
            return this.sexo;
        }
    }
}
