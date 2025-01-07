package com.moneyconverter.alura.main;

import com.moneyconverter.alura.classes.Historial;
import com.moneyconverter.alura.classes.Transaccion;
import com.moneyconverter.alura.classes.TransaccionER;
import com.moneyconverter.alura.connections.ConnectionHTTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String divisaEntrada = "", divisaSalida = "";
        double saldoAConvertir;
        int opcion;
        Historial historial = new Historial();
        ConnectionHTTP con = new ConnectionHTTP();
        TransaccionER transER;
        List<Transaccion> transacciones = new ArrayList<>();

        do {
            System.out.println("Conversor de monedas");
            System.out.println("-----------------------");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Revisar historial de transacciones");
            System.out.println("3. Exportar historial en formato JSON");
            System.out.println("4. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("¿Qué moneda desea convertir?");
                    System.out.println("------------------------------");
                    System.out.println("1. Dólares");
                    System.out.println("2. Euros");
                    System.out.println("3. Pesos argentinos");
                    System.out.println("4. Soles");
                    System.out.println("5. Libras esterlinas");
                    int opcionEntrada = sc.nextInt();

                    switch (opcionEntrada) {
                        case 1: divisaEntrada = "USD"; break;
                        case 2: divisaEntrada = "EUR"; break;
                        case 3: divisaEntrada = "ARS"; break;
                        case 4: divisaEntrada = "PEN"; break;
                        case 5: divisaEntrada = "GBP"; break;
                        default: System.out.println("Opción inválida"); return;
                    }

                    System.out.println("¿A qué moneda desea convertirla?");
                    System.out.println("------------------------------");

                    if (!divisaEntrada.equals("USD")) System.out.println("1. Dólares");
                    if (!divisaEntrada.equals("EUR")) System.out.println("2. Euros");
                    if (!divisaEntrada.equals("ARS")) System.out.println("3. Pesos argentinos");
                    if (!divisaEntrada.equals("PEN")) System.out.println("4. Soles");
                    if (!divisaEntrada.equals("GBP")) System.out.println("5. Libras esterlinas");

                    int opcionSalida = sc.nextInt();

                    switch (opcionSalida) {
                        case 1: divisaSalida = "USD"; break;
                        case 2: divisaSalida = "EUR"; break;
                        case 3: divisaSalida = "ARS"; break;
                        case 4: divisaSalida = "PEN"; break;
                        case 5: divisaSalida = "GBP"; break;
                        default: System.out.println("Opción inválida"); return;
                    }

                    System.out.println("Divisa a convertir: " + divisaEntrada);
                    System.out.println("Divisa de salida: " + divisaSalida);

                    transER = con.obtenerTransaccionER(divisaEntrada, divisaSalida);
                    System.out.println("¿Cuánto desea convertir?");
                    saldoAConvertir = sc.nextDouble();
                    transER = con.obtenerTransaccionER(divisaEntrada, divisaSalida);
                    if (transER != null) {

                        Transaccion transaccion = new Transaccion(transER, saldoAConvertir);
                        historial.transacciones.add(transaccion);
                        System.out.println("Resultado: " + transaccion.getResultadoConvertido() + " " + divisaSalida);

                        transacciones.add(transaccion);
                    } else {
                        System.out.println("Error en la conversión.");
                    }

                    break;
                }

                case 2: {
                    System.out.println("Historial de transacciones");
                    System.out.println("-------------------------------");
                    if (transacciones.isEmpty()) {
                        System.out.println("No hay conversiones en el historial.");
                    } else {
                        for (Transaccion transaccion : transacciones) {
                            System.out.println(transaccion);
                        }
                    }
                    break;
                }

                case 3: {
                    historial.exportarHistorial();
                    break;
                }

                case 4: {
                    System.out.println("Ejecución concluida");
                    break;
                }

                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);
    }
}
