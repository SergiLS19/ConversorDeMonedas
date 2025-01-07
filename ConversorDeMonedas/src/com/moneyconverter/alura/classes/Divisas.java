package com.moneyconverter.alura.classes;

import java.util.HashMap;
import java.util.Map;

public class Divisas {
    private static final Map<String, String> divisas = new HashMap<>();

    static {
        divisas.put("Dólares", "USD");
        divisas.put("Euros", "EUR");
        divisas.put("Pesos Argentinos", "ARS");
        divisas.put("Soles", "PEN");
        divisas.put("Libras Esterlinas", "GBP");
    }

    public static String obtenerCodigoDivisa(String nombreDivisa) {
        return divisas.get(nombreDivisa);
    }
}

