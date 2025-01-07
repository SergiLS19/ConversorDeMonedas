package com.moneyconverter.alura.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    List<Transaccion> transacciones = new ArrayList<>();
    Gson gson = new GsonBuilder().create();
    public void exportarHistorial() throws IOException {
        FileWriter historial = new FileWriter("Historial.json");
        historial.write(gson.toJson(transacciones));
        historial.close();
    }
}
