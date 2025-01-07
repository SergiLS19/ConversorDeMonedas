package com.moneyconverter.alura.classes;



public class Transaccion {
    private String divisaEntrada;
    private String divisaSalida;
    private double valorAConvertir;
    private int numTransaccion;
    private int contador = 1;



    public Transaccion(TransaccionER tranER){
        this.numTransaccion= contador++;
        this.valorAConvertir =Double.parseDouble(String.valueOf(tranER.conversion_rates())) ;
        this.divisaEntrada = tranER.base_code();
        this.divisaSalida=tranER.target_code();
    }


    public double getValorAConvertir() {
        return valorAConvertir;
    }

    public String getDivisaEntrada() {
        return divisaEntrada;
    }

    public void setDivisaEntrada(String divisaEntrada) {
        this.divisaEntrada = divisaEntrada;
    }
    @Override
    public String toString() {
        return "N° de transaccion: "+numTransaccion+
                "Divisa: "+ divisaEntrada +
                "Valor: "+ valorAConvertir;
    }

}
