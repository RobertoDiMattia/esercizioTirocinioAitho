package com.example.veicoli;

public class Punto extends Auto {

    @Override
    public String getTipoMezzo() {
        return super.getTipoMezzo() + " - Sono una Fiat Punto";
    }
}
