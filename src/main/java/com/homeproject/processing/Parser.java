package com.homeproject.processing;

import com.homeproject.User;
import com.homeproject.helper.PathToFiles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Parser {

    GenerateData generateData =new GenerateData();
    PathToFiles fPath=new PathToFiles();

    Random r = new Random();

    // Получаем пользователей из локальной базы
    public User getUserFromLocalDatabase(int count) {
        User user = new User();

        String firstName = generateData.getNamesFromFile(fPath.fileNameMan, fPath.fileNameWoman)[count]; // Получаем первое имя из общего списка М+Ж

        String lastName;
        String patronic;
        String gender;
        Date dt = generateData.getDataBirth();

        // Тут получаем данные в зависимости от пола юзера
        if (generateData.getGender(firstName) == true) {
            lastName = generateData.getNameFromFile(fPath.fileSecondNameMan)[count];
            patronic = generateData.getNameFromFile(fPath.filePatronymicMan)[count];
            gender = "М";
        } else {
            lastName = generateData.getNameFromFile(fPath.fileSecondNameWoman)[count];
            patronic = generateData.getNameFromFile(fPath.filePatronymicWoman)[count];
            gender = "Ж";
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronic);
        user.setGender(gender);
        user.setCountry(generateData.getNameFromFile(fPath.fileCountries)[count]);
        user.setStreet(generateData.getNameFromFile(fPath.fileStreets)[count]);
        user.setInn(String.valueOf(generateData.getInn()));
        user.setHouse(String.valueOf(1 + r.nextInt(200)));
        user.setFlat(String.valueOf(1 + r.nextInt(500)));
        user.setDateOfBorn(new SimpleDateFormat("dd-MM-yyyy").format(dt));
        user.setAge(String.valueOf(generateData.getAge(dt)));
        user.setIndex(String.valueOf(generateData.getIndex()));
        user.setCity(generateData.getNameFromFile(fPath.fileCity)[count]);
        user.setState(generateData.getNameFromFile(fPath.fileOblast)[count]);

        return user;
    }

}
