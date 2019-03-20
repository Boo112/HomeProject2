package com.homeproject.processing;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.homeproject.User;
import com.homeproject.helper.ParserHelper;
import com.homeproject.helper.PathToFiles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Parser {

    GeneratData generatData=new GeneratData();
    PathToFiles fPath=new PathToFiles();
    ParserHelper parserHelper=new ParserHelper();

    Random r = new Random();

    // Получаем пользователей из локальной базы
    public User getUserFromLocalDatabase(int count) {


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

        String dateOfBorn = new SimpleDateFormat("dd-MM-yyyy").format(dt);
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
    public User getUserFromJSON(StringBuffer temp,int count) {


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
        String house = parserHelper.getHouseFromStreet(street.toString().replace("\"", ""));
        String inn = String.valueOf(generatData.getInn()); // ИНН формируем из локальной базы
        String street1 = street.toString().replace("\"", "");
        String country = parserHelper.getCountry(nationality.toString().replace("\"", ""));
        String patronymic;

        if(parserHelper.getFormatedData(gender)=="male") {
            patronymic = generatData.getNameFromFile(fPath.filePatronymicMan)[count];
        }else{
            patronymic = generatData.getNameFromFile(fPath.filePatronymicWoman)[count];
        }

        return new User(
                parserHelper.getFormatedData(firstName), parserHelper.getFormatedData(lastName), patronymic,
                parserHelper.getFormatedData(age), parserHelper.getFormatedData(gender),
                parserHelper.getFormatedDate(parserHelper.getFormatedData(dateOfBirth)),
                parserHelper.getFormatedData(city), street1.replaceAll(house, ""),
                country, flat, inn, parserHelper.getFormatedData(index), house, parserHelper.getFormatedData(state)
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

}
