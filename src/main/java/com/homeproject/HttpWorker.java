package com.homeproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpWorker {

    private String url = "https://randomuser.me/api/?exc=login,email,registered,phone,id,picture";

    public StringBuffer getResponse() {

        int TIMEOUT_VALUE = 10000;
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(TIMEOUT_VALUE);
            connection.setReadTimeout(TIMEOUT_VALUE);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            try {
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            } finally {
                in.close();
            }
            return response;

        } catch (IOException e) {
            System.err.println("Соединение с интенетом прервано, данные формируются из локальной БД...");

        } catch (Exception e) {
            throw new RuntimeException("Ошибка читения Json из потока", e);
        }
        return null;
    }
}