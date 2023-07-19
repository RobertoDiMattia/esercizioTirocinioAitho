package com.example.veicoli;

//3. Creare una classe Java "Auto.java" che implementi l'interfaccia "Veicolo.java" e
// quindi il metodo "getTipoMezzo()" che dovrà ritornare la stringa "Sono un automobile"
public class Auto implements Veicolo {

    @Override
    public String getTipoMezzo() {
        return "Sono un automobile";
    }
}
