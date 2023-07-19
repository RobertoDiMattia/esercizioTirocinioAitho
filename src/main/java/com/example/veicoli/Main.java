package com.example.veicoli;

public class Main {
    public static void main(String[] args) {
        Auto auto = new Auto();
        Punto punto = new Punto();

        System.out.println(auto.getTipoMezzo());
        System.out.println(punto.getTipoMezzo());
    }
}