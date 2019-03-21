package com.homeproject.processing;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.homeproject.User;
import com.homeproject.helper.ParserHelper;
import com.homeproject.helper.PathToFiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Parser {

    GeneratData generatData=new GeneratData();
    PathToFiles fPath=new PathToFiles();
    ParserHelper parserHelper=new ParserHelper();

    Random r = new Random();

    // Получаем пользователей из локальной базы
    public User getUserFromLocalDatabase(int count) {
        User user = new User();

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

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronic);
        user.setGender(gender);
        user.setCountry(generatData.getNameFromFile(fPath.fileCountries)[count]);
        user.setStreet(generatData.getNameFromFile(fPath.fileStreets)[count]);
        user.setInn(String.valueOf(generatData.getInn()));
        user.setHouse(String.valueOf(1 + r.nextInt(200)));
        user.setFlat(String.valueOf(1 + r.nextInt(500)));
        user.setDateOfBorn(new SimpleDateFormat("dd-MM-yyyy").format(dt));
        user.setAge(String.valueOf(generatData.getAge(dt)));
        user.setIndex(String.valueOf(generatData.getIndex()));
        user.setCity(generatData.getNameFromFile(fPath.fileCity)[count]);
        user.setState(generatData.getNameFromFile(fPath.fileOblast)[count]);

        return user;
    }

    // Получение данных о пользователе из "https://randomuser.me/api/"
    public User getUserFromJSON(StringBuffer temp,int count) {
        User user = new User();

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

        user.setFirstName(parserHelper.getFormatedData(firstName));
        user.setLastName(parserHelper.getFormatedData(lastName));
        user.setPatronymic(parserHelper.getPatronymic(gender,generatData,fPath,count));
        user.setGender(parserHelper.genderToRus(gender));
        user.setCountry(parserHelper.getCountry(nationality.toString().replace("\"", "")));
        user.setStreet(parserHelper.getStreetWithoutHouse (street.toString().replace("\"", ""),
                parserHelper.getHouseFromStreet(street.toString().replace("\"", ""))));
        user.setInn(String.valueOf(generatData.getInn()));
        user.setHouse(parserHelper.getHouseFromStreet(street.toString().replace("\"", "")));
        user.setFlat(String.valueOf(1 + r.nextInt(500)));
        user.setDateOfBorn(parserHelper.getFormatedDate(parserHelper.getFormatedData(dateOfBirth)));
        user.setAge(parserHelper.getFormatedData(age));
        user.setIndex(parserHelper.getFormatedData(index));
        user.setCity(parserHelper.getFormatedData(city));
        user.setState(parserHelper.getFormatedData(state));

        return user;
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
