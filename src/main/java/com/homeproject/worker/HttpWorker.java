package com.homeproject.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpWorker {

    private String url = "https://randomuser.me/api/?exc=login,email,registered,phone,id,picture";

    public StringBuffer getResponse() {

        int timeoutValue = 10000;
        BufferedReader in=null;


        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeoutValue);
            connection.setReadTimeout(timeoutValue);


            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

            return response;

        } catch (IOException e) {
            System.err.println("Соединение с интенетом прервано, данные формируются из локальной БД...");

        }

        // тут может нужно переделать!!!!
        finally {
            try {
                if(in==null)
                return null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }
}
