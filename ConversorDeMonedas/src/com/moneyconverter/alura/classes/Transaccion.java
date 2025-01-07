package com.moneyconverter.alura.classes;

public class Transaccion {
    private String divisaEntrada;
    private String divisaSalida;
    private double valorAConvertir;
    private double resultadoConvertido;
    private int numTransaccion;
    private static int contador = 1;


    public Transaccion(TransaccionER tranER, double saldoAConvertir){
        this.numTransaccion = contador++;
        this.valorAConvertir = saldoAConvertir;
        this.divisaEntrada = tranER.base_code();
        this.divisaSalida = tranER.target_code();
        this.resultadoConvertido = saldoAConvertir * tranER.conversion_rates();
    }


    public double getValorAConvertir() {
        return valorAConvertir;
    }

    public double getResultadoConvertido() {
        return resultadoConvertido;
    }

    public String getDivisaEntrada() {
        return divisaEntrada;
    }

    public String getDivisaSalida() {
        return divisaSalida;
    }
    @Override
    public String toString() {
        return "N° de transacción: " + numTransaccion + "\n" +
                "De: " + divisaEntrada + "\n" +
                "A: " + divisaSalida + "\n" +
                "Monto a convertir: " + valorAConvertir + "\n" +
                "Resultado: " + resultadoConvertido;
    }
}
