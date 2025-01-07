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
        String divisaEntrada, divisaSalida;
        double saldoAConvertir;
        int opcion;
        Historial historial = new Historial();
        ConnectionHTTP con = new ConnectionHTTP();
        TransaccionER transER;
        List<TransaccionER> transacciones = new ArrayList<>();
        List<String> divisas = new ArrayList<>(List.of("Dolares (USD)","Euros (EUR)",
                "Pesos Argentinos (ARS)"," Soles Peruanos (PEN)","Libras esterlinas (GBP)"));


        do {
            System.out.println("Conversor de monedas");
            System.out.println("-----------------------");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Revisar historial de monedas");
            System.out.println("3. Exportar historial en formato JSON");
            System.out.println("4. Salir");
            opcion=sc.nextInt();

            switch (opcion){
                case 1:{
                    System.out.println("¿Que moneda desea convertir? Ingrese el codigo de la divisa");
                    System.out.println("------------------------------");
                    for (int i=0;i<divisas.size();i++){
                        System.out.println((i+1)+". "+divisas.get(i));
                    }
                    int opcionEntrada= sc.nextInt();
                    divisaEntrada=divisas.get(opcionEntrada-1);
                    divisas.remove(opcionEntrada-1);
                    System.out.println("¿A que moneda desea convertirla?");
                    System.out.println("------------------------------");
                    for (int i=0;i<divisas.size();i++){
                        System.out.println((i+1)+". "+divisas.get(i));
                    }
                    int opcionSalida = sc.nextInt();
                    divisaSalida=divisas.get(opcionEntrada-1);
                    System.out.println("Divisa a convertir: "+divisaEntrada);
                    System.out.println("Divisa de salida: "+divisaSalida);
                    transER=con.obtenerTransaccionER(divisaEntrada,divisaSalida);
                    System.out.println("¿Cuanto desea convertir?");
                    saldoAConvertir = sc.nextDouble();
                    if (transER != null) {
                        double resultado = saldoAConvertir * transER.conversion_rates();
                        System.out.println("Resultado: " + saldoAConvertir + " " + divisaEntrada + " = " + resultado + " " + divisaSalida);
                    } else {
                        System.out.println("Error en la conversión.");
                    }
                    transacciones.add(transER);
                    break;
                }


                case 2: System.out.println(transacciones);

                case 3: historial.exportarHistorial();

                case 4:{
                    System.out.println("Ejecucion concluida");
                    break;
                }
                default:
                    System.out.println("Opcion no valida");
            }



        }while (opcion!=4);
    }
}
