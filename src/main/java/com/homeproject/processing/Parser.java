package com.homeproject.processing;

import com.homeproject.User;
import com.homeproject.helper.PathToFiles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Parser {

    GeneratData generatData=new GeneratData();
    PathToFiles fPath=new PathToFiles();

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

}
