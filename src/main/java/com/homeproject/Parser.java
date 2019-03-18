package com.homeproject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Parser {


    GeneratData generatData=new GeneratData();
    PathToFiles fPath=new PathToFiles();
    Random r = new Random();

    // Получаем пользователей из локальной базы
    public User getUserFromLocalDatabase(int count) {

        Locale local = new Locale("ru", "RU");
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

        String firstName = generatData.getNamesFromFile(fPath.fileNameMan, fPath.fileNameWoman)[count]; // Получаем первое имя из общего списка М+Ж
        String lastName;
        String patronic;
        String gender;
        Date dt = generatData.getDataBirth();

        // Тут получаем данные в зависимости от пола юзера
        if (generatData.getGender(firstName) == true) {

            lastName = generatData.getNameFromFile(fPath.fileSecondNameMan)[count];
            patronic = generatData.getNameFromFile(fPath.filePatronymicMan)[count];
            gender = "М";
        } else {
            lastName = generatData.getNameFromFile(fPath.fileSecondNameWoman)[count];
            patronic = generatData.getNameFromFile(fPath.filePatronymicWoman)[count];
            gender = "Ж";
        }

        String dateOfBorn = df.format(dt);
        String index = String.valueOf(generatData.getIndex());
        String inn = String.valueOf(generatData.getInn());
        String age = String.valueOf(generatData.getAge(dt));
        String country = generatData.getNameFromFile(fPath.fileCountries)[count];
        String state = generatData.getNameFromFile(fPath.fileOblast)[count];
        String city = generatData.getNameFromFile(fPath.fileCity)[count];
        String street = generatData.getNameFromFile(fPath.fileStreets)[count];
        String flat = String.valueOf(1 + r.nextInt(500));
        String house = String.valueOf(1 + r.nextInt(200));

        return new User(firstName, lastName, patronic, age, gender, dateOfBorn, city, street, country, flat, inn, index, house, state);
    }

    // Получение данных о пользователе из "https://randomuser.me/api/"
    public User getUserFromJSON(StringBuffer temp) {


        JsonElement jsonTree = new JsonParser().parse(temp.toString());

        JsonElement results = jsonTree.getAsJsonObject().get("results");

        JsonElement name = getArrayObject(results, "name");
        JsonElement firstName = getValue(name, "first");
        JsonElement lastName = getValue(name, "last");
        JsonElement gender = getArrayObject(results, "gender");
        JsonElement nationality = getArrayObject(results, "nat");
        JsonElement location = getArrayObject(results, "location");
        JsonElement state = getValue(location, "state");
        JsonElement city = getValue(location, "city");
        JsonElement street = getValue(location, "street");
        JsonElement index = getValue(location, "postcode");
        JsonElement dob = getArrayObject(results, "dob");
        JsonElement age = getValue(dob, "age");
        JsonElement dateOfBirth = getValue(dob, "date");

        String flat = String.valueOf(1 + r.nextInt(500));
        String house = getHouseFromStreet(street.toString().replace("\"", ""));
        String inn = String.valueOf(generatData.getInn()); // ИНН формируем из локальной базы
        String street1 = street.toString().replace("\"", "");
        String country = getCountry(nationality.toString().replace("\"", ""));

        return new User(
                getFormatedData(firstName), getFormatedData(lastName), "no patronymic",
                getFormatedData(age), getFormatedData(gender), getFormatDate(getFormatedData(dateOfBirth)),
                getFormatedData(city), street1.replaceAll(house, ""),
                country, flat, inn, getFormatedData(index), house, getFormatedData(state)
        );
    }

    private JsonElement getArrayObject(JsonElement array, String objectName) {
        return array.getAsJsonArray().get(0).getAsJsonObject().get(objectName);
    }

    private JsonElement getValue(JsonElement object, String objectName) {
        if (object.isJsonObject()) {
            return object.getAsJsonObject().get(objectName);
        }
        return null;
    }

    //Получаем номер дома из названия улицы
    private String getHouseFromStreet(String street) {

        return street.replaceAll("\\D+", "");

    }

    // Формируем дату полученную из Json в норм формат
    private String getFormatDate(String dt) {

        String day;
        String year;
        String month;
        String date;

        int k = dt.indexOf('T');

        dt = dt.substring(0, k);
        year = dt.substring(0, 4);
        day = dt.substring(8, k);
        month = dt.substring(5, 7);
        date = day + "." + month + "." + year;

        return date;
    }

    // Получаем страну из национальности
    private String getCountry(String temp) {

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

    // Преобразование к нормальному виду данных из Json
    private String getFormatedData(JsonElement data) {

        return data.toString().replace("\"", "");
    }
}
