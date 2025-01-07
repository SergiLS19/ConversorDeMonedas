package com.moneyconverter.alura.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    public List<Transaccion> transacciones = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void exportarHistorial() {
        try {
            FileWriter historial = new FileWriter("Historial.json");
            historial.write(gson.toJson(transacciones));
            historial.close();
            System.out.println("Historial exportado en formato JSON");
        } catch (IOException e) {
            System.out.println("Error al exportar el historial: " + e.getMessage());
        }
    }

}
