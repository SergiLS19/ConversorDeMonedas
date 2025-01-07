package com.moneyconverter.alura.connections;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moneyconverter.alura.classes.TransaccionER;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHTTP {

    private static final String API_KEY = "6988a6b8c4c2561a6c934b20";

    public TransaccionER obtenerTransaccionER(String divisaEntrada, String divisaSalida) {
        try {

            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + divisaEntrada + "/" + divisaSalida;
            URL url = new URL(urlStr);


            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            if (request.getResponseCode() != 200) {
                throw new RuntimeException("Error en la conexión: " + request.getResponseCode());
            }


            InputStreamReader reader = new InputStreamReader(request.getInputStream());
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();


            double tasaCambio = response.get("conversion_rate").getAsDouble();

            return new TransaccionER(divisaEntrada, divisaSalida, tasaCambio);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
