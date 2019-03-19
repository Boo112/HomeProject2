package com.homeproject.helper;

import com.google.gson.JsonElement;

public class ParserHelper {

    // Формируем дату для записи в эксель
    public String getFormatedDate(String dt) {

        String day;
        String year;
        String month;
        String date;

        int k = dt.indexOf('T');

        dt = dt.substring(0, k);
        year = dt.substring(0, 4);
        day = dt.substring(8, k);
        month = dt.substring(5, 7);
        date = day + "-" + month + "-" + year;

        return date;
    }

    //Получаем номер дома из названия улицы
    public String getHouseFromStreet(String street) {

        return street.replaceAll("\\D+", "");

    }

    // Получаем страну из национальности
    public String getCountry(String temp) {

        String country;

        //AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IR, NO, NL, NZ, TR, US
        switch (temp) {
            case "BR":
                country = "Brazil";
                break;
            case "US":
                country = "United States";
                break;
            case "FR":
                country = "France";
                break;
            case "CA":
                country = "Canada";
                break;
            case "AU":
                country = "Australia";
                break;
            case "CH":
                country = "China";
                break;
            case "DE":
                country = "Germany";
                break;
            case "DK":
                country = "Denmark";
                break;
            case "ES":
                country = "Spain";
                break;
            case "FI":
                country = "Finland";
                break;
            case "GB":
                country = "Great Britain";
                break;
            case "IE":
                country = "Norway";
                break;
            case "NO":
                country = "France";
                break;
            case "NL":
                country = "France";
                break;
            case "IR":
                country = "Turkey";
                break;
            case "NZ":
                country = "New Zealand";
                break;
            case "TR":
                country = "France";
                break;
            default:
                country = "Unknown Country";
                break;
        }
        return country;
    }

    // Преобразование в нормальную отформатированную строку данных из Json
    public String getFormatedData(JsonElement data) {

        return data.toString().replace("\"", "");
    }


}
