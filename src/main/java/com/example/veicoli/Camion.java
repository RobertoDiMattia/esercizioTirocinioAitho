package com.example.veicoli;

//4. Creare un'altra classe Java "Camion.java" che implementi l'interfaccia "Veicolo.java"
// e quindi il metodo "getTipoMezzo()" che dovrà ritornare la stringa "Sono un camion"

public class Camion implements Veicolo {

    @Override
    public String getTipoMezzo() {
        return "Sono un camion";
    }
}
